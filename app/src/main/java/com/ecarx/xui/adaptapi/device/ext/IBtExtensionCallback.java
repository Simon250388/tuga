package com.ecarx.xui.adaptapi.device.ext;

import com.ecarx.xui.adaptapi.device.ext.common.BtDevice;
import java.util.List;

/* loaded from: classes.dex */
public interface IBtExtensionCallback {
    void onAdapterStateChanged(int i, int i2);

    void onDeviceBondStateChanged(BtDevice btDevice, int i, int i2);

    void onDeviceFoundChanged(int i, BtDevice btDevice);

    void onDevicePowerUpdated(BtDevice btDevice, int i);

    void onDeviceUuidsUpdated(BtDevice btDevice);

    void onPairedDevicesChanged(List<BtDevice> list);
}
