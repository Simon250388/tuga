package com.ecarx.xui.adaptapi.dvr.forp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IFileManager {
    public static final int OPERATION_DELETE_FILES = 8194;
    public static final int OPERATION_LOCK_FILES = 8199;
    public static final int OPERATION_MOVE_FILES_TO_EMERGENCY = 8195;
    public static final int OPERATION_PAGE_DOWN = 8197;
    public static final int OPERATION_PAGE_UP = 8196;
    public static final int OPERATION_RESTORE_DELETED_FILES = 8198;
    public static final int OPERATION_SYNC_FILES = 8193;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FileOperationType {
    }

    /* loaded from: classes.dex */
    public interface IFileObserver {
        void onDeleteFiles(IDvrFile[] iDvrFileArr);

        void onFileOperationResults(int i, IDvrFile[] iDvrFileArr, IDvrFile[] iDvrFileArr2);

        void onNewFiles(IDvrFile[] iDvrFileArr);
    }

    void deleteDvrFiles(IDvrFile[] iDvrFileArr);

    IDvrFile[] getAllDvrFiles();

    IDvrFile[] getDrvFiles(int i, int i2);

    IDvrFile[] getDvrDeletedFiles();

    int getDvrFileCount(int i);

    IDvrFile[] getDvrFiles(int i);

    void lockDvrFiles(IDvrFile[] iDvrFileArr);

    void moveFiles2EmergencyPartition(IDvrFile[] iDvrFileArr);

    boolean registerOperationObserver(IFileObserver iFileObserver);

    void restoreDeletedFiles(IDvrFile[] iDvrFileArr);

    boolean unegisterOperationObserver(IFileObserver iFileObserver);
}
