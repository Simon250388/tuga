package com.ecarx.systemui.plugin.hvac;

import android.os.Handler;

/* loaded from: classes.dex */
public class HvacLiveDataPolicy {
    public static final int FUNC_FAN_SPEED_CHANGE = 268501507;
    public static final int KEY_HVAC_FUNC_AUTO_ALL = 268501505;
    public static final int KEY_HVAC_FUNC_AUTO_FAN_SETTING_FRONT = 268567041;
    public static final int KEY_HVAC_FUNC_AUTO_FAN_SETTING_REAR = 268567042;
    public static final int KEY_HVAC_FUNC_AUTO_REAR = 268501506;
    public static final int KEY_HVAC_FUNC_AUTO_SEAT_HEATING_TIME_LEFT = 268764673;
    public static final int KEY_HVAC_FUNC_AUTO_SEAT_HEATING_TIME_RIGHT = 268764674;
    public static final int KEY_HVAC_FUNC_AUTO_SEAT_VENTILATION_TIME_LEFT = 268764161;
    public static final int KEY_HVAC_FUNC_AUTO_SEAT_VENTILATION_TIME_RIGHT = 268764162;
    public static final int KEY_HVAC_FUNC_BLOWING_MODE_FRONT = 268894465;
    public static final int KEY_HVAC_FUNC_BLOWING_MODE_REAR = 268894466;
    public static final int KEY_HVAC_FUNC_FAN_SPEED_FRONT = 268566785;
    public static final int KEY_HVAC_FUNC_FAN_SPEED_REAR = 268566786;
    public static final int KEY_HVAC_FUNC_SEAT_HEATING_LEFT = 268763649;
    public static final int KEY_HVAC_FUNC_SEAT_HEATING_REAR_LEFT = 268763651;
    public static final int KEY_HVAC_FUNC_SEAT_HEATING_REAR_RIGHT = 268763652;
    public static final int KEY_HVAC_FUNC_SEAT_HEATING_RIGHT = 268763650;
    public static final int KEY_HVAC_FUNC_SEAT_HEAT_ERROR = 268763653;
    public static final int KEY_HVAC_FUNC_SEAT_MASSAGE_LEFT = 268764929;
    public static final int KEY_HVAC_FUNC_SEAT_MASSAGE_RIGHT = 268764930;
    public static final int KEY_HVAC_FUNC_SEAT_VENTILATION_ERROR = 268763395;
    public static final int KEY_HVAC_FUNC_SEAT_VENTILATION_LEFT = 268763393;
    public static final int KEY_HVAC_FUNC_SEAT_VENTILATION_RIGHT = 268763394;
    public static final int KEY_TEMP_LEFT = 268828929;
    public static final int KEY_TEMP_REAR = 268828931;
    public static final int KEY_TEMP_RIGHT = 268828930;
    public static final int ROW_ONE = 1;
    public static final int ROW_THREE = 2;
    public static final int ROW_TWO = 2;
    private static final String TAG = HvacLiveDataPolicy.class.getSimpleName();
    private Handler handler = new Handler();

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static HvacLiveDataPolicy INSTANCE = new HvacLiveDataPolicy();

        private SingletonHolder() {
        }
    }

    public static HvacLiveDataPolicy getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
