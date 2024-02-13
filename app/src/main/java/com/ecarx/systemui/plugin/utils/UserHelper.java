package com.ecarx.systemui.plugin.utils;

import com.ecarx.sdk.ECarXAPIBase;
import com.ecarx.sdk.openapi.ECarXApiClient;
import com.ecarx.sdk.user.IUser;
import com.ecarx.sdk.user.UserAPI;
import com.ecarx.sdk.user.callback.LoginCallback;
import com.ecarx.systemui.plugin.PluginContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class UserHelper {
    private static final String TAG = UserHelper.class.getSimpleName();
    private static final long WAIT_TIME = 10000;
    private final Object INIT_LOCK;
    private final Object LOCK;
    private AtomicBoolean hasInit;
    private AtomicBoolean isReady;
    private final List<LoginCallback> listeners;
    private final LoginCallback loginCallback;
    private UserAPI userAPI;

    /* loaded from: classes.dex */
    private static class Holder {
        static final UserHelper ourInstance = new UserHelper();

        private Holder() {
        }
    }

    public static UserHelper get() {
        return Holder.ourInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForReady() {
        if (!this.hasInit.get()) {
            synchronized (this.INIT_LOCK) {
                try {
                    Lg.d(TAG, "wait init lock start");
                    this.INIT_LOCK.wait(WAIT_TIME);
                    Lg.d(TAG, "wait init lock end");
                } catch (InterruptedException unused) {
                }
            }
        }
        UserAPI userAPI = this.userAPI;
        if (userAPI == null || !needAuth(userAPI) || this.isReady.get()) {
            return;
        }
        synchronized (this.LOCK) {
            try {
                Lg.d(TAG, "wait ready lock start");
                this.LOCK.wait(WAIT_TIME);
                Lg.d(TAG, "wait ready lock end");
            } catch (InterruptedException unused2) {
            }
        }
    }

    private UserHelper() {
        this.listeners = new ArrayList();
        this.LOCK = new Object();
        this.INIT_LOCK = new Object();
        this.isReady = new AtomicBoolean(false);
        this.hasInit = new AtomicBoolean(false);
        this.loginCallback = new LoginCallback() { // from class: com.ecarx.systemui.plugin.utils.UserHelper.2
            @Override // com.ecarx.sdk.user.callback.LoginCallback, com.ecarx.sdk.user.callback.ILoginCallBack
            public void onLogin() {
                Lg.d(UserHelper.TAG, "LoginCallback onLogin");
                for (LoginCallback loginCallback : UserHelper.this.listeners) {
                    loginCallback.onLogin();
                }
            }

            @Override // com.ecarx.sdk.user.callback.LoginCallback, com.ecarx.sdk.user.callback.ILoginCallBack
            public void onLogout() {
                Lg.d(UserHelper.TAG, "LoginCallback onLogout");
                for (LoginCallback loginCallback : UserHelper.this.listeners) {
                    loginCallback.onLogout();
                }
            }

            @Override // com.ecarx.sdk.user.callback.LoginCallback, com.ecarx.sdk.user.callback.ILoginCallBack
            public void onTokenRefresh(String str) {
                String str2 = UserHelper.TAG;
                Lg.d(str2, "LoginCallback onTokenRefresh: " + str);
                for (LoginCallback loginCallback : UserHelper.this.listeners) {
                    loginCallback.onTokenRefresh(str);
                }
            }
        };
        ECarXApiClient.Callback callback = new ECarXApiClient.Callback() { // from class: com.ecarx.systemui.plugin.utils.UserHelper.1
            @Override // com.ecarx.sdk.openapi.ECarXApiClient.Callback
            public void onAPIReady(boolean z) {
                String str = UserHelper.TAG;
                Lg.d(str, "[JRSYS] userAPI.init callback: " + z);
                UserHelper.this.isReady.set(z);
                synchronized (UserHelper.this.LOCK) {
                    UserHelper.this.LOCK.notifyAll();
                }
                if (UserHelper.this.userAPI != null && z) {
                    UserHelper.this.userAPI.setLoginCallback(UserHelper.this.loginCallback);
                }
            }
        };
        try {
            UserAPI createUserAPI = UserAPI.createUserAPI(PluginContext.getHostContext());
            if (createUserAPI == null) {
                Lg.w(TAG, "create UserAPI error");
                this.hasInit.set(true);
                synchronized (this.INIT_LOCK) {
                    this.INIT_LOCK.notifyAll();
                }
            } else if (!needAuth(createUserAPI)) {
                createUserAPI.setLoginCallback(this.loginCallback);
                this.hasInit.set(true);
                synchronized (this.INIT_LOCK) {
                    this.INIT_LOCK.notifyAll();
                }
            } else {
                createUserAPI.init(PluginContext.getHostContext(), callback);
                this.userAPI = createUserAPI;
                this.hasInit.set(true);
                synchronized (this.INIT_LOCK) {
                    this.INIT_LOCK.notifyAll();
                }
            }
        } catch (Throwable th) {
            this.hasInit.set(true);
            synchronized (this.INIT_LOCK) {
                this.INIT_LOCK.notifyAll();
                throw th;
            }
        }
    }

    private boolean needAuth(ECarXAPIBase eCarXAPIBase) {
        return eCarXAPIBase.getVersionInt() >= 330;
    }

    public boolean hasLogin() {
        waitForReady();
        UserAPI userAPI = this.userAPI;
        if (userAPI == null) {
            return false;
        }
        return userAPI.hasLogin();
    }

    public String getAccessToken() {
        waitForReady();
        UserAPI userAPI = this.userAPI;
        if (userAPI == null) {
            return null;
        }
        return userAPI.getAccessToken();
    }

    public IUser getLoginUser() {
        waitForReady();
        UserAPI userAPI = this.userAPI;
        if (userAPI == null) {
            return null;
        }
        return userAPI.getLoginUser();
    }

    public boolean launchLogin() {
        waitForReady();
        UserAPI userAPI = this.userAPI;
        if (userAPI == null) {
            return false;
        }
        return userAPI.launchLogin();
    }

    public boolean notifyTokenExpired() {
        waitForReady();
        UserAPI userAPI = this.userAPI;
        if (userAPI == null) {
            return false;
        }
        return userAPI.notifyTokenExpired();
    }

    public void addListener(LoginCallback loginCallback) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() { // from class: com.ecarx.systemui.plugin.utils.UserHelper.3
            @Override // java.lang.Runnable
            public void run() {
                UserHelper.this.waitForReady();
            }
        });
        if (this.listeners.contains(loginCallback)) {
            return;
        }
        this.listeners.add(loginCallback);
    }

    public void removeListener(LoginCallback loginCallback) {
        this.listeners.remove(loginCallback);
    }
}
