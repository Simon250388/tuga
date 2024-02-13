package com.ecarx.xui.adaptapi.car.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IDayMode {
    public static final int SETTING_FUNC_BACKLIGHT_LINKAGE = 687931648;
    public static final int SETTING_FUNC_BRIGHTNESS_BACKLIGHT = 687997184;
    public static final int SETTING_FUNC_BRIGHTNESS_BACKLIGHT_MAX = 687997440;
    public static final int SETTING_FUNC_BRIGHTNESS_BACKLIGHT_MIN = 687997696;
    public static final int SETTING_FUNC_BRIGHTNESS_BACKLIGHT_STEP = 687997952;
    public static final int SETTING_FUNC_BRIGHTNESS_DAY = 538247936;
    public static final int SETTING_FUNC_BRIGHTNESS_MAX = 538248448;
    public static final int SETTING_FUNC_BRIGHTNESS_MIN = 538248704;
    public static final int SETTING_FUNC_BRIGHTNESS_NIGHT = 538248192;
    public static final int SETTING_FUNC_BRIGHTNESS_STEP = 538248960;
    public static final int SETTING_FUNC_DAYMODE_SETTING = 538247424;
    public static final int SETTING_FUNC_DAYMODE_SYNC = 538247680;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DayModeFunction {
    }
}
