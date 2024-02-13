package com.ecarx.xui.adaptapi.ota;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IOtaSessionNotification extends IOtaSessionCallback {
    public static final int NOTIFICATION_DOWNLOADING = 17;
    public static final int NOTIFICATION_DOWNLOAD_COMPLETED = 18;
    public static final int NOTIFICATION_DOWNLOAD_ERROR = 19;
    public static final int NOTIFICATION_ESTIMATED_TIME_UPDATE = 65;
    public static final int NOTIFICATION_INSTALLING = 33;
    public static final int NOTIFICATION_INSTALL_ABORTED = 34;
    public static final int NOTIFICATION_NEW_VERSION = 1;
    public static final int NOTIFICATION_REMIND_POPUP_ENABLE = 35;
    public static final int NOTIFICATION_TIME_OVERDUE = 52;
    public static final int NOTIFICATION_TIME_REMIND = 51;
    public static final int NOTIFICATION_TIME_SET_FAILED = 50;
    public static final int NOTIFICATION_TIME_SET_SUCCEED = 49;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface NotificationType {
    }

    void onNotificationUpdate(int i);
}
