package com.ecarx.xui.adaptapi.policy;

/* loaded from: classes.dex */
public interface IVoiceAssistantPolicy {
    public static final int WAKE_ID_BIN_RUI = 9;
    public static final int WAKE_ID_BO_RUI = 7;
    public static final int WAKE_ID_BO_YUE = 1;
    public static final int WAKE_ID_DI_HAO = 2;
    public static final int WAKE_ID_ELISE = 5;
    public static final int WAKE_ID_JIA_JI = 11;
    public static final int WAKE_ID_JIHE = 14;
    public static final int WAKE_ID_JI_LI = 12;
    public static final int WAKE_ID_LYNKCO = 4;
    public static final int WAKE_ID_PROTON = 13;
    public static final int WAKE_ID_SHUAI_GE = 6;
    public static final int WAKE_ID_XIAO_KA = 8;
    public static final int WAKE_ID_XING_YUE = 10;
    public static final int WAKE_ID_YUAN_JING = 3;
    public static final int WORK_MODE_BYPASS = 0;
    public static final int WORK_MODE_DEFAULT = 3;
    public static final int WORK_MODE_ECHO_CANCEL = 2;
    public static final int WORK_MODE_NOISE_CLEAN = 1;
    public static final int WORK_MODE_VOICE_WAKEUP = 3;

    /* loaded from: classes.dex */
    public @interface WakeID {
    }

    /* loaded from: classes.dex */
    public @interface WorkMode {
    }

    int getTTSPCMVolume();

    int getWakeID();

    int setWorkMode(int i);
}
