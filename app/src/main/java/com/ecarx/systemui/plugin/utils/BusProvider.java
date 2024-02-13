package com.ecarx.systemui.plugin.utils;

import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class BusProvider {
    private static final EventBus EVENT_BUS = EventBus.getDefault();

    private BusProvider() {
    }

    public static void p(Object obj) {
        EVENT_BUS.post(obj);
    }

    public static void r(Object obj) {
        EVENT_BUS.register(obj);
    }

    public static void u(Object obj) {
        EVENT_BUS.unregister(obj);
    }
}
