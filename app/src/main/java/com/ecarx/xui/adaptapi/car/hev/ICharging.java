package com.ecarx.xui.adaptapi.car.hev;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;

/* loaded from: classes.dex */
public interface ICharging {
    public static final int CHARGE_FUNC_ADJUST_MAX_CURRENT = 605031168;
    public static final int CHARGE_FUNC_AUTO_DISCHARGE_LEVEL = 605161216;
    public static final int CHARGE_FUNC_BATTERY_STABILITY = 605031680;
    public static final int CHARGE_FUNC_CHARGING = 605028608;
    public static final int CHARGE_FUNC_CHARGING_CURRENT = 605029888;
    public static final int CHARGE_FUNC_CHARGING_CURRENT_MAX = 605030144;
    public static final int CHARGE_FUNC_CHARGING_CURRENT_MIN = 605030400;
    public static final int CHARGE_FUNC_CHARGING_CURRENT_STEP = 605030656;
    public static final int CHARGE_FUNC_CHARGING_ENERGY = 605291776;
    public static final int CHARGE_FUNC_CHARGING_ESTIMATED_TIME = 605291264;
    public static final int CHARGE_FUNC_CHARGING_PLUG_STATE = 605225472;
    public static final int CHARGE_FUNC_CHARGING_PLUG_TYPE = 605225216;
    public static final int CHARGE_FUNC_CHARGING_SOC = 605028864;
    public static final int CHARGE_FUNC_CHARGING_SOC_MAX = 605029120;
    public static final int CHARGE_FUNC_CHARGING_SOC_MIN = 605029376;
    public static final int CHARGE_FUNC_CHARGING_SOC_STEP = 605029632;
    public static final int CHARGE_FUNC_CHARGING_SPEED = 605291520;
    public static final int CHARGE_FUNC_CHARGING_STATE = 605225728;
    public static final int CHARGE_FUNC_CHARGING_WORK_CURRENT = 605291008;
    public static final int CHARGE_FUNC_CHARGING_WORK_VOLTAGE = 605290752;
    public static final int CHARGE_FUNC_DISCHARGING_ENETGY = 605357056;
    public static final int CHARGE_FUNC_DISCHARGING_ESTIMATED_TIME = 605356800;
    public static final int CHARGE_FUNC_DISCHARGING_SOC = 605160192;
    public static final int CHARGE_FUNC_DISCHARGING_SOC_MAX = 605160448;
    public static final int CHARGE_FUNC_DISCHARGING_SOC_MIN = 605160704;
    public static final int CHARGE_FUNC_DISCHARGING_SOC_STEP = 605160960;
    public static final int CHARGE_FUNC_DISCHARGING_SWITCH_V2L = 605159936;
    public static final int CHARGE_FUNC_DISCHARGING_SWITCH_V2V = 605159680;
    public static final int CHARGE_FUNC_DISCHARGING_WORK_CURRENT = 605356544;
    public static final int CHARGE_FUNC_DISCHARGING_WORK_VOLTAGE = 605356288;
    public static final int CHARGE_FUNC_EXTENDED_BATTERY_LIFE = 605031424;
    public static final int CHARGE_FUNC_EXTERNAL_CHARGING_LIGHT = 605031936;
    public static final int CHARGE_FUNC_MAINTAIN_BATTERY_TEMP = 605030912;
    public static final int CHARGE_FUNC_PHEV_BATTERY_CHARGING_MODE = 605357568;
    public static final int CHARGE_FUNC_PHEV_RARELY_CHARGING_MODE = 605357824;
    public static final int CHARGE_FUNC_PRE_CHARGING = 605094144;
    public static final int CHARGE_FUNC_PRE_CHARGING_CURRENT = 605094656;
    public static final int CHARGE_FUNC_PRE_CHARGING_SOC = 605094400;
    public static final int CHARGE_FUNC_PRE_CHARGING_STATUS = 605094912;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING = 606077184;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_FRI = 606082304;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_MON = 606081280;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_SAT = 606082560;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_SUN = 606082816;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_THUR = 606082048;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_TUE = 606081536;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_WED = 606081792;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_MODE = 606077440;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_ONCE = 606077696;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_PEAK_VALLEY_PERIOD = 606085120;
    public static final int CHARGE_PLUG_AC = 605225217;
    public static final int CHARGE_PLUG_DC = 605225218;
    public static final int CHARGE_PLUG_DISCHARGE = 605225219;
    public static final int CHARGE_PLUG_STATE_CHARGING = 605225474;
    public static final int CHARGE_PLUG_STATE_CHARGING_PAUSE = 605225483;
    public static final int CHARGE_PLUG_STATE_COMPLETED = 605225475;
    public static final int CHARGE_PLUG_STATE_CONNECTED = 605225481;
    public static final int CHARGE_PLUG_STATE_DISCHARGING = 605225478;
    public static final int CHARGE_PLUG_STATE_DISCHARGING_COMPLETED = 605225479;
    public static final int CHARGE_PLUG_STATE_DISCHARGING_PAUSE = 605225484;
    public static final int CHARGE_PLUG_STATE_DISCONNECTED = 605225482;
    public static final int CHARGE_PLUG_STATE_ERROR = 605225477;
    public static final int CHARGE_PLUG_STATE_HEATING = 605225480;
    public static final int CHARGE_PLUG_STATE_MULTI = 605225476;
    public static final int CHARGE_PLUG_STATE_PREPARED = 605225473;
    public static final int CHARGE_PLUG_STATE_RESERVE_WAITING = 605225485;
    public static final int CHARGE_PLUG_STATE_UNKNOWN = 255;
    public static final int CHARGE_PLUG_UNKNOWN = 255;
    public static final int PHEV_CHARGE_MODE_ACTIVE = 605357569;
    public static final int PHEV_CHARGE_MODE_OFF = 605357571;
    public static final int PHEV_CHARGE_MODE_SOC_HOLD = 605357570;
    public static final int PRE_CHARGING_STATUS_CANCELED = 605094918;
    public static final int PRE_CHARGING_STATUS_CANCEL_FAILED = 605094919;
    public static final int PRE_CHARGING_STATUS_CHARGING = 605094917;
    public static final int PRE_CHARGING_STATUS_FAILED = 605094914;
    public static final int PRE_CHARGING_STATUS_FAILURE = 605094915;
    public static final int PRE_CHARGING_STATUS_SCHEDULING = 605094916;
    public static final int PRE_CHARGING_STATUS_SUCCEED = 605094913;
    public static final int PRE_CHARGING_STATUS_UNKNOWN = 255;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_ALLWEEK = 606077443;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_CUSTOM = 606077444;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_ONCE = 606077441;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_UNKNOWN = 255;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_WEEKDATS = 606077442;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ChargingFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ChargingPlugState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ChargingPlugType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ChargingTimeSettingType {
    }

    /* loaded from: classes.dex */
    public interface IChargingListener {
        void onPreChargingTimeChanged(Calendar[] calendarArr);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PHEVPowerBatteryMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PreChargingStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TravelAppointChargingMode {
    }

    Calendar[] getChargingTimeSetting(int i);

    Calendar[] getHistoricalDischargeCapacityTime();

    Float[] getHistoricalDischargeCapacityValue();

    Calendar[] getPreChargingTime();

    void registerListener(IChargingListener iChargingListener);

    boolean setChargingTimeSetting(int i, Calendar[] calendarArr);

    boolean setPreChargingTime(Calendar calendar, Calendar calendar2);

    void unregisterListener(IChargingListener iChargingListener);
}
