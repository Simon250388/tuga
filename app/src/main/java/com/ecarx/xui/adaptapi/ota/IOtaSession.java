package com.ecarx.xui.adaptapi.ota;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;

/* loaded from: classes.dex */
public interface IOtaSession {
    public static final int OTA_MODE_DOWNLOAD_INSTALL = 2;
    public static final int OTA_MODE_INSTALL_DIRECTLY = 1;
    public static final int OTA_MODE_SELF_DOWNLOAD_INSTALL = 3;
    public static final int OTA_PRIORITY_HIGH = 2;
    public static final int OTA_PRIORITY_LOW = 0;
    public static final int OTA_PRIORITY_NORMAL = 1;
    public static final int OTA_PRIORITY_SET_TIME = 3;
    public static final int OTA_PRIORITY_UNKNOWN = 0;
    public static final int OTA_UPDATE_INPROGRESS_STATE_IDLE = 1;
    public static final int OTA_UPDATE_INPROGRESS_STATE_UPGRADE = 2;
    public static final int REGRET_TERMINATE = 2;
    public static final int REGRET_TIMEOUT = 1;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface InstallRegretState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface OtaMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface OtaPriority {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface OtaUpdateInProgressState {
    }

    boolean cancel();

    boolean cancelOtaUpgradeTime();

    boolean checkUpdate();

    boolean couldBeginInstallRightNow();

    boolean download();

    int getEstimatedInstallationTime();

    int getOtaBaseSysVersionCode();

    String getOtaBaseSysVersionName();

    int getOtaMode();

    int getOtaPriority();

    int getOtaProgress();

    int getOtaType();

    int getOtaUpdateInProgressState();

    Calendar getOtaUpdateTime();

    int getSysVersionCode();

    String getSysVersionName();

    String getUpgradeInfo();

    boolean ifSystemWillRebootAfterOta();

    boolean isInstallationStarted();

    boolean isPopupEnable();

    boolean isRecoveryOta();

    void setInstallRegretState(int i);

    boolean setOtaUpdateTime(Calendar calendar);

    boolean setPowerState(int i);
}
