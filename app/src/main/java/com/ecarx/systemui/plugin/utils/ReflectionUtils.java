package com.ecarx.systemui.plugin.utils;

import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class ReflectionUtils {
    static final String TAG = "ReflectionUtils";

    public static <T> T invoke(Object obj, String str) {
        try {
            return (T) obj.getClass().getMethod(str, new Class[0]).invoke(obj, new Object[0]);
        } catch (Error e) {
            Lg.e(TAG, e);
            return null;
        } catch (Exception e2) {
            Lg.e(TAG, e2);
            return null;
        }
    }

    public static <T> T invoke(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return (T) obj.getClass().getMethod(str, clsArr).invoke(obj, objArr);
        } catch (Error e) {
            Lg.e(TAG, e);
            return null;
        } catch (Exception e2) {
            Lg.e(TAG, e2);
            return null;
        }
    }

    public static <T> T invokeStatic(Class cls, String str) {
        try {
            return (T) cls.getClass().getMethod(str, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            Lg.e(TAG, e);
            return null;
        }
    }

    public static <T> T invokeStatic(Class cls, String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return (T) cls.getClass().getMethod(str, clsArr).invoke(null, objArr);
        } catch (Exception e) {
            Lg.e(TAG, e);
            return null;
        }
    }

    public static Field findField(Object obj, String str) throws NoSuchFieldException {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + obj.getClass());
    }

    public static Field findField(Class<?> cls, String str) throws NoSuchFieldException {
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + cls);
    }
}
