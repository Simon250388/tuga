package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import com.ecarx.sdk.openapi.ECarXApiClient;
import com.ecarx.sdk.user.IUser;
import com.ecarx.sdk.user.UserAPI;
import com.ecarx.sdk.user.callback.LoginCallback;
import com.ecarx.systemui.plugin.PluginContext;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.navigationbar.DataServerController;
import com.ecarx.systemui.plugin.navigationbar.IShortCut;
import com.ecarx.systemui.plugin.navigationbar.NavigationBar;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.utils.AppExecutors;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.UserHelper;

/* loaded from: classes.dex */
public class UserController extends BaseController {
    private static final String TAG = UserController.class.getSimpleName();
    private static volatile UserController userController;
    private Context mContext;
    private DataServerController mDataServerController;
    private Context mHostContext;
    private UserAPI userAPI;
    private String userAvatarURL;
    private boolean userInit;
    private String username;

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserName(String str) {
    }

    private UserController(Context context) {
        super(context);
        this.mHostContext = context;
        this.mContext = PluginContext.getPluginContext();
        init();
    }

    public static UserController getInstance() {
        if (userController == null) {
            synchronized (UserController.class) {
                if (userController == null) {
                    userController = new UserController(PluginContext.getHostContext());
                }
            }
        }
        return userController;
    }

    public void init() {
        this.userAPI = UserAPI.get(this.mHostContext);
        String str = TAG;
        Log.d(str, "[JRSYS_USER] init user " + this.userAPI);
        this.userAPI.init(this.mHostContext, new ECarXApiClient.Callback() { // from class: com.ecarx.systemui.plugin.statusbar.policy.UserController.1
            @Override // com.ecarx.sdk.openapi.ECarXApiClient.Callback
            public void onAPIReady(boolean z) {
                UserController.this.userInit = z;
                if (z) {
                    Log.d(UserController.TAG, "[JRSYS_USER] user init success");
                    if (UserController.this.getUser() != null) {
                        UserController userController2 = UserController.this;
                        userController2.username = userController2.getUser().getNickName();
                        UserController userController3 = UserController.this;
                        userController3.userAvatarURL = userController3.getUser().getAvatarURL();
                        String str2 = UserController.TAG;
                        Log.d(str2, "[JRSYS_USER] user avatar " + UserController.this.userAvatarURL + ",username " + UserController.this.username);
                    }
                    UserController.this.userAPI.setLoginCallback(new LoginCallback() { // from class: com.ecarx.systemui.plugin.statusbar.policy.UserController.1.1
                        @Override // com.ecarx.sdk.user.callback.LoginCallback, com.ecarx.sdk.user.callback.ILoginCallBack
                        public void onLogin() {
                            if (UserController.this.getUser() != null) {
                                UserController.this.username = UserController.this.getUser().getNickName();
                                UserController.this.userAvatarURL = UserController.this.getUser().getAvatarURL();
                                String str3 = UserController.TAG;
                                Log.d(str3, "[JRSYS_USER] user login avatar " + UserController.this.userAvatarURL + ",username " + UserController.this.username);
                                UserController.this.updateUserInfo();
                            }
                        }

                        @Override // com.ecarx.sdk.user.callback.LoginCallback, com.ecarx.sdk.user.callback.ILoginCallBack
                        public void onLogout() {
                            UserController.this.username = null;
                            UserController.this.userAvatarURL = null;
                            Log.d(UserController.TAG, "[JRSYS_USER] user logout");
                            UserController.this.updateUserInfo();
                        }

                        @Override // com.ecarx.sdk.user.callback.LoginCallback, com.ecarx.sdk.user.callback.ILoginCallBack
                        public void onUserCancelLogin() {
                            super.onUserCancelLogin();
                        }

                        @Override // com.ecarx.sdk.user.callback.LoginCallback, com.ecarx.sdk.user.callback.ILoginCallBack
                        public void onTokenRefresh(String str3) {
                            super.onTokenRefresh(str3);
                        }
                    });
                    UserController.this.updateUserInfo();
                    return;
                }
                Log.d(UserController.TAG, "[JRSYS_USER] user died");
            }
        });
        initDataService();
    }

