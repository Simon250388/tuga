package com.ecarx.xui.adaptapi.policy;

/* loaded from: classes.dex */
public interface IStoragePolicy {
    public static final int MOUNT_TYPE_DISK = 1;
    public static final int MOUNT_TYPE_MTP = 3;
    public static final int MOUNT_TYPE_UNKNOWN = 0;
    public static final int MOUNT_TYPE_USB = 2;
    public static final int VOLUME_GKUI_PRIVATE_COMMON = 5;
    public static final int VOLUME_GKUI_PRIVATE_MAP = 4;
    public static final int VOLUME_GKUI_PRIVATE_VR_RES = 3;
    public static final int VOLUME_USB_FLASH_DISK_1 = 1;
    public static final int VOLUME_USB_FLASH_DISK_2 = 2;
    public static final int VOLUME_USB_HOST_1 = 10;
    public static final int VOLUME_USB_HOST_2 = 11;
    public static final int VOLUME_USB_HOST_3 = 12;
    public static final int VOLUME_USB_HOST_4 = 13;

    /* loaded from: classes.dex */
    public interface IUsbDeviceListener {
        void onReceiveUsbDeviceAction(String str, IUsbVolumeInfo iUsbVolumeInfo);
    }

    /* loaded from: classes.dex */
    public interface IUsbVolumeInfo {
        String getFullPath();

        int getMountTypes();

        int getUsbHostId();

        String getVolumeId();
    }

    /* loaded from: classes.dex */
    public @interface MountType {
    }

    /* loaded from: classes.dex */
    public @interface UsbActions {
    }

    /* loaded from: classes.dex */
    public @interface UsbHostId {
    }

    /* loaded from: classes.dex */
    public @interface VolumeType {
    }

    int getUsbHostCount();

    IUsbVolumeInfo[] getUsbHostVolumeInfos(int i);

    String getVolumeFullPath(int i);

    String getVolumeName(int i);

    boolean registerUsbDeviceListener(IUsbDeviceListener iUsbDeviceListener);

    boolean unregisterUsbDeviceListener(IUsbDeviceListener iUsbDeviceListener);
}
