package com.ecarx.sdk;

import android.content.Context;
import com.ecarx.sdk.log.ILog;
import com.ecarx.sdk.log.LogProxy;
import com.ecarx.sdk.openapi.ECarXApiClient;

/* loaded from: classes.dex */
public class ECarXAPIBase {
    protected static final int ERROR_CODE = -1;
    public static final int SUPPORT_AUTH_VERSION = 330;
    public static final String VERSION = "3.3.3";
    public static final int VERSION_INT = 333;
    protected LogProxy L = new LogProxy();

    public void init(Context context, ECarXApiClient.Callback callback) {
    }

    public void release() {
    }

    public final String getVersion() {
        return VERSION;
    }

    public final int getVersionInt() {
        return VERSION_INT;
    }

    public void setLogEnable(boolean z) {
        this.L.setLogEnable(z);
    }

    public void setLogLevel(int i) {
        this.L.setLogLevel(i);
    }

    public void setLogImpl(ILog iLog) {
        this.L.setLogImpl(iLog);
    }
}
