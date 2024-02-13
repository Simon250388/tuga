package com.ecarx.systemui.plugin.utils;

import android.util.Log;

/* loaded from: classes.dex */
public class Lg {
    private static final String TAG_BASE = "SystemUIPlugin:";

    private static String genTag(String str) {
        if (str == null) {
            return TAG_BASE;
        }
        return TAG_BASE + str;
    }

    public static void i(String str) {
        i((String) null, str);
    }

    public static void i(String str, Throwable th) {
        i(null, str, th);
    }

    public static void e(String str) {
        e((String) null, str);
    }

    public static void e(Throwable th) {
        e((String) null, th);
    }

    public static void e(String str, Throwable th) {
        e(null, str, th);
    }

    public static void d(String str) {
        d((String) null, str);
    }

    public static void d(String str, Throwable th) {
        d(null, str, th);
    }

    public static void v(String str) {
        v((String) null, str);
    }

    public static void v(String str, Throwable th) {
        v(null, str, th);
    }

    public static void w(String str) {
        w((String) null, str);
    }

    public static void w(String str, Throwable th) {
        w(null, str, th);
    }

    public static void i(String str, String str2) {
        Log.i(genTag(str), str2);
    }

    public static void i(String str, String str2, Throwable th) {
        Log.i(genTag(str), str2, th);
    }

    public static void e(String str, String str2) {
        Log.e(genTag(str), str2);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(genTag(str), str2, th);
    }

    public static void d(String str, String str2) {
        Log.d(genTag(str), str2);
    }

    public static void d(String str, String str2, Throwable th) {
        Log.d(genTag(str), str2, th);
    }

    public static void v(String str, String str2) {
        Log.v(genTag(str), str2);
    }

    public static void v(String str, String str2, Throwable th) {
        Log.v(genTag(str), str2, th);
    }

    public static void w(String str, String str2) {
        Log.w(genTag(str), str2);
    }

    public static void w(String str, String str2, Throwable th) {
        Log.w(genTag(str), str2, th);
    }

    public static void eTag(String str, String str2) {
        Log.e(str, str2);
    }
}
