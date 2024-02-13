package com.ecarx.systemui.plugin.navigationbar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.ecarx.systemui.plugin.utils.Lg;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes.dex */
public class HvacController {
    private static final long ADD_HVAC_MAX_TIME = 180000;
    private static final int HVAC_READDED = 101;
    private static final long READD_HVAC_TIME_INTERVAL = 5000;
    private static final String TAG = HvacController.class.getSimpleName();
    private static volatile HvacController mHvacController;
    private long allTime;
    private boolean mAddHvacSuccess;
    private Context mHostContext;
    private String mHvacAction;
    private FrameLayout mHvacBar;
    private String mHvacFunc;
    private Runnable mHvacRunnable = new Runnable() { // from class: com.ecarx.systemui.plugin.navigationbar.HvacController.1
        @Override // java.lang.Runnable
        public void run() {
            Lg.i(HvacController.TAG, "[JRSYS][mHvacRunnable]");
            HvacController.this.addHvac();
        }
    };
    private HvacAddHandler mHandler = new HvacAddHandler(Looper.myLooper());

    private HvacController(Context context) {
        this.mHostContext = context;
    }

    public static HvacController getInstance(Context context) {
        if (mHvacController == null) {
            synchronized (HvacController.class) {
                if (mHvacController == null) {
                    mHvacController = new HvacController(context);
                }
            }
        }
        return mHvacController;
    }

    private boolean addHvacView(ViewGroup viewGroup) {
        List<ResolveInfo> queryIntentActivities = this.mHostContext.getPackageManager().queryIntentActivities(new Intent(this.mHvacAction), 128);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("[JRSYS_HVAC][addHvacView], infoList: ");
        sb.append(queryIntentActivities);
        sb.append(", size: ");
        sb.append(queryIntentActivities == null ? "NULL" : Integer.valueOf(queryIntentActivities.size()));
        Log.i(str, sb.toString());
        boolean z = false;
        if (queryIntentActivities != null && !queryIntentActivities.isEmpty()) {
            ResolveInfo resolveInfo = queryIntentActivities.get(0);
            String str2 = TAG;
            Lg.i(str2, "[JRSYS_HVAC][addHvacView]-V201215-1, name: " + resolveInfo.activityInfo.packageName);
            if (resolveInfo.activityInfo == null) {
                Lg.i(TAG, "[JRSYS_HVAC][addHvacView]--1 activity is null");
                return false;
            }
            View hvacPluginView = getHvacPluginView(this.mHostContext, new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            if (hvacPluginView != null) {
                if (viewGroup.indexOfChild(hvacPluginView) != -1) {
                    viewGroup.removeView(hvacPluginView);
                }
                try {
                    String str3 = TAG;
                    Lg.w(str3, "hvacView: " + hvacPluginView.getClass().getName());
                    if (hvacPluginView.getLayoutParams() != null) {
                        String str4 = TAG;
                        Lg.w(str4, "hvacView LayoutParams: " + hvacPluginView.getLayoutParams().getClass().getName());
                    } else {
                        Lg.w(TAG, "hvacView LayoutParams == null");
                    }
                    ViewParent parent = hvacPluginView.getParent();
                    if (parent instanceof ViewGroup) {
                        String str5 = TAG;
                        Lg.w(str5, "hvacView: removeView " + parent.getClass());
                        ((ViewGroup) parent).removeView(hvacPluginView);
                    }
                    viewGroup.addView(hvacPluginView);
                    z = true;
                } catch (Exception e) {
                    Lg.w(TAG, "[JRSYS_HVAC][addHvacView]--2 panelHolder has view");
                    Lg.w(TAG, e);
                }
            }
        }
        String str6 = TAG;
        Lg.i(str6, "[JRSYS_HVAC][addHvacView]--3, addSuccess: " + z);
        return z;
    }

    private View getHvacPluginView(Context context, ComponentName componentName) {
        try {
            String packageName = componentName.getPackageName();
            String className = componentName.getClassName();
            Context createPackageContext = context.createPackageContext(packageName, Context.CONTEXT_INCLUDE_CODE);
            PathClassLoader pathClassLoader = new PathClassLoader(createPackageContext.getPackageResourcePath(), createPackageContext.getClassLoader());
            String str = TAG;
            Lg.i(str, "[JRSYS_HVAC][getHvacPluginView], pluginClassLoader: " + pathClassLoader);
            Class loadClass = pathClassLoader.loadClass(className);
            Method method = loadClass.getMethod(this.mHvacFunc, Context.class, Context.class, Bundle.class);
            String str2 = TAG;
            Lg.i(str2, "[JRSYS_HVAC][getHvacPluginView], localClass: " + loadClass + ", localMethod: " + method);
            return (View) method.invoke(loadClass, context, createPackageContext, new Bundle());
        } catch (Throwable th) {
            String str3 = TAG;
            Lg.w(str3, "[JRSYS_HVAC][getHvacPluginView], t: " + Log.getStackTraceString(th));
            return null;
        }
    }

    public void initHvac(FrameLayout frameLayout, String str, String str2) {
        if (frameLayout == null || str == null || str2 == null) {
            return;
        }
        this.mHvacFunc = str2;
        this.mHvacBar = frameLayout;
        this.mHvacAction = str;
        HvacAddHandler hvacAddHandler = this.mHandler;
        if (hvacAddHandler != null) {
            hvacAddHandler.post(this.mHvacRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addHvac() {
        boolean addHvacView = addHvacView(this.mHvacBar);
        this.mAddHvacSuccess = addHvacView;
        if (addHvacView) {
            this.mHandler.removeMessages(101);
            return;
        }
        long j = this.allTime;
        if (j >= ADD_HVAC_MAX_TIME) {
            this.mHandler.removeMessages(101);
            return;
        }
        this.allTime = j + READD_HVAC_TIME_INTERVAL;
        this.mHandler.removeMessages(101);
        this.mHandler.sendEmptyMessageDelayed(101, READD_HVAC_TIME_INTERVAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reAddHvac() {
        String str = TAG;
        Log.i(str, "[JRSYS_HVAC][reAddHvac], allTime: " + this.allTime);
        addHvac();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class HvacAddHandler extends Handler {
        HvacAddHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str = HvacController.TAG;
            Log.i(str, "[JRSYS_HVAC][handleMessage], allTime: " + HvacController.this.allTime + ", mAddHvacSuccess: " + HvacController.this.mAddHvacSuccess);
            if (message.what == 101 && !HvacController.this.mAddHvacSuccess) {
                HvacController.this.reAddHvac();
            }
        }
    }
}
