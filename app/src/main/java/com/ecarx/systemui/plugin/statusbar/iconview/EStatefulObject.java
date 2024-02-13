package com.ecarx.systemui.plugin.statusbar.iconview;

/* loaded from: classes.dex */
public enum EStatefulObject {
    NETWORK_SIGNAL_STRENGTH(1),
    NETWORK_SIGNAL(2),
    WIFI_AP(3),
    BLUETOOTH(4),
    WPC(5),
    USB(6),
    DVR(7),
    INTELLIGENT_WEARING_EQUIPMENT(8),
    UNREAD_MESSAGE(9);
    
    private int priority;

    EStatefulObject(int i) {
        this.priority = i;
    }

    public int getPriority() {
        return this.priority;
    }
}
