package com.ecarx.systemui.plugin.utils;

import com.ecarx.systemui.plugin.R;

/* loaded from: classes.dex */
public class WifiIcons {
    private static final int WIFI_AP_MAX = 5;
    public static final int WIFI_LEVEL_COUNT;
    public static final int WIFI_SIGNAL_NULL = 2131099803;
    public static final int[] WIFI_SIGNAL_STRENGTH;

    public static int getApIconByCount(int i) {
        if (i > 5) {
            i = 5;
        }
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? R.drawable.ic_status_hotspot_0 : R.drawable.ic_status_hotspot_5 : R.drawable.ic_status_hotspot_4 : R.drawable.ic_status_hotspot_3 : R.drawable.ic_status_hotspot_2 : R.drawable.ic_status_hotspot_1 : R.drawable.ic_status_hotspot_0;
    }

    static {
        int[] iArr = {R.drawable.ic_status_wifi_strength_0, R.drawable.ic_status_wifi_strength_1, R.drawable.ic_status_wifi_strength_2, R.drawable.ic_status_wifi_strength_3, R.drawable.ic_status_wifi_strength_4};
        WIFI_SIGNAL_STRENGTH = iArr;
        WIFI_LEVEL_COUNT = iArr.length;
    }
}
