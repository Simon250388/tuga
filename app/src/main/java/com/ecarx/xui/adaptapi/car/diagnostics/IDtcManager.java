package com.ecarx.xui.adaptapi.car.diagnostics;

import java.util.List;

/* loaded from: classes.dex */
public interface IDtcManager {

    /* loaded from: classes.dex */
    public interface IDtcInfo {
        String getDtcCode();

        String getDtcId();

        int getEcuType();

        int getStatus();

        long getTicktime();
    }

    /* loaded from: classes.dex */
    public interface IDtcInfoWatcher {
        void onDtcInfosChanged(List<IDtcInfo> list);
    }

    List<IDtcInfo> getDtcInfos();

    boolean registerWatcher(IDtcInfoWatcher iDtcInfoWatcher);

    void unregisterWatcher(IDtcInfoWatcher iDtcInfoWatcher);
}
