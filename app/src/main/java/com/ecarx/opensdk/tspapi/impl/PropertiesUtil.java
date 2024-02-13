package com.ecarx.opensdk.tspapi.impl;

import android.content.Context;

/* loaded from: classes.dex */
final class PropertiesUtil {
    PropertiesUtil() {
    }

    public static String getStringProp(Context context, String str) throws IllegalArgumentException {
        return getString(context, str, "");
    }

    public static int getIntProp(Context context, String str) throws IllegalArgumentException {
        return getInt(context, str, 0).intValue();
    }

    public static String getString(Context context, String str, String str2) throws IllegalArgumentException {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) loadClass.getMethod("get", String.class, String.class).invoke(loadClass, new String(str), new String(str2));
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception unused) {
            return str2;
        }
    }

    public static Integer getInt(Context context, String str, int i) throws IllegalArgumentException {
        Integer.valueOf(i);
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (Integer) loadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(loadClass, new String(str), new Integer(i));
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception unused) {
            return Integer.valueOf(i);
        }
    }

    public static Long getLong(Context context, String str, long j) throws IllegalArgumentException {
        Long.valueOf(j);
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (Long) loadClass.getMethod("getLong", String.class, Long.TYPE).invoke(loadClass, new String(str), new Long(j));
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception unused) {
            return Long.valueOf(j);
        }
    }

    public static Boolean getBoolean(Context context, String str, boolean z) throws IllegalArgumentException {
        Boolean.valueOf(z);
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (Boolean) loadClass.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(loadClass, new String(str), new Boolean(z));
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception unused) {
            return Boolean.valueOf(z);
        }
    }
}
