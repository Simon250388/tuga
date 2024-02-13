package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.Context;
import android.os.storage.StorageManager;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.xui.adaptapi.policy.IStoragePolicy;
import com.ecarx.xui.adaptapi.policy.Policy;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class USBDevice {
    private static final String AUTHORITY = "media";
    private static final String CONTENT_AUTHORITY_SLASH = "content://media/";
    private static final String TAG = USBDevice.class.getSimpleName();
    private static USBDevice instance;
    private IStoragePolicy iStoragePolicy;
    private Context mContext;

    public static USBDevice getInstance(Context context) {
        if (instance == null) {
            synchronized (USBDevice.class) {
                if (instance == null) {
                    instance = new USBDevice(context);
                }
            }
        }
        return instance;
    }

    private USBDevice(Context context) {
        this.mContext = context;
        Policy create = Policy.create(context);
        if (create != null) {
            this.iStoragePolicy = create.getStoragePolicy();
        }
    }

    public String getVolumeName(int i) {
        return this.iStoragePolicy.getVolumeName(i);
    }

    public String getVolumeFullPath(int i) {
        try {
            if (this.iStoragePolicy != null) {
                return this.iStoragePolicy.getVolumeFullPath(i);
            }
            return null;
        } catch (NullPointerException e) {
            Lg.e(TAG, e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isChecking(int i) {
        String str;
        List<String> storagePath = getStoragePath(this.mContext);
        int i2 = 0;
        while (true) {
            if (i2 >= storagePath.size()) {
                str = "removed";
                break;
            } else if (storagePath.get(i2).equals(getVolumeFullPath(i))) {
                str = storageVolumeStatue(this.mContext, i2);
                break;
            } else {
                i2++;
            }
        }
        return "checking".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isMounted(int i) {
        String str;
        List<String> storagePath = getStoragePath(this.mContext);
        int i2 = 0;
        while (true) {
            if (i2 >= storagePath.size()) {
                str = "removed";
                break;
            } else if (storagePath.get(i2).equals(getVolumeFullPath(i))) {
                str = storageVolumeStatue(this.mContext, i2);
                break;
            } else {
                i2++;
            }
        }
        return "mounted".equals(str) || "mounted_ro".equals(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0043, code lost:
        if ("internal".equals(r1) != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0054, code lost:
        if (r7 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0057, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean isScanning(int r9) {
        /*
            r8 = this;
            java.lang.String r0 = "content://media/none/media_scanner"
            android.net.Uri r2 = android.net.Uri.parse(r0)
            r0 = 0
            r7 = 0
            android.content.Context r1 = r8.mContext     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            java.lang.String r3 = "volume"
            java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            if (r7 == 0) goto L46
            int r1 = r7.getCount()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            r2 = 1
            if (r1 != r2) goto L46
            r7.moveToFirst()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            java.lang.String r1 = r7.getString(r0)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            java.lang.String r9 = r8.getVolumeName(r9)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            boolean r9 = r9.equals(r1)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            if (r9 != 0) goto L45
            java.lang.String r9 = "private"
            boolean r9 = r9.equals(r1)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            if (r9 != 0) goto L45
            java.lang.String r9 = "internal"
            boolean r9 = r9.equals(r1)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            if (r9 == 0) goto L46
        L45:
            r0 = r2
        L46:
            if (r7 == 0) goto L57
        L48:
            r7.close()
            goto L57
        L4c:
            r9 = move-exception
            goto L58
        L4e:
            r9 = move-exception
            java.lang.String r1 = com.ecarx.systemui.plugin.statusbar.policy.USBDevice.TAG     // Catch: java.lang.Throwable -> L4c
            com.ecarx.systemui.plugin.utils.Lg.e(r1, r9)     // Catch: java.lang.Throwable -> L4c
            if (r7 == 0) goto L57
            goto L48
        L57:
            return r0
        L58:
            if (r7 == 0) goto L5d
            r7.close()
        L5d:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.systemui.plugin.statusbar.policy.USBDevice.isScanning(int):boolean");
    }

    private List<String> getStoragePath(Context context) {
        StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        ArrayList arrayList = new ArrayList();
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = storageManager != null ? storageManager.getClass().getMethod("getVolumeList", new Class[0]) : null;
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Object invoke = method != null ? method.invoke(storageManager, new Object[0]) : null;
            int length = Array.getLength(invoke);
            for (int i = 0; i < length; i++) {
                arrayList.add((String) method2.invoke(Array.get(invoke, i), new Object[0]));
            }
        } catch (ClassNotFoundException e) {
            Lg.e(TAG, e);
        } catch (IllegalAccessException e2) {
            Lg.e(TAG, e2);
        } catch (NoSuchMethodException e3) {
            Lg.e(TAG, e3);
        } catch (InvocationTargetException e4) {
            Lg.e(TAG, e4);
        }
        return arrayList;
    }

    private String storageVolumeStatue(Context context, int i) {
        StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = storageManager != null ? storageManager.getClass().getMethod("getVolumeList", new Class[0]) : null;
            Object invoke = method != null ? method.invoke(storageManager, new Object[0]) : null;
            int length = Array.getLength(invoke);
            for (int i2 = 0; i2 < length; i2++) {
                if (i2 == i) {
                    return (String) StorageManager.class.getDeclaredMethod("getVolumeState", String.class).invoke(storageManager, (String) cls.getMethod("getPath", new Class[0]).invoke(Array.get(invoke, i2), new Object[0]));
                }
            }
            return "";
        } catch (Exception e) {
            Lg.e(TAG, e);
            return "";
        }
    }
}
