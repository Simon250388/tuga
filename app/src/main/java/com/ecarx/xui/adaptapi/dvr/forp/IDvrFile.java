package com.ecarx.xui.adaptapi.dvr.forp;

import android.net.Uri;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IDvrFile {
    public static final int FILE_TYPE_AVM_VIDEO = 6;
    public static final int FILE_TYPE_CAMERA_PHOTO = 4;
    public static final int FILE_TYPE_DRIVING_PHOTO = 3;
    public static final int FILE_TYPE_EMERGENCY_VIDEO = 1;
    public static final int FILE_TYPE_GENERAL_VIDEO = 2;
    public static final int FILE_TYPE_PARKING_VIDEO = 5;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FileType {
    }

    int getApplicationType();

    long getDeleteTime();

    long getDuration();

    Uri getFileUri();

    int getHeight();

    double getLatitude();

    double getLongitude();

    String getMd5();

    String getName();

    long getSize();

    Uri getThumbnail();

    long getTicktime();

    int getType();

    int getWidth();

    boolean isDeleted();

    boolean isDvrFileLocked();
}
