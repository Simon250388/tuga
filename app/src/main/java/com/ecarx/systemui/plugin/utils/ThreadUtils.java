package com.ecarx.systemui.plugin.utils;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public class ThreadUtils {
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void start(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void postDelay(long j, Runnable runnable) {
        sHandler.postDelayed(runnable, j);
    }

    public static void post(Runnable runnable) {
        postDelay(0L, runnable);
    }

    public static void removeCallbacks(Runnable runnable) {
        sHandler.removeCallbacks(runnable);
    }
}
