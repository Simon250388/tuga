package com.ecarx.systemui.plugin.utils;

import android.content.Context;
import android.content.res.Configuration;

/* loaded from: classes.dex */
public class UIModeModel {
    public static final int NAVIGATION_BAR = 1;
    public static final int STATUS_BAR = 0;
    private static Context mNavigationBarConfigurationContext;
    private static Context mStatusBarConfigurationContext;
    private String TAG = UIModeModel.class.getSimpleName();

    /* loaded from: classes.dex */
    public @interface BAR_TYPE {
    }

    public static Context getConfigurationContext(int i) {
        return i == 0 ? mStatusBarConfigurationContext : mNavigationBarConfigurationContext;
    }

    public static Context setConfigurationContext(Context context, int i, int i2) {
        Configuration configuration = context.getResources().getConfiguration();
        if (i == 1) {
            configuration.uiMode = 16;
        } else if (i == 2) {
            configuration.uiMode = 32;
        } else if (i == 0) {
            configuration.uiMode = 0;
        }
        Context createConfigurationContext = context.createConfigurationContext(configuration);
        if (i2 == 0) {
            mStatusBarConfigurationContext = createConfigurationContext;
        } else if (i2 == 1) {
            mNavigationBarConfigurationContext = createConfigurationContext;
        }
        return createConfigurationContext;
    }
}
