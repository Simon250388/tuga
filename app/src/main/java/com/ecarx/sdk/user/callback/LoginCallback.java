package com.ecarx.sdk.user.callback;

/* loaded from: classes.dex */
public abstract class LoginCallback implements ILoginCallBack {
    @Override // com.ecarx.sdk.user.callback.ILoginCallBack
    public void onLogin() {
    }

    @Override // com.ecarx.sdk.user.callback.ILoginCallBack
    public void onLogout() {
    }

    @Override // com.ecarx.sdk.user.callback.ILoginCallBack
    public void onTokenRefresh(String str) {
    }

    @Override // com.ecarx.sdk.user.callback.ILoginCallBack
    public void onUserCancelLogin() {
    }
}
