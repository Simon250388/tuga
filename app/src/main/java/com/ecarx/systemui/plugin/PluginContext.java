package com.ecarx.systemui.plugin;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.ecarx.systemui.plugin.utils.Lg;

public class PluginContext extends ContextWrapper {
    private static final String TAG = PluginContext.class.getSimpleName();
    private static Context context;

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        return this;
    }

    public PluginContext(Context context2) {
        super(context2);
        context = this;
    }

    public static Context getPluginContext() {
        return context;
    }

    public static Context getHostContext() {
        Context context2 = null;
        try {
            context2 = ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null)).getApplicationContext();
        } catch (Throwable th) {
            String str = TAG;
            Lg.w(str, "e: " + Log.getStackTraceString(th));
        }
        String str2 = TAG;
        Lg.i(str2, "[JRSYS][getCurAppContext], hostContext: " + context2);
        return context2;
    }
}