    private void initDataService() {
        try {
            DataServerController dataServerController = DataServerController.getInstance(this.mHostContext);
            this.mDataServerController = dataServerController;
            if (dataServerController != null) {
                Log.w(TAG, "[JRSYS_USER][initDataService]");
                this.mDataServerController.init(new DataServerController.ConnectedCallBack() { // from class: com.ecarx.systemui.plugin.statusbar.policy.UserController.2
                    @Override // com.ecarx.systemui.plugin.navigationbar.DataServerController.ConnectedCallBack
                    public void onConnected(boolean z) {
                        String str = UserController.TAG;
                        Log.w(str, "[JRSYS_USER][initDataService], connected: " + z);
                        if (z) {
                            UserController.this.mDataServerController.addDataListener(DataServerController.SYSTEMUI_PLUGIN, new ImageDataReadyListener());
                            UserController.this.updateUserInfo();
                        }
                    }
                });
            }
        } catch (Throwable th) {
            String str = TAG;
            Log.w(str, "[JRSYS_USER][initDataService], e: " + th.getMessage());
        }
    }

    public IUser getUser() {
        if (this.userInit) {
            IUser loginUser = this.userAPI.getLoginUser();
            String str = TAG;
            Log.d(str, "[JRSYS_USER] get user success " + loginUser);
            if (loginUser != null) {
                this.username = loginUser.getNickName();
                this.userAvatarURL = loginUser.getAvatarURL();
            }
            return loginUser;
        }
        Log.d(TAG, "[JRSYS_USER] get user failed");
        return null;
    }

    public void updateUserInfo() {
        String str;
        String str2 = TAG;
        Log.d(str2, "[JRSYS_USER][updateUserInfo], userInit: " + this.userInit + ", mDataServerController.isIsConnected(): " + this.mDataServerController.isIsConnected());
        if (this.userInit && this.mDataServerController.isIsConnected() && (str = this.userAvatarURL) != null) {
            this.mDataServerController.getUserImageByUrl(str);
            return;
        }
        final ImageView userView = NavigationBar.getInstance(this.mContext).getRootView().getUserView();
        String str3 = TAG;
        Log.d(str3, "[JRSYS_USER][updateUserInfo], targetImageView: " + userView + ", userAvatarURL: " + this.userAvatarURL + ", userName: " + this.username);
        if (userView != null) {
            userView.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.UserController.3
                @Override // java.lang.Runnable
                public void run() {
                    userView.setImageResource(R.drawable.ic_default_user_header);
                }
            });
        }
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
            Lg.d(TAG, "onReceive ACTION_LOCALE_CHANGED");
            updateUserName();
        }
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController
    public void destroy() {
        super.destroy();
        this.mContext.unregisterReceiver(this);
    }

    public void updateUserName() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.UserController.4
            @Override // java.lang.Runnable
            public void run() {
                if (!UserHelper.get().hasLogin()) {
                    Lg.d(UserController.TAG, "[JRSYS] updateUserName not login");
                    UserController.this.setUserName(null);
                    return;
                }
                IUser loginUser = UserHelper.get().getLoginUser();
                String nickName = loginUser != null ? loginUser.getNickName() : null;
                UserController.this.setUserName(nickName);
                String str = UserController.TAG;
                Lg.d(str, "[JRSYS] updateUserName has login, user: " + nickName);
            }
        });
    }

    /* loaded from: classes.dex */
    private class ImageDataReadyListener implements DataServerController.DataReadyCallBack {
        private ImageDataReadyListener() {
        }

        @Override // com.ecarx.systemui.plugin.navigationbar.DataServerController.DataReadyCallBack
        public void onImageReady(final IShortCut iShortCut) {
            final ImageView userView = NavigationBar.getInstance(UserController.this.mContext).getRootView().getUserView();
            String str = UserController.TAG;
            Log.d(str, "[JRSYS_USER][onImageReady]--1, targetImageView: " + userView + ", userAvatarURL: " + UserController.this.userAvatarURL + ", userName: " + UserController.this.username + ", shortCut: " + iShortCut);
            if (userView != null) {
                userView.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.UserController.ImageDataReadyListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        IShortCut iShortCut2 = iShortCut;
                        if (iShortCut2 == null || iShortCut2.getBundle() == null) {
                            userView.setImageResource(R.drawable.ic_default_user_header);
                            return;
                        }
                        Bitmap bitmap = (Bitmap) iShortCut.getBundle().getParcelable(IShortCut.EXTRA_USER_IMAGE);
                        if (bitmap != null) {
                            String str2 = UserController.TAG;
                            Log.w(str2, "[JRSYS_USER][onImageReady]--3, resBitmap: " + bitmap + ", resWidth: " + bitmap.getWidth() + ", resHeight: " + bitmap.getHeight());
                        }
                        userView.setImageBitmap(bitmap);
                        userView.requestLayout();
                    }
                });
            }
        }
    }
}
