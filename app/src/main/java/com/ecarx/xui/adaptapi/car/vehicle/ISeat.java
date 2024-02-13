package com.ecarx.xui.adaptapi.car.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface ISeat {
    public static final int SEAT_BACKREST_BACKWARD = 755171842;
    public static final int SEAT_BACKREST_FORWARD = 755171841;
    public static final int SEAT_BACKREST_OFF = 0;
    public static final int SEAT_BACKREST_SIDE_SUPPORT_BACKWARD = 755237378;
    public static final int SEAT_BACKREST_SIDE_SUPPORT_FORWARD = 755237377;
    public static final int SEAT_BACKREST_SIDE_SUPPORT_OFF = 0;
    public static final int SEAT_CUSHION_EXTENSION_BACKWARD = 755433730;
    public static final int SEAT_CUSHION_EXTENSION_FORWARD = 755433729;
    public static final int SEAT_CUSHION_EXTENSION_OFF = 0;
    public static final int SEAT_CUSHION_SIDE_SUPPORT_DOWN = 755237122;
    public static final int SEAT_CUSHION_SIDE_SUPPORT_OFF = 0;
    public static final int SEAT_CUSHION_SIDE_SUPPORT_UP = 755237121;
    public static final int SEAT_CUSHION_TILT_DOWN = 755171586;
    public static final int SEAT_CUSHION_TILT_OFF = 0;
    public static final int SEAT_CUSHION_TILT_UP = 755171585;
    public static final int SEAT_HEADREST_HEIGHT_DOWN = 755302658;
    public static final int SEAT_HEADREST_HEIGHT_OFF = 0;
    public static final int SEAT_HEADREST_HEIGHT_UP = 755302657;
    public static final int SEAT_HEADREST_TILT_BACKWARD = 755302914;
    public static final int SEAT_HEADREST_TILT_FORWARD = 755302913;
    public static final int SEAT_HEADREST_TILT_OFF = 0;
    public static final int SEAT_HEIGHT_DOWN = 755106306;
    public static final int SEAT_HEIGHT_OFF = 0;
    public static final int SEAT_HEIGHT_UP = 755106305;
    public static final int SEAT_LEG_SUPPORT_HEIGHT_DOWN = 755499266;
    public static final int SEAT_LEG_SUPPORT_HEIGHT_OFF = 0;
    public static final int SEAT_LEG_SUPPORT_HEIGHT_UP = 755499265;
    public static final int SEAT_LEG_SUPPORT_LENGTH_BACKWARD = 755499522;
    public static final int SEAT_LEG_SUPPORT_LENGTH_FORWARD = 755499521;
    public static final int SEAT_LEG_SUPPORT_LENGTH_OFF = 0;
    public static final int SEAT_LENGTH_BACKWARD = 755106050;
    public static final int SEAT_LENGTH_FORWARD = 755106049;
    public static final int SEAT_LENGTH_OFF = 0;
    public static final int SEAT_LUMBAR_EXTENDED_BACKWARD = 755368450;
    public static final int SEAT_LUMBAR_EXTENDED_FORWARD = 755368449;
    public static final int SEAT_LUMBAR_EXTENDED_OFF = 0;
    public static final int SEAT_LUMBAR_HEIGHT_DOWN = 755368194;
    public static final int SEAT_LUMBAR_HEIGHT_OFF = 0;
    public static final int SEAT_LUMBAR_HEIGHT_UP = 755368193;
    public static final int SEAT_POSITION_SAVED_1 = 759169281;
    public static final int SEAT_POSITION_SAVED_2 = 759169282;
    public static final int SEAT_POSITION_SAVED_3 = 759169283;
    public static final int SEAT_POSITION_SAVED_OFF = 0;
    public static final int SEAT_SUPPORT_CONTROL_MODE_LONG_PRESS = 755040769;
    public static final int SEAT_SUPPORT_CONTROL_MODE_LONG_SHORT_PRESS = 755040771;
    public static final int SEAT_SUPPORT_CONTROL_MODE_SHORT_PRESS = 755040770;
    public static final int SETTING_FUNC_DRIVER_CONTROL_PASSENGER_SEAT = 755040512;
    public static final int SETTING_FUNC_EASY_INGRESS_EGRESS = 538378496;
    public static final int SETTING_FUNC_SEAT_BACKREST = 755171840;
    public static final int SETTING_FUNC_SEAT_BACKREST_POS = 755172352;
    public static final int SETTING_FUNC_SEAT_BACKREST_SIDE_SUPPORT = 755237376;
    public static final int SETTING_FUNC_SEAT_BOSS_KEY = 759235072;
    public static final int SETTING_FUNC_SEAT_CUSHION_EXTENSION = 755433728;
    public static final int SETTING_FUNC_SEAT_CUSHION_SIDE_SUPPORT = 755237120;
    public static final int SETTING_FUNC_SEAT_CUSHION_TILT = 755171584;
    public static final int SETTING_FUNC_SEAT_CUSHION_TILT_POS = 755172096;
    public static final int SETTING_FUNC_SEAT_HEADREST_HEIGHT = 755302656;
    public static final int SETTING_FUNC_SEAT_HEADREST_HEIGHT_POS = 755303168;
    public static final int SETTING_FUNC_SEAT_HEADREST_TILT = 755302912;
    public static final int SETTING_FUNC_SEAT_HEADREST_TILT_POS = 755303424;
    public static final int SETTING_FUNC_SEAT_HEIGHT = 755106304;
    public static final int SETTING_FUNC_SEAT_HEIGHT_POS = 755106816;
    public static final int SETTING_FUNC_SEAT_LEG_SUPPORT_HEIGHT = 755499264;
    public static final int SETTING_FUNC_SEAT_LEG_SUPPORT_HEIGHT_POS = 755499776;
    public static final int SETTING_FUNC_SEAT_LEG_SUPPORT_LENGTH = 755499520;
    public static final int SETTING_FUNC_SEAT_LEG_SUPPORT_LENGTH_POS = 755500032;
    public static final int SETTING_FUNC_SEAT_LENGTH = 755106048;
    public static final int SETTING_FUNC_SEAT_LENGTH_POS = 755106560;
    public static final int SETTING_FUNC_SEAT_LUMBAR_EXTENDED = 755368448;
    public static final int SETTING_FUNC_SEAT_LUMBAR_HEIGHT = 755368192;
    public static final int SETTING_FUNC_SEAT_ONE_KEY_BED = 759235328;
    public static final int SETTING_FUNC_SEAT_POSITION_SAVE = 759169280;
    public static final int SETTING_FUNC_SEAT_POSITION_SET = 759169536;
    public static final int SETTING_FUNC_SEAT_REST_PATTERN = 759234816;
    public static final int SETTING_FUNC_SEAT_SUPPORT_CONTROL_MODE = 755040768;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatBackrestMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatBackrestSideSupportMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatCushionExtensionMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatCushionSideSupportMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatCushionTiltMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatHeadrestHeightMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatHeadrestLengthMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatHeigthMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatLegSupportHeightMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatLegSupportLengthMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatLengthMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatLumbarExtendedMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatLumbarHeightMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatPositionSavedMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SeatSupportControlMode {
    }
}
