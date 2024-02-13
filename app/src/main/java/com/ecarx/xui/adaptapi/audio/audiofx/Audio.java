package com.ecarx.xui.adaptapi.audio.audiofx;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.FunctionStatus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class Audio extends AdaptAPI {
    public static final int AUDIO_SETTING_BOOT_UP_MUSIC = 4098;
    public static final int AUDIO_SETTING_NAVI_MIX_MODE = 4097;
    public static final int AUDIO_SETTING_SEAT_SOUND_OPTIMIZE = 4099;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface AudioSettings {
    }

    /* loaded from: classes.dex */
    public interface IAudioSettingListener {
        void onAudioSettingStateChanged(int i);
    }

    public static Audio create(Context context) {
        return null;
    }

    public abstract int getAudioProvider();

    public abstract IAudioState getAudioState();

    public abstract ICompensation getCompensation();

    public abstract IEqualizer getEqualizer();

    public abstract IFaderBalance getFaderBalance();

    public abstract IHarmanEqualizer getHarmanEqualizer();

    public abstract FunctionStatus isAudioSettingSupported(int i);

    public abstract boolean registerAudioSettingListener(IAudioSettingListener iAudioSettingListener);

    public abstract void setBootUpMusicOnOff(boolean z);

    public abstract void setNaviVoiceMixMode(int i);

    public abstract void setSeatSoundStageOptimize(int i);

    public abstract boolean unregisterAudioSettingListener(IAudioSettingListener iAudioSettingListener);
}
