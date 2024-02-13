package com.ecarx.sdk.user;

import android.content.Context;
import android.util.Log;
import com.ecarx.sdk.ECarXAPIBase;
import com.ecarx.sdk.user.callback.LoginCallback;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;

/* loaded from: classes.dex */
public abstract class UserAPI extends ECarXAPIBase {
    private static final String TAG = "UserAPI";
    private static UserAPI mInstance;

    public abstract String getAccessToken();

    public abstract IUser getLoginUser();

    public abstract String getRefreshToken();

    @Deprecated
    public abstract String getToken();

    public abstract boolean hasLogin();

    public abstract boolean launchLogin();

    public abstract boolean launchLogin(String str, String str2, boolean z);

    public abstract boolean notifyRefreshTokenExpired();

    public abstract boolean notifyTokenExpired();

    public abstract boolean setLoginCallback(LoginCallback loginCallback);

    public static UserAPI get(Context context) {
        if (mInstance == null) {
            synchronized (UserAPI.class) {
                if (mInstance == null) {
                    mInstance = createUserAPI(context);
                }
            }
        }
        return mInstance;
    }

    public static UserAPI createUserAPI(Context context) {
        if (context == null) {
            throw new InvalidParameterException("param Context is null!");
        }
        Log.i(TAG, "createUserAPI");
        try {
            return (UserAPI) Class.forName("com.ecarx.sdk.user.UserAPIImpl").getConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "createUserAPI failed");
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            Log.e(TAG, "createUserAPI failed");
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            Log.e(TAG, "createUserAPI failed");
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            Log.e(TAG, "createUserAPI failed");
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            Log.e(TAG, "createUserAPI failed");
            return null;
        }
    }
}
