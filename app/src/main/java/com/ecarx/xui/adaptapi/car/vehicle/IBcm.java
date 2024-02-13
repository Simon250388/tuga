package com.ecarx.xui.adaptapi.car.vehicle;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IBcm {
    public static final int ANTI_PINCH_DETECTED = 1;
    public static final int ANTI_PINCH_NORMAL = 0;
    public static final int BCM_FUNC_CHARGING_CAP = 553780480;
    public static final int BCM_FUNC_CHILD_SAFETY_LOCK = 553780224;
    public static final int BCM_FUNC_CUSTOM_KEY = 554762496;
    public static final int BCM_FUNC_DISPLAY_ONOFF = 554697216;
    public static final int BCM_FUNC_DOOR = 553779456;
    public static final int BCM_FUNC_DOOR_ANTI_PINCH = 553785600;
    public static final int BCM_FUNC_DOOR_CONTROL = 553783296;
    public static final int BCM_FUNC_DOOR_LOCK = 553779712;
    public static final int BCM_FUNC_DOOR_OBSTACLE_DETECTED = 553785344;
    public static final int BCM_FUNC_DOOR_POS = 553779968;
    public static final int BCM_FUNC_FOLD_REAR_MIRROR = 554041600;
    public static final int BCM_FUNC_FUEL_CAP = 553780736;
    public static final int BCM_FUNC_LIGHT_ALL_WEATHER_LIGHT = 553981440;
    public static final int BCM_FUNC_LIGHT_ATMOSPHERE_LAMPS = 553979904;
    public static final int BCM_FUNC_LIGHT_CORNERING_LAMPS = 553977344;
    public static final int BCM_FUNC_LIGHT_DAYTIME_RUNNING_LAMPS = 553978112;
    public static final int BCM_FUNC_LIGHT_DIM_DIP_LAMPS = 553978368;
    public static final int BCM_FUNC_LIGHT_DIPPED_BEAM = 553976064;
    public static final int BCM_FUNC_LIGHT_DRIVING_LAMPS = 553976576;
    public static final int BCM_FUNC_LIGHT_FRONT_FOG_LAMPS = 553976832;
    public static final int BCM_FUNC_LIGHT_FRONT_POSITION_LAMPS = 553977856;
    public static final int BCM_FUNC_LIGHT_GRILLE_LAMP = 553981184;
    public static final int BCM_FUNC_LIGHT_HAZARD_FLASHERS = 553979648;
    public static final int BCM_FUNC_LIGHT_LEFT_TRUN_SIGNAL = 553980160;
    public static final int BCM_FUNC_LIGHT_MAIN_BEAM = 553976320;
    public static final int BCM_FUNC_LIGHT_READING_LIGHT = 553980672;
    public static final int BCM_FUNC_LIGHT_REAR_FOG_LAMPS = 553977088;
    public static final int BCM_FUNC_LIGHT_REAR_LOGO_LIGHT = 553980928;
    public static final int BCM_FUNC_LIGHT_REAR_POSITION_LAMPS = 553978880;
    public static final int BCM_FUNC_LIGHT_REVERSING_LAMPS = 553979392;
    public static final int BCM_FUNC_LIGHT_RIGHT_TRUN_SIGNAL = 553980416;
    public static final int BCM_FUNC_LIGHT_SIDE_MARKER_LIGHTS = 553978624;
    public static final int BCM_FUNC_LIGHT_SPOT_LIGHTS = 553977600;
    public static final int BCM_FUNC_LIGHT_STOP_LAMPS = 553979136;
    public static final int BCM_FUNC_POWER_ONOFF = 554696960;
    public static final int BCM_FUNC_REAR_MIRROR_ADJUST = 554041856;
    public static final int BCM_FUNC_STEERING_WHEEL_ADJUST = 554107136;
    public static final int BCM_FUNC_SUNROOF_TILT = 553845760;
    public static final int BCM_FUNC_WASHER = 553910528;
    public static final int BCM_FUNC_WINDOW = 553844992;
    public static final int BCM_FUNC_WINDOW_LOCK = 553845248;
    public static final int BCM_FUNC_WINDOW_POS = 553845504;
    public static final int BCM_FUNC_WINDOW_TRANSPARENCY = 553846016;
    public static final int BCM_FUNC_WIPER = 553713920;
    public static final int CUSTOM_KEY_TYPE_360_PANORAMA = 1;
    public static final int CUSTOM_KEY_TYPE_COLLECT_FAV = 5;
    public static final int CUSTOM_KEY_TYPE_DIM_FULL_SCREEN_MAP = 3;
    public static final int CUSTOM_KEY_TYPE_DVR = 0;
    public static final int CUSTOM_KEY_TYPE_NAVIGATION = 2;
    public static final int CUSTOM_KEY_TYPE_SOUND_SWITCH = 4;
    public static final int DOOR_CLOSE = 0;
    public static final int DOOR_OBSTACLE_DETECTED = 1;
    public static final int DOOR_OBSTACLE_NORMAL = 0;
    public static final int DOOR_OPEN = 1;
    public static final int DOOR_PAUSE = 553779457;
    public static final int WINDOW_CLOSE = 0;
    public static final int WINDOW_CLOSE_PAUSE = 553844996;
    public static final int WINDOW_HALF = 553844994;
    public static final int WINDOW_OPEN = 1;
    public static final int WINDOW_OPEN_PAUSE = 553844995;
    public static final int WINDOW_PAUSE = 553844993;
    public static final int WIPER_GEAR_AUTO = 553713921;
    public static final int WIPER_GEAR_HIGHT = 553713923;
    public static final int WIPER_GEAR_INTERMITTENT = 553713924;
    public static final int WIPER_GEAR_LOW = 553713922;
    public static final int WIPER_GEAR_OFF = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AntiPinchDetected {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BcmFunction {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CustomKeyType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DoorControlType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DoorObstacleDetected {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface WindowControlType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface WiperGearMode {
    }
}
