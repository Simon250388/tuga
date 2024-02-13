package com.ecarx.xui.adaptapi.car.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IDriveMode {
    public static final int CUSTOM_BPF_OFF = 0;
    public static final int CUSTOM_BPF_SPORT = 570622978;
    public static final int CUSTOM_BPF_STANDARD = 570622977;
    public static final int CUSTOM_CLIMATE_MODE_ECO = 570624514;
    public static final int CUSTOM_CLIMATE_MODE_NORMAL = 570624513;
    public static final int CUSTOM_CLIMATE_MODE_OFF = 0;
    public static final int CUSTOM_DRIVER_INFO_ECO = 570625026;
    public static final int CUSTOM_DRIVER_INFO_OFF = 0;
    public static final int CUSTOM_DRIVER_INFO_OFFROAD = 570625028;
    public static final int CUSTOM_DRIVER_INFO_SPORT = 570625027;
    public static final int CUSTOM_DRIVER_INFO_STANDARD = 570625025;
    public static final int CUSTOM_INFOR_THEME_CLEAR = 570624772;
    public static final int CUSTOM_INFOR_THEME_HYPER = 570624770;
    public static final int CUSTOM_INFOR_THEME_INTER = 570624771;
    public static final int CUSTOM_INFOR_THEME_LOUDER = 570624769;
    public static final int CUSTOM_INFOR_THEME_OFF = 0;
    public static final int CUSTOM_INTERIOR_LIGHT_COMFORT = 570625282;
    public static final int CUSTOM_INTERIOR_LIGHT_ECO = 570625281;
    public static final int CUSTOM_INTERIOR_LIGHT_HYBRID = 570625287;
    public static final int CUSTOM_INTERIOR_LIGHT_OFF = 0;
    public static final int CUSTOM_INTERIOR_LIGHT_OFFROAD = 570625284;
    public static final int CUSTOM_INTERIOR_LIGHT_PURE = 570625288;
    public static final int CUSTOM_INTERIOR_LIGHT_SAND = 570625286;
    public static final int CUSTOM_INTERIOR_LIGHT_SNOW = 570625285;
    public static final int CUSTOM_INTERIOR_LIGHT_SPORT = 570625283;
    public static final int CUSTOM_INTERIOR_LIGHT_STANDARD = 570625289;
    public static final int CUSTOM_PROPULSION_SYS_AWD = 570622218;
    public static final int CUSTOM_PROPULSION_SYS_COMFORT = 570622210;
    public static final int CUSTOM_PROPULSION_SYS_ECO = 570622209;
    public static final int CUSTOM_PROPULSION_SYS_HYBRID = 570622215;
    public static final int CUSTOM_PROPULSION_SYS_OFF = 0;
    public static final int CUSTOM_PROPULSION_SYS_OFFROAD = 570622212;
    public static final int CUSTOM_PROPULSION_SYS_POWER = 570622217;
    public static final int CUSTOM_PROPULSION_SYS_PURE = 570622216;
    public static final int CUSTOM_PROPULSION_SYS_SAND = 570622214;
    public static final int CUSTOM_PROPULSION_SYS_SNOW = 570622213;
    public static final int CUSTOM_PROPULSION_SYS_SPORT = 570622211;
    public static final int CUSTOM_RAB_OFF = 0;
    public static final int CUSTOM_RAB_SPORT = 570622722;
    public static final int CUSTOM_RAB_STANDARD = 570622721;
    public static final int CUSTOM_STEERING_WHEEL_FEEL_BALANCED = 570624258;
    public static final int CUSTOM_STEERING_WHEEL_FEEL_HEAVY = 570624259;
    public static final int CUSTOM_STEERING_WHEEL_FEEL_LIGHT = 570624257;
    public static final int CUSTOM_STEERING_WHEEL_FEEL_OFF = 0;
    public static final int CUSTOM_SUSPENSION_MODE_AUTOMATIC = 570622470;
    public static final int CUSTOM_SUSPENSION_MODE_COMFORT = 570622466;
    public static final int CUSTOM_SUSPENSION_MODE_OFF = 0;
    public static final int CUSTOM_SUSPENSION_MODE_OFFROAD = 570622468;
    public static final int CUSTOM_SUSPENSION_MODE_SNOW = 570622469;
    public static final int CUSTOM_SUSPENSION_MODE_SPORT = 570622467;
    public static final int CUSTOM_SUSPENSION_MODE_STANDARD = 570622465;
    public static final int DIM_THEME_SET_BLUE = 570688003;
    public static final int DIM_THEME_SET_GOLD = 570688002;
    public static final int DIM_THEME_SET_OFF = 0;
    public static final int DIM_THEME_SET_RED = 570688001;
    public static final int DM_FUNC_DIM_THEME_SET = 570688000;
    public static final int DM_FUNC_DIM_THEME_SYNC_DRIVEMODE = 570687744;
    public static final int DM_FUNC_DM_CUSTOM_BPF = 570622976;
    public static final int DM_FUNC_DM_CUSTOM_CLIMATE_MODE = 570624512;
    public static final int DM_FUNC_DM_CUSTOM_DRIVER_INFO = 570625024;
    public static final int DM_FUNC_DM_CUSTOM_EN_START_STOP = 570625536;
    public static final int DM_FUNC_DM_CUSTOM_INFOR_THEME = 570624768;
    public static final int DM_FUNC_DM_CUSTOM_INTERIOR_LIGHT = 570625280;
    public static final int DM_FUNC_DM_CUSTOM_PROPULSION_SYS = 570622208;
    public static final int DM_FUNC_DM_CUSTOM_RAB = 570622720;
    public static final int DM_FUNC_DM_CUSTOM_STEERING_WHEEL_FEEL = 570624256;
    public static final int DM_FUNC_DM_CUSTOM_SUSPENSION_MODE = 570622464;
    public static final int DM_FUNC_DRIVE_MODE_SELECT = 570491136;
    public static final int DM_FUNC_ECO_BUTTON = 570556672;
    public static final int DM_FUNC_STEERING_WHEEL_FEEL_SYNC_DRIVEMODE = 570688256;
    public static final int DRIVE_MODE_ECO_PLUS = 570491156;
    public static final int DRIVE_MODE_SELECTION_ADAPTIVE = 570491158;
    public static final int DRIVE_MODE_SELECTION_AWD = 570491150;
    public static final int DRIVE_MODE_SELECTION_COMFORT = 570491138;
    public static final int DRIVE_MODE_SELECTION_CUSTOM = 570491200;
    public static final int DRIVE_MODE_SELECTION_DYNAMIC = 570491139;
    public static final int DRIVE_MODE_SELECTION_ECO = 570491137;
    public static final int DRIVE_MODE_SELECTION_ECO_HEV_PHEV = 570491152;
    public static final int DRIVE_MODE_SELECTION_HDC = 570491141;
    public static final int DRIVE_MODE_SELECTION_HYBRID = 570491143;
    public static final int DRIVE_MODE_SELECTION_MUD = 570491146;
    public static final int DRIVE_MODE_SELECTION_NORMAL = 570491153;
    public static final int DRIVE_MODE_SELECTION_OFFROAD = 570491155;
    public static final int DRIVE_MODE_SELECTION_PHEV = 570491148;
    public static final int DRIVE_MODE_SELECTION_POWER = 570491144;
    public static final int DRIVE_MODE_SELECTION_PURE = 570491142;
    public static final int DRIVE_MODE_SELECTION_ROCK = 570491147;
    public static final int DRIVE_MODE_SELECTION_SAND = 570491149;
    public static final int DRIVE_MODE_SELECTION_SAVE = 570491151;
    public static final int DRIVE_MODE_SELECTION_SNOW = 570491145;
    public static final int DRIVE_MODE_SELECTION_UNKNOWN = 255;
    public static final int DRIVE_MODE_SELECTION_XC = 570491140;
    public static final int DRIVE_MODE_SELECTION_eAWD = 570491154;
    public static final int DRIVE_MODE_SPORT_PLUS = 570491157;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BpfMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ClimateMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DimThemeValues {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DriveModeFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DriveModeSelection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DriverInfoMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DriverInforTheme {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface InteriorLightMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropulsionSysMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface RabMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SteeringWheelFeelMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SuspensionMode {
    }
}
