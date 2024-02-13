package com.ecarx.xui.adaptapi.device.ext;

import com.ecarx.xui.adaptapi.device.ext.common.BtDevice;
import java.util.List;

/* loaded from: classes.dex */
public interface IBtExtension {
    boolean cancelBtDiscovery();

    IA2dpExtension getA2dpExtension();

    int getBtState();

    String getConnectedPhoneNumber();

    int getHeadsetPower(BtDevice btDevice);

    IPbapExtension getPbapExtension();

    boolean isBtDiscovering();

    boolean isBtEnabled();

    boolean registerBtCallback(IBtExtensionCallback iBtExtensionCallback);

    boolean reqBtPair(String str);

    List<BtDevice> reqBtPairedDevices();

    boolean reqBtUnpair(String str);

    boolean setBtEnable(boolean z);

    boolean startBtDiscovery();

    boolean unregisterBtCallback(IBtExtensionCallback iBtExtensionCallback);
}
