package com.ecarx.xui.adaptapi.ota;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IOtaSessionCallback {
    public static final int UPDATE_FAILED_CHARGE_CONNECTED = 15;
    public static final int UPDATE_FAILED_CONFIG_ERROR = 6;
    public static final int UPDATE_FAILED_CRITICAL_CONFIGURATION_MISMATCH_OR_OTHER = 14;
    public static final int UPDATE_FAILED_DOOR_LOCKING = 8;
    public static final int UPDATE_FAILED_LOW_BATTERY = 4;
    public static final int UPDATE_FAILED_MEMORY_ERROR = 10;
    public static final int UPDATE_FAILED_NETWORK_ERROR = 3;
    public static final int UPDATE_FAILED_REASON_DEFAULT = 0;
    public static final int UPDATE_FAILED_REASON_INSUFFICIENT_STORAGE = 2;
    public static final int UPDATE_FAILED_REASON_INVALID_PACKAGE = 1;
    public static final int UPDATE_FAILED_SERVICE_ERROR = 9;
    public static final int UPDATE_FAILED_SYSTEM = 18;
    public static final int UPDATE_FAILED_TEMPERATURE_LOW = 16;
    public static final int UPDATE_FAILED_TIME_OUT = 5;
    public static final int UPDATE_FAILED_UPDATING_ERROR = 11;
    public static final int UPDATE_FAILED_VEHICLE_IN_USE = 12;
    public static final int UPDATE_FAILED_VEHICLE_NOT_SECURED = 13;
    public static final int UPDATE_FAILED_WINDOW = 17;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface UpdateFailedReason {
    }

    void onFailed(int i);

    void onProgressUpdate(int i);

    void onRebootingAfterOta();

    void onSessionCanceled();

    void onShouldBeginInstall();

    void onSucceeded();
}
