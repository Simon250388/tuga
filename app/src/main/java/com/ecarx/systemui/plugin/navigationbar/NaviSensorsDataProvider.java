package com.ecarx.systemui.plugin.navigationbar;

import android.content.Context;
import android.content.pm.ProviderInfo;
import com.ecarx.systemui.plugin.PluginContext;
import com.ecarx.systemui.plugin.utils.Lg;
import com.sensorsdata.analytics.android.sdk.data.SensorsDataContentProvider;
import java.io.File;

/* loaded from: classes.dex */
public class NaviSensorsDataProvider extends SensorsDataContentProvider {
    @Override // com.sensorsdata.analytics.android.sdk.data.SensorsDataContentProvider, android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        String packageName = context.getApplicationContext().getPackageName();
        File databasePath = context.getDatabasePath(packageName);
        Lg.i("NaviProvider", "[JRSYS][onCreate], context: " + context + ", context name: " + context.getPackageName() + ", app context: " + context.getApplicationContext() + ", packageName: " + packageName + ", oldDataBase: " + databasePath.exists());
        return super.onCreate();
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(PluginContext.getHostContext(), providerInfo);
    }
}
