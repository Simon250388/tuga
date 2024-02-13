package com.ecarx.xui.adaptapi.audio.audiofx;

import com.ecarx.xui.adaptapi.FunctionStatus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface ICompensation {
    public static final int AUDIO_SETTING_COMPENSATION_LEVEL = 3;
    public static final int AUDIO_SETTING_EFFECT_ENHANCE = 4;
    public static final int AUDIO_SETTING_LOUDNESS_COMPENSATION = 1;
    public static final int AUDIO_SETTING_SPEED_COMPENSATION = 2;
    public static final int COMPENSATION_LEVEL_HIGH = 101;
    public static final int COMPENSATION_LEVEL_LOW = 99;
    public static final int COMPENSATION_LEVEL_MEDIUM = 100;
    public static final int EFFECT_ENHANCE_ALL_BLANCE = 4;
    public static final int EFFECT_ENHANCE_CENTERPOINT = 5;
    public static final int EFFECT_ENHANCE_DRIVE = 1;
    public static final int EFFECT_ENHANCE_OFF = 0;
    public static final int EFFECT_ENHANCE_PASSENGER = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CompensationLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CompensationSettings {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EffectEnhanceMode {
    }

    /* loaded from: classes.dex */
    public interface ICompensationSettingListener {
        void onCompensationSettingStateChanged(int i);
    }

    int getCompensationLevelOfSpeedCompensatedVolume();

    int getEffectEnhanceMode();

    int[] getSupportedEffectEnhanceMode();

    FunctionStatus isCompensationSettingSupported(int i);

    boolean isLoudnessEnabled();

    boolean isSpeedCompensatedVolumeEnabled();

    boolean registerCompensationSettingListener(ICompensationSettingListener iCompensationSettingListener);

    void setCompensationLevelOfSpeedCompensatedVolume(int i);

    boolean setEffectEnhanceMode(int i);

    void setLoudnessEnable(boolean z);

    void setSpeedCompensatedVolumeEnable(boolean z);

    boolean unregisterCompensationSettingListener(ICompensationSettingListener iCompensationSettingListener);
}
