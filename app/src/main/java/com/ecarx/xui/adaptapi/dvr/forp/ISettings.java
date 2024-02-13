package com.ecarx.xui.adaptapi.dvr.forp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface ISettings {
    public static final int CRASH_SENSITIVITY_LEVEL_HIGH = 1049091;
    public static final int CRASH_SENSITIVITY_LEVEL_LOW = 1049089;
    public static final int CRASH_SENSITIVITY_LEVEL_MIDDLE = 1049090;
    public static final int CRASH_SENSITIVITY_LEVEL_OFF = 0;
    public static final int EMER_RECORDING_DURATION_10S = 1051393;
    public static final int EMER_RECORDING_DURATION_20S = 1051394;
    public static final int EMER_RECORDING_DURATION_30S = 1051395;
    public static final int PREVIEW_OPT_FRONT = 1050881;
    public static final int PREVIEW_OPT_INNER = 1050882;
    public static final int PREVIEW_OPT_PIP_FRONT = 1050883;
    public static final int PREVIEW_OPT_PIP_INNER = 1050884;
    public static final int RECORDING_DURATION_1_MINUTES = 1049857;
    public static final int RECORDING_DURATION_3_MINUTES = 1049858;
    public static final int RECORDING_DURATION_5_MINUTES = 1049859;
    public static final int RESOLUTION_1080P_30FPS = 1048834;
    public static final int RESOLUTION_480P_30FPS = 1048832;
    public static final int RESOLUTION_720P_30FPS = 1048833;
    public static final int SETTING_FUNC_AUTO_EMERGENCY_OVERRIDE = 1050368;
    public static final int SETTING_FUNC_CRASH_SENSITIVITY_LEVEL = 1049088;
    public static final int SETTING_FUNC_EMER_RECORDING_DURATION = 1051392;
    public static final int SETTING_FUNC_GENERAL_RECORDING_ENABLE = 1052160;
    public static final int SETTING_FUNC_MIC_MUTE = 1049344;
    public static final int SETTING_FUNC_PARK_MONITOR = 1049600;
    public static final int SETTING_FUNC_PREVIEW_OPT = 1050880;
    public static final int SETTING_FUNC_RECORDING_DURATION = 1049856;
    public static final int SETTING_FUNC_SDCARD_REMAINED_SIZE = 1051904;
    public static final int SETTING_FUNC_SDCARD_TOTAL_SIZE = 1051648;
    public static final int SETTING_FUNC_SHOW_TIME = 1050624;
    public static final int SETTING_FUNC_SHUTDOWN_DELAY = 1050112;
    public static final int SETTING_FUNC_VIDEO_RESOLUTION = 1048832;
    public static final int SETTING_FUNC_WDR = 1051136;
    public static final int SHUTDOWN_DELAY_10S = 1050113;
    public static final int SHUTDOWN_DELAY_180S = 1050116;
    public static final int SHUTDOWN_DELAY_300S = 1050117;
    public static final int SHUTDOWN_DELAY_30S = 1050114;
    public static final int SHUTDOWN_DELAY_60S = 1050115;
    public static final int SHUTDOWN_DELAY_OFF = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CrashSensitivityLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EmerRecordingDuration {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PreviewOption {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface RecordingDuration {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ResolutionType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SettingFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ShutdownDelay {
    }
}
