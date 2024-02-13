package com.ecarx.systemui.plugin.utils;

import android.util.Log;

/* loaded from: classes.dex */
public class LogUtils {
    private static final String TAG = "systemuiplugin";

    public static void d(String str) {
        Log.d(TAG, str);
    }

    public static void w(String str) {
        Log.w(TAG, str);
    }

    public static void i(String str) {
        Log.i(TAG, str);
    }

    public static void d(String str, String str2) {
        Log.d(TAG, str + " : " + str2);
    }

    public static void w(String str, String str2) {
        Log.w(TAG, str + " : " + str2);
    }

    public static void i(String str, String str2) {
        Log.i(TAG, str + " : " + str2);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(TAG, str + " : " + str2, th);
    }

    public static void e(String str, String str2) {
        Log.e(TAG, str + " : " + str2);
    }

    public static void stack(Object... objArr) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        String methodName = stackTraceElement.getMethodName();
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(fileName);
        sb.append(":");
        sb.append(lineNumber);
        sb.append(")");
        sb.append("  ");
        sb.append(methodName);
        sb.append(": ");
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            sb.append(obj == null ? "null" : obj.toString());
            sb.append("    ");
        }
        Log.i(TAG, sb.toString());
    }
}
