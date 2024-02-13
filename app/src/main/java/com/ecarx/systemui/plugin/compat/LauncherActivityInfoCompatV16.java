package com.ecarx.systemui.plugin.compat;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

/* loaded from: classes.dex */
public class LauncherActivityInfoCompatV16 extends LauncherActivityInfoCompat {
    private final ActivityInfo mActivityInfo;
    private final ComponentName mComponentName;
    private final Context mContext;
    private final PackageManager mPm;
    private final ResolveInfo mResolveInfo;

    @Override // com.ecarx.systemui.plugin.compat.LauncherActivityInfoCompat
    public ApplicationInfo getApplicationInfo() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LauncherActivityInfoCompatV16(Context context, ResolveInfo resolveInfo) {
        this.mResolveInfo = resolveInfo;
        this.mActivityInfo = resolveInfo.activityInfo;
        this.mComponentName = new ComponentName(this.mActivityInfo.packageName, this.mActivityInfo.name);
        this.mPm = context.getPackageManager();
        this.mContext = context;
    }

    @Override // com.ecarx.systemui.plugin.compat.LauncherActivityInfoCompat
    public ComponentName getComponentName() {
        return this.mComponentName;
    }
}
