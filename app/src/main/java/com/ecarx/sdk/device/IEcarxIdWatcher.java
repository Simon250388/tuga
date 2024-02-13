package com.ecarx.sdk.device;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IEcarxIdWatcher {
    public static final String ACTION_GKUI_ECARXID_CHANGED = "ecarx.intent.action.ECARXID_CHANGED";
    public static final int CODE_ERROR_INTERNAL = 500;
    public static final int CODE_ERROR_PARAMS = 400;
    public static final int CODE_SUCCESS = 200;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Code {
    }

    void onChanged(String str, int i);
}
