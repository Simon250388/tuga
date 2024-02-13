package com.ecarx.systemui.plugin.utils;

import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public class ThemeHelper implements ComponentCallbacks2 {
    private static Map<String, String> NAME_MAP = null;
    private static final String TAG = "ThemeHelper";
    public static final String THEME_APPLIED = "com.android.server.ThemeManager.action.THEME_APPLIED";
    private static final String THEME_ICON_NAME_PREFIX = "res/drawable-xhdpi/";
    public static final String THEME_NOT_APPLIED = "com.android.server.ThemeManager.action.THEME_NOT_APPLIED";
    private boolean isInited;
    private Map<String, SoftReference<Bitmap>> mCache;
    private Context mContext;
    private IntentFilter mFilter;
    private Map<String, SoftReference<Bitmap>> mLightCache;
    private Map<String, SoftReference<Bitmap>> mNoBackgroundCache;
    private PackageManager mPm;
    private BroadcastReceiver mThemeChangeReceiver;
    private static final File THEME_DIRECTORY = new File("/data/system/theme");
    private static final File THEME_ICONS_FILE = new File(THEME_DIRECTORY, "icons");
    private static final File THEME_ICONS_LIGHT_FILE = new File(THEME_DIRECTORY, "icons_light");
    private static final File THEME_ICONS_NOBACKGROUND_FILE = new File(THEME_DIRECTORY, "icons_nobackground");
    private static final Set<String> SKIP_CACHE = new HashSet();

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("com.tpopration.roprocam", "ecarx.camera.dvr");
        hashMap.put("com.ecarx.cx.thememanager", "com.ecarx.thememanager");
        NAME_MAP = Collections.unmodifiableMap(hashMap);
    }

    public static ThemeHelper getDefault() {
        return Holder.INSTANCE;
    }

    /* loaded from: classes.dex */
    private static class Holder {
        private static ThemeHelper INSTANCE = new ThemeHelper();

        private Holder() {
        }
    }

    private ThemeHelper() {
        this.isInited = Boolean.FALSE.booleanValue();
        this.mCache = new HashMap();
        this.mLightCache = new HashMap();
        this.mNoBackgroundCache = new HashMap();
        this.mFilter = null;
        this.mThemeChangeReceiver = new BroadcastReceiver() { // from class: com.ecarx.systemui.plugin.utils.ThemeHelper.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action) && ThemeHelper.THEME_APPLIED.equals(action)) {
                    ThemeHelper.this.clear();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        this.mFilter = intentFilter;
        intentFilter.addAction(THEME_APPLIED);
        this.mFilter.addAction(THEME_NOT_APPLIED);
    }

    public void init(Context context) {
        Context context2;
        if (this.isInited && (context2 = this.mContext) != null) {
            context2.unregisterReceiver(this.mThemeChangeReceiver);
        }
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mPm = applicationContext.getPackageManager();
        this.mContext.registerReceiver(this.mThemeChangeReceiver, this.mFilter);
        this.isInited = Boolean.TRUE.booleanValue();
    }

    public Drawable getActivityIcon(Resources resources, ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        ActivityInfo activityInfo = this.mPm.getActivityInfo(componentName, 1);
        int i2 = activityInfo.icon;
        int i3 = activityInfo.applicationInfo.icon;
        if (i2 != 0 && i != 0) {
            if (SKIP_CACHE.contains(activityInfo.name)) {
                return null;
            }
            return getDrawable(activityInfo.name, activityInfo.packageName, i, resources, i2);
        } else if (i2 != 0 || i3 == 0 || SKIP_CACHE.contains(activityInfo.packageName)) {
            return null;
        } else {
            if (NAME_MAP.containsKey(activityInfo.packageName)) {
                return getDrawable(null, NAME_MAP.get(activityInfo.packageName), i, resources, i3);
            }
            return getDrawable(activityInfo.name, activityInfo.packageName, i, resources, i3);
        }
    }

    public Drawable getLightActivityIcon(Resources resources, ComponentName componentName, int i) throws PackageManager.NameNotFoundException, IOException {
        ActivityInfo activityInfo = this.mPm.getActivityInfo(componentName, 1);
        int i2 = activityInfo.icon;
        int i3 = activityInfo.applicationInfo.icon;
        if (i2 != 0 && i != 0) {
            if (SKIP_CACHE.contains(activityInfo.name)) {
                return null;
            }
            return getLightDrawable(activityInfo.name, activityInfo.packageName, i, resources, i2);
        } else if (i2 != 0 || i3 == 0 || SKIP_CACHE.contains(activityInfo.packageName)) {
            return null;
        } else {
            if (NAME_MAP.containsKey(activityInfo.packageName)) {
                return getLightDrawable(null, NAME_MAP.get(activityInfo.packageName), i, resources, i3);
            }
            return getLightDrawable(activityInfo.name, activityInfo.packageName, i, resources, i3);
        }
    }

    public Drawable getNoBackgroundActivityIcon(Resources resources, ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        ActivityInfo activityInfo = this.mPm.getActivityInfo(componentName, 1);
        int i2 = activityInfo.icon;
        int i3 = activityInfo.applicationInfo.icon;
        if (i2 != 0 && i != 0) {
            if (SKIP_CACHE.contains(activityInfo.name)) {
                return null;
            }
            return getNoBackgroundDrawable(activityInfo.name, activityInfo.packageName, i, resources, i2);
        } else if (i2 != 0 || i3 == 0 || SKIP_CACHE.contains(activityInfo.packageName)) {
            return null;
        } else {
            if (NAME_MAP.containsKey(activityInfo.packageName)) {
                return getNoBackgroundDrawable(null, NAME_MAP.get(activityInfo.packageName), i, resources, i3);
            }
            return getNoBackgroundDrawable(activityInfo.name, activityInfo.packageName, i, resources, i3);
        }
    }

    public Drawable getApplicationIcon(Resources resources, String str, int i) throws PackageManager.NameNotFoundException {
        int i2;
        ApplicationInfo applicationInfo = this.mPm.getApplicationInfo(str, 0);
        if (applicationInfo == null || (i2 = applicationInfo.icon) == 0 || i == 0 || SKIP_CACHE.contains(str)) {
            return null;
        }
        return getDrawable(null, str, i, resources, i2);
    }

    public Drawable getLightApplicationIcon(Resources resources, String str, int i) throws PackageManager.NameNotFoundException, IOException {
        int i2;
        ApplicationInfo applicationInfo = this.mPm.getApplicationInfo(str, 0);
        if (applicationInfo == null || (i2 = applicationInfo.icon) == 0 || i == 0 || SKIP_CACHE.contains(str)) {
            return null;
        }
        return getLightDrawable(null, str, i, resources, i2);
    }

    public Drawable getNotBackgroundApplicationIcon(Resources resources, String str, int i) throws PackageManager.NameNotFoundException {
        int i2;
        ApplicationInfo applicationInfo = this.mPm.getApplicationInfo(str, 0);
        if (applicationInfo == null || (i2 = applicationInfo.icon) == 0 || i == 0 || SKIP_CACHE.contains(str)) {
            return null;
        }
        return getNoBackgroundDrawable(null, str, i, resources, i2);
    }

    private Drawable getDrawable(String str, String str2, int i, Resources resources, int i2) {
        ZipFile zipFile;
        Closeable closeable;
        String fileName = getFileName(str, str2);
        Closeable closeable2 = null;

        if (!THEME_ICONS_FILE.exists() || !THEME_ICONS_FILE.isFile()) {
            addSkip(str, str2);
            return null;
        }
        if (this.mCache.containsKey(THEME_ICON_NAME_PREFIX + fileName)) {
            Bitmap bitmap = this.mCache.get(THEME_ICON_NAME_PREFIX + fileName).get();
            if (bitmap != null) {
                return new BitmapDrawable(this.mContext.getResources(), bitmap);
            }
        }
        try {
            zipFile = new ZipFile(THEME_ICONS_FILE);
            try {
                ZipEntry entry = zipFile.getEntry(THEME_ICON_NAME_PREFIX + fileName);
                if (entry == null && !TextUtils.isEmpty(str)) {
                    fileName = getFileName(null, str2);
                    if (this.mCache.containsKey(THEME_ICON_NAME_PREFIX + fileName)) {
                        Bitmap bitmap2 = this.mCache.get(THEME_ICON_NAME_PREFIX + fileName).get();
                        if (bitmap2 != null) {
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmap2);
                            closeIO(null);
                            closeZip(zipFile);
                            return bitmapDrawable;
                        }
                    }
                    entry = zipFile.getEntry(THEME_ICON_NAME_PREFIX + fileName);
                }
                if (entry == null) {
                    addSkip(str, str2);
                    closeIO(null);
                    closeZip(zipFile);
                    return null;
                }
                InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(THEME_ICON_NAME_PREFIX + fileName));
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                    this.mCache.put(entry.getName(), new SoftReference<>(decodeStream));
                    BitmapDrawable bitmapDrawable2 = new BitmapDrawable(this.mContext.getResources(), decodeStream);
                    closeIO(inputStream);
                    closeZip(zipFile);
                    return bitmapDrawable2;
                } catch (Exception e) {
                    closeable = inputStream;
                    e = e;
                    try {
                        Log.e(TAG, "<" + this.mContext.getPackageName() + ">>> open ZipFile " + e.getMessage());
                        closeIO(closeable);
                        closeZip(zipFile);
                        return null;
                    } catch (Throwable th) {
                        closeable2 = closeable;
                        closeIO(closeable2);
                        closeZip(zipFile);
                        throw th;
                    }
                } catch (Throwable th2) {
                    closeable2 = inputStream;
                    closeIO(closeable2);
                    closeZip(zipFile);
                    throw th2;
                }
            } catch (Exception e2) {
                Throwable e = e2;
                closeable = null;
            } catch (Throwable th3) {
//                th = th3;
            }
        } catch (Exception e3) {
//            e = e3;
            closeable = null;
            zipFile = null;
        } catch (Throwable th4) {
//            th = th4;
            zipFile = null;
        }
        return null;
    }

    private Drawable getLightDrawable(String str, String str2, int i, Resources resources, int i2) throws IOException {
        ZipFile zipFile;
        Closeable closeable;
        String fileName = getFileName(str, str2);
        Closeable closeable2 = null;
        if (!THEME_ICONS_LIGHT_FILE.exists() || !THEME_ICONS_LIGHT_FILE.isFile()) {
            addSkip(str, str2);
            return null;
        }
        if (this.mLightCache.containsKey(THEME_ICON_NAME_PREFIX + fileName)) {
            Bitmap bitmap = this.mLightCache.get(THEME_ICON_NAME_PREFIX + fileName).get();
            if (bitmap != null) {
                return new BitmapDrawable(this.mContext.getResources(), bitmap);
            }
        }
        try {
            zipFile = new ZipFile(THEME_ICONS_LIGHT_FILE);
            try {
                ZipEntry entry = zipFile.getEntry(THEME_ICON_NAME_PREFIX + fileName);
                if (entry == null && !TextUtils.isEmpty(str)) {
                    fileName = getFileName(null, str2);
                    if (this.mLightCache.containsKey(THEME_ICON_NAME_PREFIX + fileName)) {
                        Bitmap bitmap2 = this.mLightCache.get(THEME_ICON_NAME_PREFIX + fileName).get();
                        if (bitmap2 != null) {
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmap2);
                            closeIO(null);
                            closeZip(zipFile);
                            return bitmapDrawable;
                        }
                    }
                    entry = zipFile.getEntry(THEME_ICON_NAME_PREFIX + fileName);
                }
                if (entry == null) {
                    addSkip(str, str2);
                    closeIO(null);
                    closeZip(zipFile);
                    return null;
                }
                InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(THEME_ICON_NAME_PREFIX + fileName));
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                    this.mLightCache.put(entry.getName(), new SoftReference<>(decodeStream));
                    BitmapDrawable bitmapDrawable2 = new BitmapDrawable(this.mContext.getResources(), decodeStream);
                    closeIO(inputStream);
                    closeZip(zipFile);
                    return bitmapDrawable2;
                } catch (Exception e) {
                    closeable = inputStream;
                    e = e;
                    try {
                        Log.e(TAG, "<" + this.mContext.getPackageName() + ">>> open ZipFile " + e.getMessage());
                        closeIO(closeable);
                        closeZip(zipFile);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        closeable2 = closeable;
                        closeIO(closeable2);
                        closeZip(zipFile);
                        throw th;
                    }
                } catch (Throwable th2) {
                    closeable2 = inputStream;
//                    th = th2;
                    closeIO(closeable2);
                    closeZip(zipFile);
                    throw th2;
                }
            } catch (Exception e2) {
//                e = e2;
                closeable = null;
            } catch (Throwable th3) {
//                th = th3;
            }
        } catch (Exception e3) {
//            e = e3;
            closeable = null;
            zipFile = null;
            throw e3;
        } catch (Throwable th4) {
//            th = th4;
            zipFile = null;
            throw th4;
        }
        return null;
    }

    private Drawable getNoBackgroundDrawable(String str, String str2, int i, Resources resources, int i2) {
        ZipFile zipFile;
        Closeable closeable;
        String fileName = getFileName(str, str2);
        Closeable closeable2 = null;
        if (!THEME_ICONS_NOBACKGROUND_FILE.exists() || !THEME_ICONS_NOBACKGROUND_FILE.isFile()) {
            addSkip(str, str2);
            return null;
        }
        if (this.mNoBackgroundCache.containsKey(THEME_ICON_NAME_PREFIX + fileName)) {
            Bitmap bitmap = this.mNoBackgroundCache.get(THEME_ICON_NAME_PREFIX + fileName).get();
            if (bitmap != null) {
                return new BitmapDrawable(this.mContext.getResources(), bitmap);
            }
        }
        try {
            zipFile = new ZipFile(THEME_ICONS_NOBACKGROUND_FILE);
            try {
                ZipEntry entry = zipFile.getEntry(THEME_ICON_NAME_PREFIX + fileName);
                if (entry == null && !TextUtils.isEmpty(str)) {
                    fileName = getFileName(null, str2);
                    if (this.mNoBackgroundCache.containsKey(THEME_ICON_NAME_PREFIX + fileName)) {
                        Bitmap bitmap2 = this.mNoBackgroundCache.get(THEME_ICON_NAME_PREFIX + fileName).get();
                        if (bitmap2 != null) {
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmap2);
                            closeIO(null);
                            closeZip(zipFile);
                            return bitmapDrawable;
                        }
                    }
                    entry = zipFile.getEntry(THEME_ICON_NAME_PREFIX + fileName);
                }
                if (entry == null) {
                    addSkip(str, str2);
                    closeIO(null);
                    closeZip(zipFile);
                    return null;
                }
                InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(THEME_ICON_NAME_PREFIX + fileName));
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                    this.mNoBackgroundCache.put(entry.getName(), new SoftReference<>(decodeStream));
                    BitmapDrawable bitmapDrawable2 = new BitmapDrawable(this.mContext.getResources(), decodeStream);
                    closeIO(inputStream);
                    closeZip(zipFile);
                    return bitmapDrawable2;
                } catch (Exception e) {
                    closeable = inputStream;
                    e = e;
                    try {
                        Log.e(TAG, "<" + this.mContext.getPackageName() + ">>> open ZipFile " + e.getMessage());
                        closeIO(closeable);
                        closeZip(zipFile);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        closeable2 = closeable;
                        closeIO(closeable2);
                        closeZip(zipFile);
                        throw th;
                    }
                } catch (Throwable th2) {
                    closeable2 = inputStream;
//                    th = th2;
                    closeIO(closeable2);
                    closeZip(zipFile);
                    throw th2;
                }
            } catch (Exception e2) {
//                e = e2;
                closeable = null;
            } catch (Throwable th3) {
//                th = th3;
            }
        } catch (Exception e3) {
//            e = e3;
            closeable = null;
            zipFile = null;
        } catch (Throwable th4) {
//            th = th4;
            zipFile = null;
        }
        return null;
    }

    private String getFileName(String str, String str2) {
        String str3 = str2 + ".png";
        if (TextUtils.isEmpty(str)) {
            return str3;
        }
        return str + "@" + str3;
    }

    private void addSkip(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            SKIP_CACHE.add(str);
        } else {
            SKIP_CACHE.add(str2);
        }
    }

    private void closeIO(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            Log.e(TAG, "<" + this.mContext.getPackageName() + ">> close InputStream " + e.getMessage());
        }
    }

    private void closeZip(ZipFile zipFile) {
        if (zipFile == null) {
            return;
        }
        try {
            zipFile.close();
        } catch (IOException e) {
            Log.e(TAG, "<" + this.mContext.getPackageName() + ">>> close ZipFile " + e.getMessage());
        }
    }

    public void clear() {
        this.mCache.clear();
        SKIP_CACHE.clear();
        this.mLightCache.clear();
        this.mNoBackgroundCache.clear();
    }
}
