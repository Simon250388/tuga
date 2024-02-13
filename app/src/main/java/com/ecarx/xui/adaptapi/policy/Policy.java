package com.ecarx.xui.adaptapi.policy;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;

/* loaded from: classes.dex */
public abstract class Policy extends AdaptAPI {
    public static Policy create(Context context) {
        return null;
    }

    public abstract IAudioAttributes getAudioAttributes();

    @Deprecated
    public abstract IAudioPolicy getAudioPolicy();

    public abstract IStoragePolicy getStoragePolicy();

    public abstract IVoiceAssistantPolicy getVoiceAssistantPolicy();

    public abstract IWindowManagerPolicy getWindowManagerPolicy();
}
