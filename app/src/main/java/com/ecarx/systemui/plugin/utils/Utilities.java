package com.ecarx.systemui.plugin.utils;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.navigationbar.AppWatcherService;
import com.ecarx.systemui.plugin.psd.StackInfo;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class Utilities {
    public static final ComponentName APPPANE_COMPONENT;
    public static final String APPPANE_PROCESS = "ecarx.launcher3:appsvr";
    public static final boolean ATLEAST_JB_MR1;
    public static final boolean ATLEAST_JB_MR2;
    public static final boolean ATLEAST_KITKAT;
    public static final boolean ATLEAST_LOLLIPOP;
    public static final boolean ATLEAST_LOLLIPOP_MR1;
    public static final boolean ATLEAST_O_MR1;
    public static final int DEFAULT_TYPE = 0;
    public static final String EXIT_TYPE = "APPPANE_EXIT_TYPE";
    public static final String LAUNCHER_PROCESS = "ecarx.launcher3";
    public static final int NOT_RESUME_TYPE = 1;
    public static final int SYSTEMUI_CHANGE = 4;
    public static final int SYSTEMUI_HIDE_TYPE = 3;
    public static final int SYSTEMUI_SHOW_TYPE = 2;
    private static final String TAG = Utilities.class.getSimpleName();

    static {
        ATLEAST_O_MR1 = Build.VERSION.SDK_INT >= 27;
        APPPANE_COMPONENT = new ComponentName("ecarx.launcher3", AppWatcherService.PANE_CLASSNAME);
        ATLEAST_LOLLIPOP_MR1 = Build.VERSION.SDK_INT >= 22;
        ATLEAST_LOLLIPOP = Build.VERSION.SDK_INT >= 21;
        ATLEAST_KITKAT = Build.VERSION.SDK_INT >= 19;
        ATLEAST_JB_MR1 = Build.VERSION.SDK_INT >= 17;
        ATLEAST_JB_MR2 = Build.VERSION.SDK_INT >= 18;
    }

    public static List<String> getTopProcess(Context context) {
        ArrayList arrayList = new ArrayList();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses();
        for (int i = 0; i < runningAppProcesses.size(); i++) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
            if (runningAppProcessInfo != null && runningAppProcessInfo.importance == 100 && runningAppProcessInfo.pkgList != null) {
                String str = TAG;
                Log.w(str, "[JRSYS][getTopProcess][" + i + "], is IMPORTANCE_FOREGROUND, pkgList: " + Arrays.toString(runningAppProcessInfo.pkgList) + ", processName: " + runningAppProcessInfo.processName + ", topAppProcess.importanceReasonComponent: " + runningAppProcessInfo.importanceReasonComponent);
                if (runningAppProcessInfo.processName != null) {
                    arrayList.add(runningAppProcessInfo.processName);
                }
            }
        }
        return arrayList;
    }

    public static ComponentName getTopActivityName(Context context) {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(3);
        if (runningTasks == null || runningTasks.size() <= 0 || (runningTaskInfo = runningTasks.get(0)) == null || runningTaskInfo.topActivity == null) {
            return null;
        }
        ComponentName componentName = runningTaskInfo.topActivity;
        String str = TAG;
        Log.w(str, "[JRSP][getTopActivityName][0], name: " + componentName);
        return componentName;
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight > 0 ? intrinsicHeight : 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        Bitmap createCircleBitmap = createCircleBitmap(createBitmap);
        if (!createBitmap.isRecycled()) {
            createBitmap.recycle();
        }
        return createCircleBitmap;
    }

    private static Bitmap createCircleBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f = width / 2;
        canvas.drawCircle(f, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static int getBgId(EStatefulObject eStatefulObject) {
        if (!eStatefulObject.equals(EStatefulObject.NETWORK_SIGNAL_STRENGTH)) {
            if (!eStatefulObject.equals(EStatefulObject.NETWORK_SIGNAL)) {
                if (eStatefulObject.equals(EStatefulObject.WIFI_AP)) {
                    return R.color.colorPrimary_test3;
                }
                if (!eStatefulObject.equals(EStatefulObject.BLUETOOTH)) {
                    if (!eStatefulObject.equals(EStatefulObject.WPC)) {
                        if (eStatefulObject.equals(EStatefulObject.USB)) {
                            return R.color.colorPrimary_test3;
                        }
                        if (!eStatefulObject.equals(EStatefulObject.DVR)) {
                            if (!eStatefulObject.equals(EStatefulObject.INTELLIGENT_WEARING_EQUIPMENT)) {
                                return eStatefulObject.equals(EStatefulObject.UNREAD_MESSAGE) ? R.color.colorPrimary_test3 : R.color.colorPrimary_test5;
                            }
                        }
                    }
                }
            }
            return R.color.colorPrimary_test2;
        }
        return R.color.colorPrimary_test1;
    }

    public static int getComponentDisplayId(ComponentName componentName) {
        if (componentName == null) {
            return -1;
        }
        for (StackInfo stackInfo : getStackStackInfos()) {
            if (componentName.equals(stackInfo.topActivity)) {
                int i = stackInfo.displayId;
                String str = TAG;
                Log.i(str, "[JRPSD_SYSTEM][getComponentDisplayId]--1, target stackInfo: " + stackInfo);
                return i;
            }
        }
        return -1;
    }

    public static List<StackInfo> getStackStackInfos() {
        ArrayList arrayList = new ArrayList();
        try {
            Method declaredMethod = ActivityManager.class.getDeclaredMethod("getService", new Class[0]);
            declaredMethod.setAccessible(true);
//            IActivityManager iActivityManager = (IActivityManager) declaredMethod.invoke(null, new Object[0]);
//            List<Object> allStackInfos = iActivityManager.getAllStackInfos();
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("[JRPSD_SYSTEM][getStackStackInfos]--1, iAm: ");
//            sb.append(iActivityManager);
            sb.append(", size: ");
//            sb.append(allStackInfos != null ? Integer.valueOf(allStackInfos.size()) : "NULL");
            sb.append(", list: ");
//            sb.append(allStackInfos);
            Log.i(str, sb.toString());
//            for (Object obj : allStackInfos) {
//                Class<?> cls = obj.getClass();
//                Field declaredField = cls.getDeclaredField("stackId");
//                declaredField.setAccessible(true);
//                Field declaredField2 = cls.getDeclaredField("displayId");
//                declaredField2.setAccessible(true);
//                Field declaredField3 = cls.getDeclaredField("topActivity");
//                declaredField3.setAccessible(true);
//                Field declaredField4 = cls.getDeclaredField("visible");
//                declaredField3.setAccessible(true);
//                Field declaredField5 = cls.getDeclaredField("position");
//                declaredField5.setAccessible(true);
//                Field declaredField6 = cls.getDeclaredField("taskNames");
//                declaredField5.setAccessible(true);
//                int i = declaredField.getInt(obj);
//                int i2 = declaredField2.getInt(obj);
//                ComponentName componentName = (ComponentName) declaredField3.get(obj);
//                boolean z = declaredField4.getBoolean(obj);
//                int i3 = declaredField5.getInt(obj);
//                String[] strArr = (String[]) declaredField6.get(obj);
//                String str2 = TAG;
//                Log.w(str2, "[JRPSD_SYSTEM][getStackStackInfos]--2, stackId: " + i + ", displayId: " + i2 + ", visibel: " + z + ", cn: " + componentName + ", position: " + i3 + ", taskNames: " + Arrays.toString(strArr));
//                StackInfo stackInfo = new StackInfo();
//                stackInfo.stackId = i;
//                stackInfo.displayId = i2;
//                stackInfo.topActivity = componentName;
//                stackInfo.visible = z;
//                stackInfo.position = i3;
//                stackInfo.taskNames = strArr;
//                arrayList.add(stackInfo);
//            }
        } catch (Exception e) {
            String str3 = TAG;
            Log.w(str3, "[JRPSD_SYSTEM][getStackStackInfos], e: " + Log.getStackTraceString(e));
        }
        return arrayList;
    }

    public static String getTopActivityPackage(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String str = TAG;
        Log.i(str, " getTopActivityPackage version = " + Build.VERSION.SDK_INT);
        String str2 = "";
        if (Build.VERSION.SDK_INT >= 22) {
            UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            long currentTimeMillis = System.currentTimeMillis();
            List<UsageStats> queryUsageStats = usageStatsManager != null ? usageStatsManager.queryUsageStats(0, currentTimeMillis - 120000, currentTimeMillis) : null;
            if (queryUsageStats != null && queryUsageStats.size() > 0) {
                TreeMap treeMap = new TreeMap();
                for (UsageStats usageStats : queryUsageStats) {
                    if (!"com.android.server.telecom".equals(usageStats.getPackageName()) && !"android".equals(usageStats.getPackageName())) {
                        treeMap.put(Long.valueOf(usageStats.getLastTimeUsed()), usageStats);
                    } else {
                        String str3 = TAG;
                        Log.i(str3, "getTopActivityPackage UsageStats remove package:" + usageStats.getPackageName());
                    }
                }
                if (treeMap.isEmpty()) {
                    return "";
                }
                String packageName = ((UsageStats) treeMap.get(treeMap.lastKey())).getPackageName();
                String str4 = TAG;
                Log.i(str4, "getTopActivityPackage topPackageName = " + packageName);
                return packageName;
            }
            Log.i(TAG, "getTopActivityPackage UsageStats null");
            return "";
        } else if (Build.VERSION.SDK_INT >= 21) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager != null ? activityManager.getRunningAppProcesses() : null;
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses != null ? runningAppProcesses.get(0) : null;
            if (runningAppProcessInfo != null && runningAppProcessInfo.importance == 100) {
                str2 = runningAppProcessInfo.processName;
            }
            String str5 = TAG;
            Log.i(str5, "getTopActivityPackage topPackageName = " + str2);
            return str2;
        } else {
            String packageName2 = (activityManager != null ? activityManager.getRunningTasks(1) : null).get(0).topActivity.getPackageName();
            String str6 = TAG;
            Log.i(str6, "getTopActivityPackage topPackageName = " + packageName2);
            return packageName2;
        }
    }

    public static boolean isProcessActive(Context context, String str) {
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                return false;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            String str2 = next.processName;
            if (next.importance > 200) {
                for (String str3 : next.pkgList) {
                    if (TextUtils.equals(str, str3)) {
                        return true;
                    }
                }
                continue;
            }
        }
    }
}
