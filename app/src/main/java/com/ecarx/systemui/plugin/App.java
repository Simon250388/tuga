package com.ecarx.systemui.plugin;

import android.app.Application;
import android.util.Log;
import com.ecarx.systemui.plugin.utils.CarUtils;

/* loaded from: classes.dex */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    private static App mApp;

    @Override // android.app.Application
    public void onCreate() {
        Log.w(TAG, "gc11111111111111111111");
        super.onCreate();
        Log.w(TAG, "gc111111111111111111111");
        mApp = this;
        CarUtils.initCarUtils(this);
    }

    public static App getApp() {
        return mApp;
    }
}
