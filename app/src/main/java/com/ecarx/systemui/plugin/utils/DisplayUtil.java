package com.ecarx.systemui.plugin.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.util.Log;
import com.ecarx.systemui.plugin.psd.StackInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DisplayUtil {
    private static final String TAG = "DisplayUtil";

    public static ComponentName getTopActivityByDisplayId(int i) {
        List list;
        ArrayList arrayList = new ArrayList();
        try {
            Method declaredMethod = ActivityManager.class.getDeclaredMethod("getService", new Class[0]);
            declaredMethod.setAccessible(true);
            ArrayList arrayList2 = new ArrayList();
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke != null && (list = (List) invoke.getClass().getDeclaredMethod("getAllStackInfos", new Class[0]).invoke(invoke, new Object[0])) != null) {
                arrayList2.addAll(list);
            }
            for (Object obj : arrayList2) {
                Class<?> cls = obj.getClass();
                Field declaredField = cls.getDeclaredField("displayId");
                declaredField.setAccessible(true);
                Field declaredField2 = cls.getDeclaredField("topActivity");
                declaredField2.setAccessible(true);
                Field declaredField3 = cls.getDeclaredField("visible");
                declaredField2.setAccessible(true);
                int i2 = declaredField.getInt(obj);
                ComponentName componentName = (ComponentName) declaredField2.get(obj);
                boolean z = declaredField3.getBoolean(obj);
                Log.w(TAG, "[ZKPSD_activity][getStackStackInfosByDisplayId]--2, displayId: " + i2 + ", visibel: " + z + ", cn: " + componentName);
                if (i == i2) {
                    StackInfo stackInfo = new StackInfo();
                    stackInfo.displayId = i2;
                    stackInfo.topActivity = componentName;
                    stackInfo.visible = z;
                    if (componentName != null && z) {
                        arrayList.add(stackInfo);
                    }
                }
            }
        } catch (Exception e) {
            Log.w(TAG, "getTopActivityByDisplayId, e: " + Log.getStackTraceString(e));
        }
        if (arrayList.size() == 0 || arrayList.get(0) == null) {
            return null;
        }
        return ((StackInfo) arrayList.get(0)).topActivity;
    }
}
