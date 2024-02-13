package com.ecarx.xui.adaptapi.car.hvac;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IHvac {
    public static final int AIR_FRAGRANCE_JASMINE = 269156870;
    public static final int AIR_FRAGRANCE_LAVENDER = 269156867;
    public static final int AIR_FRAGRANCE_LEVEL_1 = 269157121;
    public static final int AIR_FRAGRANCE_LEVEL_2 = 269157122;
    public static final int AIR_FRAGRANCE_LEVEL_3 = 269157123;
    public static final int AIR_FRAGRANCE_LEVEL_OFF = 0;
    public static final int AIR_FRAGRANCE_LILY = 269156866;
    public static final int AIR_FRAGRANCE_LONGJING = 269156868;
    public static final int AIR_FRAGRANCE_OFF = 0;
    public static final int AIR_FRAGRANCE_ROSE = 269156865;
    public static final int AIR_FRAGRANCE_SANDALWOOD = 269156869;
    public static final int AUTO_FAN_SETTING_HIGH = 268567043;
    public static final int AUTO_FAN_SETTING_HIGHER = 268567045;
    public static final int AUTO_FAN_SETTING_NORMAL = 268567042;
    public static final int AUTO_FAN_SETTING_QUIETER = 268567044;
    public static final int AUTO_FAN_SETTING_SILENT = 268567041;
    public static final int AUTO_SEAT_HEATING_LEVEL_1 = 268764417;
    public static final int AUTO_SEAT_HEATING_LEVEL_2 = 268764418;
    public static final int AUTO_SEAT_HEATING_LEVEL_3 = 268764419;
    public static final int AUTO_SEAT_HEATING_OFF = 0;
    public static final int AUTO_SEAT_HEATING_TIME_1 = 268764673;
    public static final int AUTO_SEAT_HEATING_TIME_2 = 268764674;
    public static final int AUTO_SEAT_HEATING_TIME_3 = 268764675;
    public static final int AUTO_SEAT_HEATING_TIME_OFF = 0;
    public static final int AUTO_SEAT_MASSAGE_LEVEL_1 = 268765185;
    public static final int AUTO_SEAT_MASSAGE_LEVEL_2 = 268765186;
    public static final int AUTO_SEAT_MASSAGE_LEVEL_3 = 268765187;
    public static final int AUTO_SEAT_MASSAGE_OFF = 0;
    public static final int AUTO_SEAT_MASSAGE_TIME_1 = 268765441;
    public static final int AUTO_SEAT_MASSAGE_TIME_2 = 268765442;
    public static final int AUTO_SEAT_MASSAGE_TIME_3 = 268765443;
    public static final int AUTO_SEAT_MASSAGE_TIME_OFF = 0;
    public static final int AUTO_SEAT_VENTILATION_LEVEL_1 = 268763905;
    public static final int AUTO_SEAT_VENTILATION_LEVEL_2 = 268763906;
    public static final int AUTO_SEAT_VENTILATION_LEVEL_3 = 268763907;
    public static final int AUTO_SEAT_VENTILATION_OFF = 0;
    public static final int AUTO_SEAT_VENTILATION_TIME_1 = 268764161;
    public static final int AUTO_SEAT_VENTILATION_TIME_2 = 268764162;
    public static final int AUTO_SEAT_VENTILATION_TIME_3 = 268764163;
    public static final int AUTO_SEAT_VENTILATION_TIME_OFF = 0;
    public static final int AUTO_STEERING_WHEEL_HEAT_HIGH = 269025795;
    public static final int AUTO_STEERING_WHEEL_HEAT_LOW = 269025793;
    public static final int AUTO_STEERING_WHEEL_HEAT_MID = 269025794;
    public static final int AUTO_STEERING_WHEEL_HEAT_OFF = 0;
    public static final int AUTO_STEERING_WHEEL_HEAT_TIME_1 = 269026049;
    public static final int AUTO_STEERING_WHEEL_HEAT_TIME_2 = 269026050;
    public static final int AUTO_STEERING_WHEEL_HEAT_TIME_3 = 269026051;
    public static final int AUTO_STEERING_WHEEL_HEAT_TIME_OFF = 0;
    public static final int BLOWING_ALL = 268894471;
    public static final int BLOWING_MODE_AUTO_SWITCH = 268894472;
    public static final int BLOWING_MODE_FACE = 268894465;
    public static final int BLOWING_MODE_FACE_AND_FRONT_WINDOW = 268894469;
    public static final int BLOWING_MODE_FACE_AND_LEG = 268894467;
    public static final int BLOWING_MODE_FRONT_WINDOW = 268894468;
    public static final int BLOWING_MODE_LEG = 268894466;
    public static final int BLOWING_MODE_LEG_AND_FRONT_WINDOW = 268894470;
    public static final int BLOWING_MODE_OFF = 0;
    public static final int CIRCULATION_AUTO = 268632323;
    public static final int CIRCULATION_INNER = 268632321;
    public static final int CIRCULATION_OFF = 0;
    public static final int CIRCULATION_OUTSIDE = 268632322;
    public static final int CLIMATE_ZONE_DUAL = 268502274;
    public static final int CLIMATE_ZONE_FOUR = 268502276;
    public static final int CLIMATE_ZONE_SINGLE = 268502273;
    public static final int CLIMATE_ZONE_TRIPLE = 268502275;
    public static final int DIRECTION_MODE_AVOID = 268894978;
    public static final int DIRECTION_MODE_CUSTOM = 268894723;
    public static final int DIRECTION_MODE_FOCUS = 268894977;
    public static final int DIRECTION_MODE_OFF = 0;
    public static final int DISPLAY_WINDOW_TAB_DEFAULT = 269484801;
    public static final int DISPLAY_WINDOW_TAB_HARDWARE_POP = 269484804;
    public static final int DISPLAY_WINDOW_TAB_IONS_POP = 269484806;
    public static final int DISPLAY_WINDOW_TAB_LEFT_TEMP = 269484802;
    public static final int DISPLAY_WINDOW_TAB_NONE = 0;
    public static final int DISPLAY_WINDOW_TAB_RIGHT_TEMP = 269484803;
    public static final int DISPLAY_WINDOW_TAB_SEAT = 269484805;
    public static final int FAN_SPEED_LEVEL_1 = 268566785;
    public static final int FAN_SPEED_LEVEL_2 = 268566786;
    public static final int FAN_SPEED_LEVEL_3 = 268566787;
    public static final int FAN_SPEED_LEVEL_4 = 268566788;
    public static final int FAN_SPEED_LEVEL_5 = 268566789;
    public static final int FAN_SPEED_LEVEL_6 = 268566790;
    public static final int FAN_SPEED_LEVEL_7 = 268566791;
    public static final int FAN_SPEED_LEVEL_8 = 268566792;
    public static final int FAN_SPEED_LEVEL_9 = 268566793;
    public static final int FAN_SPEED_LEVEL_AUTO = 268566794;
    public static final int FAN_SPEED_OFF = 0;
    public static final int HVAC_FUNC_AC = 268501760;
    public static final int HVAC_FUNC_AC_MAX = 268502016;
    public static final int HVAC_FUNC_AIR_FRAGRANCE = 269156608;
    public static final int HVAC_FUNC_AIR_FRAGRANCE_LEVEL = 269157120;
    public static final int HVAC_FUNC_AIR_FRAGRANCE_TYPE = 269156864;
    public static final int HVAC_FUNC_AQS_SWITCH = 268960256;
    public static final int HVAC_FUNC_AUTO = 268501504;
    public static final int HVAC_FUNC_AUTOMATIC_VENTILATION_DRY = 269485312;
    public static final int HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND = 269418752;
    public static final int HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND_CONFIRM = 269419264;
    public static final int HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND_REQUEST = 269419008;
    public static final int HVAC_FUNC_AUTO_CZIS = 269485568;
    public static final int HVAC_FUNC_AUTO_DEFROST_CONFIRM = 268699392;
    public static final int HVAC_FUNC_AUTO_DEFROST_FRONT = 268698880;
    public static final int HVAC_FUNC_AUTO_DEFROST_REAR = 268698624;
    public static final int HVAC_FUNC_AUTO_DEFROST_REQUEST = 268699136;
    public static final int HVAC_FUNC_AUTO_DEHUMIDIFICATION = 268960512;
    public static final int HVAC_FUNC_AUTO_DEHUMIDIFICATION_CONFIRM = 269287936;
    public static final int HVAC_FUNC_AUTO_DEHUMIDIFICATION_REQUEST = 269287680;
    public static final int HVAC_FUNC_AUTO_FAN_SETTING = 268567040;
    public static final int HVAC_FUNC_AUTO_ION = 269222144;
    public static final int HVAC_FUNC_AUTO_ION_CONFIRM = 269222656;
    public static final int HVAC_FUNC_AUTO_ION_REQUEST = 269222400;
    public static final int HVAC_FUNC_AUTO_SEAT_HEATING = 268764416;
    public static final int HVAC_FUNC_AUTO_SEAT_HEATING_TIME = 268764672;
    public static final int HVAC_FUNC_AUTO_SEAT_MASSAGE = 268765184;
    public static final int HVAC_FUNC_AUTO_SEAT_MASSAGE_TIME = 268765440;
    public static final int HVAC_FUNC_AUTO_SEAT_VENTILATION = 268763904;
    public static final int HVAC_FUNC_AUTO_SEAT_VENTILATION_TIME = 268764160;
    public static final int HVAC_FUNC_AUTO_SECOND_ROW_CLIMATE = 269484288;
    public static final int HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT = 269025792;
    public static final int HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT_SWITCH = 269026304;
    public static final int HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT_TIME = 269026048;
    public static final int HVAC_FUNC_BLOWING_MODE = 268894464;
    public static final int HVAC_FUNC_BLOWING_TEMP_COLOR = 268895744;
    public static final int HVAC_FUNC_CIRCULATION = 268632320;
    public static final int HVAC_FUNC_CIRCULATION_LONG_TOUCH = 268632832;
    public static final int HVAC_FUNC_CIRCULATION_TIMER = 268632576;
    public static final int HVAC_FUNC_CLIMATE_HARDKEY_SOUND = 269486080;
    public static final int HVAC_FUNC_CLIMATE_LOCK = 269484544;
    public static final int HVAC_FUNC_CLIMATE_ZONE = 268502272;
    public static final int HVAC_FUNC_CLIMATISATION_ERROR_CONDITIONS = 269091584;
    public static final int HVAC_FUNC_CO2_HIGHER_CONFIRM = 269353728;
    public static final int HVAC_FUNC_CO2_HIGHER_REQUEST = 269353472;
    public static final int HVAC_FUNC_CO2_SWITCH = 269353216;
    public static final int HVAC_FUNC_DEFROST_FRONT = 268697856;
    public static final int HVAC_FUNC_DEFROST_FRONT_MAX = 268698112;
    public static final int HVAC_FUNC_DEFROST_REAR = 268698368;
    public static final int HVAC_FUNC_DIRECTION_MODE = 268894976;
    public static final int HVAC_FUNC_DISPLAY_WINDOW_TAB = 269484800;
    public static final int HVAC_FUNC_ECO_SWITCH = 268960000;
    public static final int HVAC_FUNC_FAN_SPEED = 268566784;
    public static final int HVAC_FUNC_G_CLEAN = 269485056;
    public static final int HVAC_FUNC_INTELLIGENT_RECOMMENDATION = 269615360;
    public static final int HVAC_FUNC_IONS_SWITCH = 268961024;
    public static final int HVAC_FUNC_MODULE_CONNECT_STATUS = 269680896;
    public static final int HVAC_FUNC_OVERHEAT_PROTECTION = 268960768;
    public static final int HVAC_FUNC_POST_CLIMATISATION = 269091328;
    public static final int HVAC_FUNC_POWER = 268501248;
    public static final int HVAC_FUNC_PRE_CLIMATISATION = 269091072;
    public static final int HVAC_FUNC_SEAT_HEATING = 268763648;
    public static final int HVAC_FUNC_SEAT_MASSAGE = 268764928;
    public static final int HVAC_FUNC_SEAT_MASSAGE_PROGRAM = 268765952;
    public static final int HVAC_FUNC_SEAT_MASSAGE_SWITCH = 268765696;
    public static final int HVAC_FUNC_SEAT_VENTILATION = 268763392;
    public static final int HVAC_FUNC_STEERING_WHEEL_HEAT = 269025536;
    public static final int HVAC_FUNC_SWEEPING_HORIZONTAL_POS = 268895232;
    public static final int HVAC_FUNC_SWEEPING_MODE = 268894720;
    public static final int HVAC_FUNC_SWEEPING_VERTICAL_POS = 268895488;
    public static final int HVAC_FUNC_TEMP = 268828928;
    public static final int HVAC_FUNC_TEMP_DUAL = 268829952;
    public static final int HVAC_FUNC_TEMP_MAX = 268829184;
    public static final int HVAC_FUNC_TEMP_MIN = 268829440;
    public static final int HVAC_FUNC_TEMP_OPTIMIZE = 269615616;
    public static final int HVAC_FUNC_TEMP_STEP = 268829696;
    public static final int HVAC_FUNC_TEMP_UNIT = 268830208;
    public static final int HVAC_FUNC_VENTILATION_ONTIME = 269485824;
    public static final int SEAT_HEATING_LEVEL_1 = 268763649;
    public static final int SEAT_HEATING_LEVEL_2 = 268763650;
    public static final int SEAT_HEATING_LEVEL_3 = 268763651;
    public static final int SEAT_HEATING_LEVEL_AUTO = 268763663;
    public static final int SEAT_HEATING_OFF = 0;
    public static final int SEAT_MASSAGE_LEVEL_1 = 268764929;
    public static final int SEAT_MASSAGE_LEVEL_2 = 268764930;
    public static final int SEAT_MASSAGE_LEVEL_3 = 268764931;
    public static final int SEAT_MASSAGE_LEVEL_AUTO = 268764943;
    public static final int SEAT_MASSAGE_OFF = 0;
    public static final int SEAT_MASSAGE_PROGRAM_1 = 268765953;
    public static final int SEAT_MASSAGE_PROGRAM_2 = 268765954;
    public static final int SEAT_MASSAGE_PROGRAM_3 = 268765955;
    public static final int SEAT_MASSAGE_PROGRAM_4 = 268765956;
    public static final int SEAT_MASSAGE_PROGRAM_5 = 268765957;
    public static final int SEAT_MASSAGE_PROGRAM_6 = 268765958;
    public static final int SEAT_MASSAGE_PROGRAM_7 = 268765959;
    public static final int SEAT_MASSAGE_PROGRAM_8 = 268765960;
    public static final int SEAT_MASSAGE_PROGRAM_9 = 268765961;
    public static final int SEAT_MASSAGE_PROGRAM_A = 268765962;
    public static final int SEAT_MASSAGE_PROGRAM_OFF = 0;
    public static final int SEAT_VENTILATION_LEVEL_1 = 268763393;
    public static final int SEAT_VENTILATION_LEVEL_2 = 268763394;
    public static final int SEAT_VENTILATION_LEVEL_3 = 268763395;
    public static final int SEAT_VENTILATION_LEVEL_AUTO = 268763407;
    public static final int SEAT_VENTILATION_OFF = 0;
    public static final int STEERING_WHEEL_HEAT_AUTO = 269025551;
    public static final int STEERING_WHEEL_HEAT_HIGH = 269025539;
    public static final int STEERING_WHEEL_HEAT_LOW = 269025537;
    public static final int STEERING_WHEEL_HEAT_MID = 269025538;
    public static final int STEERING_WHEEL_HEAT_OFF = 0;
    public static final int SWEEPING_MODE_LEFT_RIGHT = 268894721;
    public static final int SWEEPING_MODE_LR_AND_UD = 268894723;
    public static final int SWEEPING_MODE_OFF = 0;
    public static final int SWEEPING_MODE_UP_DOWN = 268894722;
    public static final int TEMPERATURE_UNIT_C = 268830209;
    public static final int TEMPERATURE_UNIT_F = 268830210;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AirFragranceLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AirFragranceType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoFanSetting {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoSeatHeatingLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoSeatHeatingTime {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoSeatMassageLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoSeatMassageTime {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoSeatVentilationLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoSeatVentilationTime {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoSteeringWheelHeatLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoSteeringWheelHeatTime {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BlowingMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CirculationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ClimateZone {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DirectionMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DisplayWindowTab {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FanSpeedLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface HvacFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatHeatingLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatMassageLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatMassageProgram {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatVentilationLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SteeringWheelHeatLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SweepingMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TemperatureUnit {
    }
}
