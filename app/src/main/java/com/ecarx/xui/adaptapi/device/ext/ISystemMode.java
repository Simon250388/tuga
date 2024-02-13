package com.ecarx.xui.adaptapi.device.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface ISystemMode {
    public static final int ENTERTAINMENT_MODE_OFF = 1049089;
    public static final int ENTERTAINMENT_MODE_ON = 1049090;
    public static final int ENTERTAINMENT_MODE_UNKNOWN = -1;
    public static final int INFOTAINMENT_MODE_OFF = 1052673;
    public static final int INFOTAINMENT_MODE_ON = 1052674;
    public static final int INFOTAINMENT_MODE_UNKNOWN = -1;
    public static final int PARTIAL_MODE_OFF = 1048833;
    public static final int PARTIAL_MODE_ON = 1048834;
    public static final int PARTIAL_MODE_UNKNOWN = -1;
    public static final int SYSTEM_MODE_ENTERTAINMENT = 1049088;
    public static final int SYSTEM_MODE_INFOTAINMENT = 1052672;
    public static final int SYSTEM_MODE_PARTIAL = 1048832;
    public static final int SYSTEM_MODE_STATE_UNKNOWN = -1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EntertainmentModeState {
    }

    /* loaded from: classes.dex */
    public interface ISystemModeListener {
        void onSystemModeStateChanged(int i, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface InfotainmentModeState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ModeState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PartialModeState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SystemMode {
    }

    int getSystemModeState(int i);

    boolean registerListener(int i, ISystemModeListener iSystemModeListener);

    boolean unregisterListener(ISystemModeListener iSystemModeListener);
}
