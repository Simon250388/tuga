package com.ecarx.xui.adaptapi.diminteraction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IVrInteraction {
    public static final int ACTION_BUTTON_BACK = 16;
    public static final int ACTION_BUTTON_CONFIRM = 17;
    public static final int ACTION_BUTTON_DOWN = 10;
    public static final int ACTION_BUTTON_LEFT = 7;
    public static final int ACTION_BUTTON_RIGHT = 8;
    public static final int ACTION_BUTTON_UP = 9;
    public static final int ACTION_SEARCH_ALL_CONTACTS = 4;
    public static final int ACTION_SEARCH_CALL_LOG = 3;
    public static final int ACTION_SEARCH_CONTACTS = 1;
    public static final int ACTION_SEARCH_FAV_CONTACTS = 2;
    public static final int ACTION_SEARCH_NAVI_ADDRESS = 5;
    public static final int ACTION_SELECT = 32;
    public static final int ACTION_VOICE_TO_TEXT = 6;
    public static final int DATA_TYPE_ADDRESS = 2;
    public static final int DATA_TYPE_CONTACT = 1;
    public static final int DATA_TYPE_ILLEGAL = 0;
    public static final int SEARCH_STATUS_COMPLETED = 2;
    public static final int SEARCH_STATUS_NOT_RECOGNIZED = 4;
    public static final int SEARCH_STATUS_ONGOING = 1;
    public static final int SEARCH_STATUS_TIME_OUT = 3;
    public static final int VR_STATUS_IDLE = 0;
    public static final int VR_STATUS_LISTENING = 1;
    public static final int VR_STATUS_PROCESSING = 2;
    public static final int VR_STATUS_PROMTING = 4;
    public static final int VR_STATUS_WAITING = Integer.MIN_VALUE;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ActionDataType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ActionType {
    }

    /* loaded from: classes.dex */
    public interface IVrInteractionCallback {
        void onDoInteractionAction(int i, int i2, Object obj);

        void onVrInfoUpdateReqired();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VrSearchStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VrStatus {
    }

    void notifyVoiceToTextCompleted(String str);

    void notifyVrSearchStates(int i);

    void notifyVrStatusChanged(int i, int i2);

    void notifyVrTTSText(String str);

    void registerVrCallback(IVrInteractionCallback iVrInteractionCallback);

    void unRegisterVrCallback(IVrInteractionCallback iVrInteractionCallback);
}
