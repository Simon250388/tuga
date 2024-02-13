package com.ecarx.xui.adaptapi.audio.audiofx;

import com.ecarx.xui.adaptapi.FunctionStatus;

/* loaded from: classes.dex */
public interface IEqualizer {
    short getBand(int i);

    int[] getBandFreqRange(short s);

    short getBandLevel(short s);

    short[] getBandLevelRange();

    int getCenterFreq(short s);

    short getCurrentPreset();

    short getNumberOfBands();

    short getNumberOfPresets();

    String getPresetName(short s);

    FunctionStatus isEqualizerSupported();

    void setBandLevel(short s, short s2);

    void usePreset(short s);
}
