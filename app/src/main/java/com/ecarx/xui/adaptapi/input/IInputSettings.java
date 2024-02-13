package com.ecarx.xui.adaptapi.input;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IInputSettings {
    public static final int DURATION_BUTTON_STUCK = 2;
    public static final int DURATION_HOLD_PRESS_INTERVAL_TRIGGER = 3;
    public static final int DURATION_HOLD_SHORT = 1;
    public static final int INPUT_SETTING_LONG_PRESS_VOLUME_ADJUSTMENT_RATE = 2;
    public static final int INPUT_SETTING_MAX_STEP_TO_STEP = 5;
    public static final int INPUT_SETTING_MOVE_VOLUME_ADJUSTMENT_RATE = 3;
    public static final int INPUT_SETTING_SHORT_PRESS_VOLUME_ADJUSTMENT = 1;
    public static final int INPUT_SETTING_SWIPE_VOLUME_ADJUSTMENT_RATE = 4;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DurationType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface InputSetting {
    }

    long getInputSettingDuration(int i);

    int getInputSettingValue(int i);
}
