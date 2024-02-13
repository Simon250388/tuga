package com.ecarx.xui.adaptapi.wpc;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.FunctionStatus;

@Deprecated
/* loaded from: classes.dex */
public abstract class WPC extends AdaptAPI {
    public static final int CHARGING_STATUS_CHARGING = 2;
    public static final int CHARGING_STATUS_ERROR = 5;
    public static final int CHARGING_STATUS_FOD = 9;
    public static final int CHARGING_STATUS_FULLY_CHARGED = 3;
    public static final int CHARGING_STATUS_INTERRUPT_PEPS = 10;
    public static final int CHARGING_STATUS_NO_DEVICE = 1;
    public static final int CHARGING_STATUS_OFF = -2147483647;
    public static final int CHARGING_STATUS_OVERHEAT_OR_FOD = 4;
    public static final int CHARGING_STATUS_OVERHEAT_PROTECTED = 8;
    public static final int CHARGING_STATUS_STANDBY = 7;
    public static final int CHARGING_STATUS_TAKE_MOBILE_DEVICE = 6;
    public static final int WORKING_MODE_AUTO = 2;
    public static final int WORKING_MODE_NONE = 0;
    public static final int WORKING_MODE_OFF = 1;

    /* loaded from: classes.dex */
    @interface ChargingStatus {
    }

    /* loaded from: classes.dex */
    public interface StateListener {
        void onChargingStatus(int i);

        void onWorkingMode(int i);
    }

    /* loaded from: classes.dex */
    @interface WorkingMode {
    }

    public static WPC create(Context context) {
        return null;
    }

    public abstract int getChargingStatus();

    public abstract int getWorkingMode();

    public abstract FunctionStatus isWPCSupported();

    public abstract void setStateListener(StateListener stateListener);

    public abstract int setWorkingMode(int i);

    public abstract void unsetStateListener(StateListener stateListener);
}
