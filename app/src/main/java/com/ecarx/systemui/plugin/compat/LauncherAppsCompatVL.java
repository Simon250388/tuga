package com.ecarx.systemui.plugin.compat;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class LauncherAppsCompatVL extends LauncherAppsCompat {
    private LauncherApps mLauncherApps;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LauncherAppsCompatVL(Context context) {
        this.mLauncherApps = (LauncherApps) context.getSystemService(Context.LAUNCHER_APPS_SERVICE);
    }

    @Override // com.ecarx.systemui.plugin.compat.LauncherAppsCompat
    public boolean isActivityEnabledForProfile(ComponentName componentName, UserHandleCompat userHandleCompat) {
        return this.mLauncherApps.isActivityEnabled(componentName, userHandleCompat.getUser());
    }

    @Override // com.ecarx.systemui.plugin.compat.LauncherAppsCompat
    public List<LauncherActivityInfoCompat> getActivityList(String str, UserHandleCompat userHandleCompat) {
        List<LauncherActivityInfo> activityList = this.mLauncherApps.getActivityList(str, userHandleCompat.getUser());
        if (activityList.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(activityList.size());
        for (LauncherActivityInfo launcherActivityInfo : activityList) {
            arrayList.add(new LauncherActivityInfoCompatVL(launcherActivityInfo));
        }
        return arrayList;
    }
}
