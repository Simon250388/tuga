package com.ecarx.systemui.plugin.compat;

import android.content.ComponentName;
import android.content.Context;
import com.ecarx.systemui.plugin.utils.Utilities;
import java.util.List;

/* loaded from: classes.dex */
public abstract class LauncherAppsCompat {
    private static LauncherAppsCompat sInstance;
    private static Object sInstanceLock = new Object();

    public abstract List<LauncherActivityInfoCompat> getActivityList(String str, UserHandleCompat userHandleCompat);

    public abstract boolean isActivityEnabledForProfile(ComponentName componentName, UserHandleCompat userHandleCompat);

    public static LauncherAppsCompat getInstance(Context context) {
        LauncherAppsCompat launcherAppsCompat;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                if (Utilities.ATLEAST_LOLLIPOP) {
                    sInstance = new LauncherAppsCompatVL(context.getApplicationContext());
                } else {
                    sInstance = new LauncherAppsCompatV16(context.getApplicationContext());
                }
            }
            launcherAppsCompat = sInstance;
        }
        return launcherAppsCompat;
    }
}
