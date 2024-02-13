package com.ecarx.systemui.plugin.utils;

import android.util.Log;

/* loaded from: classes.dex */
public class LogUtil {
    public static final String BLUETOOTH_CALLBACK_TAG = "bluetooth_callback_tag";
    public static final String CLIMATE_TAG = "climate_tag";
    public static final String EVENT_DELIVERY_TAG = "event_delivery_tag";
    public static final String NETWORK_TAG = "network_tag";
    public static final String TAG = "system_ui_plugin_tag";
    private static final boolean isDebug = true;

    public static void logEventD(String str) {
        d(EVENT_DELIVERY_TAG, str);
    }

    public static void logBluetoothD(String str) {
        d(BLUETOOTH_CALLBACK_TAG, str);
    }

    public static void logNetworkD(String str) {
        d(NETWORK_TAG, str);
    }

    public static void climateD(String str) {
        d(CLIMATE_TAG, str);
    }

    public static void i(String str) {
        Log.i(TAG, str);
    }

    public static void d(String str) {
        Log.d(TAG, str);
    }

    public static void e(String str) {
        Log.e(TAG, str);
    }

    public static void i(String str, String str2) {
        Log.i(str, str2);
    }

    public static void d(String str, String str2) {
        Log.d(str, str2);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }
}
