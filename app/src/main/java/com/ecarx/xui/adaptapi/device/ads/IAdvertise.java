package com.ecarx.xui.adaptapi.device.ads;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IAdvertise {
    public static final int OPERATION_CLICKED = 2;
    public static final int OPERATION_DISPLAY = 1;
    public static final int OPERATION_END = 4;
    public static final int OPERATION_SKIPPED = 3;

    /* loaded from: classes.dex */
    public interface OnAdOperationChangedListener {
        void onOperationChanged(String str, int i);

        void onUpdateAdRecord(IAdRecordInfo iAdRecordInfo);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface OperationCode {
    }

    void addOnAdOperationChangedListener(OnAdOperationChangedListener onAdOperationChangedListener);

    IAdRecordInfo getLatestAdRecord();

    void removeOnAdOperationChangedListener(OnAdOperationChangedListener onAdOperationChangedListener);

    void setBootAdInfo(IBootAdInfo iBootAdInfo);
}
