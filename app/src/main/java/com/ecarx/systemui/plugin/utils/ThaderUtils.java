package com.ecarx.systemui.plugin.utils;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public class ThaderUtils {
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void post(Runnable runnable) {
        sHandler.post(runnable);
    }
}
