package com.ecarx.systemui.plugin.compat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class LauncherAppsCompatV16 extends LauncherAppsCompat {
    private Context mContext;
    private PackageManager mPm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LauncherAppsCompatV16(Context context) {
        this.mPm = context.getPackageManager();
        this.mContext = context;
    }

    @Override // com.ecarx.systemui.plugin.compat.LauncherAppsCompat
    public boolean isActivityEnabledForProfile(ComponentName componentName, UserHandleCompat userHandleCompat) {
        try {
            ActivityInfo activityInfo = this.mPm.getActivityInfo(componentName, 0);
            if (activityInfo != null) {
                return activityInfo.isEnabled();
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.compat.LauncherAppsCompat
    public List<LauncherActivityInfoCompat> getActivityList(String str, UserHandleCompat userHandleCompat) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        List<ResolveInfo> queryIntentActivities = this.mPm.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList(queryIntentActivities.size());
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            arrayList.add(new LauncherActivityInfoCompatV16(this.mContext, resolveInfo));
        }
        return arrayList;
    }
}
