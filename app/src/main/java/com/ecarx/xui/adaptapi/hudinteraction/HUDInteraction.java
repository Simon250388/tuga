package com.ecarx.xui.adaptapi.hudinteraction;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class HUDInteraction extends AdaptAPI {
    public static final int HUD_MODE_AR_DISPLAY = 4;
    public static final int HUD_MODE_SIMPLE_DISPLAY = 1;
    public static final int HUD_MODE_SMART_DRIVING_DISPLAY = 2;
    public static final int HUD_MODE_SMART_GUIDE_DISPLAY = 3;
    public static final int TI_GAP_SET_FOR_LGT_CTRL_NONE = 0;
    public static final int TI_GAP_SET_FOR_LGT_CTRL_TIME_GAP_1 = 1;
    public static final int TI_GAP_SET_FOR_LGT_CTRL_TIME_GAP_2 = 2;
    public static final int TI_GAP_SET_FOR_LGT_CTRL_TIME_GAP_3 = 3;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface HUDMode {
    }

    /* loaded from: classes.dex */
    public interface IInteractionCallback {
        void onADASLaneAidSyncInfo(IADASLaneSyncInfo iADASLaneSyncInfo, IADASDrivingAidSyncInfo iADASDrivingAidSyncInfo);

        void onADASOpenStatusChanged(int i);

        void onCarFollowingGAPChanged(int i);

        void onHUDHeightChanged(int i);

        void onHUDModeChanged(int i);

        void onVehicleSyncInfo(IVehicleSyncInfo iVehicleSyncInfo);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface TiGapSetForLgtCtrl {
    }

    public static HUDInteraction create(Context context) {
        return null;
    }

    public abstract HUDCalibrationParam getHUDCalibrationParam();

    public abstract boolean registerCallback(IInteractionCallback iInteractionCallback);

    public abstract int requestADASOpenStatus();

    public abstract void requestADASSyncInfo();

    public abstract int requestCarFollowingGAPLevel();

    public abstract void requestHUDHeight();

    public abstract int requestHUDMode();

    public abstract void requestVehicleSyncInfo();

    public abstract boolean unregisterCallback(IInteractionCallback iInteractionCallback);
}
