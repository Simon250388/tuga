package com.ecarx.xui.adaptapi.dvr.ota;

/* loaded from: classes.dex */
public interface IOtaSession {
    int getOtaProgress();

    boolean ifSystemWillRebootAfterOta();
}
