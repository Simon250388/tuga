package com.ecarx.systemui.plugin.compat;

import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;

/* loaded from: classes.dex */
public class LauncherActivityInfoCompatVL extends LauncherActivityInfoCompat {
    private LauncherActivityInfo mLauncherActivityInfo;

    @Override // com.ecarx.systemui.plugin.compat.LauncherActivityInfoCompat
    public ApplicationInfo getApplicationInfo() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LauncherActivityInfoCompatVL(LauncherActivityInfo launcherActivityInfo) {
        this.mLauncherActivityInfo = launcherActivityInfo;
    }

    @Override // com.ecarx.systemui.plugin.compat.LauncherActivityInfoCompat
    public ComponentName getComponentName() {
        return this.mLauncherActivityInfo.getComponentName();
    }
}
