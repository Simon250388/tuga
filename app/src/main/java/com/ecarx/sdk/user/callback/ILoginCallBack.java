package com.ecarx.sdk.user.callback;

/* loaded from: classes.dex */
public interface ILoginCallBack {
    void onLogin();

    void onLogout();

    void onTokenRefresh(String str);

    void onUserCancelLogin();
}
