package com.ecarx.xui.adaptapi.evs;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class EVS extends AdaptAPI {
    public static final int EVS_CAMERA_AVM = 2;
    public static final int EVS_CAMERA_DVR = 3;
    public static final int EVS_CAMERA_REAR = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EvsCameraId {
    }

    /* loaded from: classes.dex */
    public interface IEvsCameraStatusObserver {
        void onEvsCameraClosed(int i);

        void onEvsCameraOpened(int i);
    }

    public static EVS create(Context context) {
        return null;
    }

    public abstract boolean attachEvsCameraStatusObserver(IEvsCameraStatusObserver iEvsCameraStatusObserver);

    public abstract boolean detachEvsCameraStatusObserver(IEvsCameraStatusObserver iEvsCameraStatusObserver);

    public abstract boolean evsCameraCloseNotify(int i);

    @Deprecated
    public abstract IEvsCamera getEvsCamera();

    public abstract boolean isCameraOpened(int i);
}
