package com.ecarx.systemui.plugin.navigationbar;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.ecarx.systemui.plugin.statusbar.AdaptApiManager;
import com.ecarx.systemui.plugin.statusbar.TopBean;
import com.ecarx.systemui.plugin.utils.BusProvider;
import com.ecarx.systemui.plugin.utils.DisplayUtil;
import com.ecarx.systemui.plugin.utils.Lg;
import java.util.HashSet;

/* loaded from: classes.dex */
public class AppWatcherService extends AccessibilityService {
    public static final String APA_CLASSNAME = "com.ecarx.xcapa.MainApaActivity";
    public static final String APA_PACKAGENAME = "com.ecarx.apa";
    public static final String BTPHONE_CLASSNAME = "android.widget.FrameLayout";
    public static final String BTPHONE_PACKAGENAME = "com.ecarx.btphone";
    public static final String CARSETTINGS_CLASSNAME = "ecarx.settings.setting.CarSettingActivity";
    public static final String CAR_PACKAGENAME = "ecarx.settings";
    public static final String DIMMENU_CLASSNAME = "com.ecarx.dimmenu.MainActivity";
    public static final String DIMMENU_PACKAGENAME = "com.ecarx.dimmenu";
    public static final String HUD_CLASSNAME = "com.ecarx.hud.main.HUDActivity";
    public static final String HUD_PACKAGENAME = "com.ecarx.hud";
    public static final String HVAC_DIALOGNAME = "android.app.Dialog";
    public static final String HVAC_LAYOUTNAME = "android.widget.LinearLayout";
    public static final String HVAC_PACKAGENAME = "ecarx.hvac.app";
    public static final String LAUNCHER_CLASSNAME = "ecarx.launcher3.Launcher";
    public static final String LAUNCHER_PACKAGENAME = "ecarx.launcher3";
    public static final String MANUALMODEFORCSD_CLASSNAME = "ecarx.launcher3.smartcard.view.activity.ManualModeForCsd";
    public static final String MEDIACENTER_CLASSNAME = "ecarx.xsf.mediacenter.app.MediaCenterActivity";
    public static final String MEDIACENTER_PACKAGENAME = "ecarx.xsf.mediacenter";
    public static final String PANE_CLASSNAME = "ecarx.launcher3.AppPane";
    public static final String PARKING_CLASSNAME = "com.ecarx.parking.MainAvmActivity";
    public static final String PARKING_PACKAGENAME = "com.ecarx.parking";
    public static final String RECENT_CHANGE_INTENT = "com.ecarx.systemui.plugin.RECENT_CHANGE";
    public static final String RECENT_PACKAGES = "recentPackages";
    public static final String SYSTEMSETTINGS_CLASSNAME = "ecarx.settings.setting.SysSettingActivity";
    private static final String TAG = "AppWatcherService";
    public static final String WE_CHAT_CLASSNAME = "com.tencent.mm.ui.LauncherUI";
    public static final String WE_CHAT_PACKAGENAME = "com.tencent.mm";
    public static final String XIAOKAGUI_PACKAGENAME = "com.ecarx.xiaokagui";
    // its i do
    public static final String YANDEX_NAVI_PACKAGENAME = "ru.yandex.yandexnavi";
    private static HashSet<String> sBlackList;
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private static final Runnable mRunnable = new Runnable() { // from class: com.ecarx.systemui.plugin.navigationbar.-$$Lambda$AppWatcherService$vSSYQkdNEfNEedD9by3eagbIOOU
        @Override // java.lang.Runnable
        public final void run() {
            AppWatcherService.checkIsLauncher(false);
        }
    };
    private String cachePackageName = "";
    private String cacheClassName = "";

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Lg.w(TAG, "[JRSYS] onAccessibilityEvent:" + accessibilityEvent.getEventType());
        if (accessibilityEvent.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED || TextUtils.isEmpty(accessibilityEvent.getPackageName())) {
            return;
        }
        String charSequence = accessibilityEvent.getPackageName().toString();
        String charSequence2 = accessibilityEvent.getClassName().toString();
        Log.e("TTTTT", "assist：" + charSequence + "," + charSequence2);
        if (charSequence.equals(XIAOKAGUI_PACKAGENAME)) {
            return;
        }
        if (charSequence.equals(HVAC_PACKAGENAME)) {
            Log.e("TTTTT", "assist：" + charSequence + "," + charSequence2);
            if (this.cacheClassName.equals(HVAC_LAYOUTNAME) || !charSequence2.equals(HVAC_DIALOGNAME)) {
                BusProvider.p(new TopBean(false, false, false, false, false, false, true, null));
            }
        } else if (charSequence.equals(BTPHONE_PACKAGENAME) && charSequence2.equals(BTPHONE_CLASSNAME) && this.cachePackageName.equals(HVAC_PACKAGENAME)) {
            sHandler.removeCallbacks(mRunnable);
            BusProvider.p(new TopBean(false, false, false, false, false, false, true, null));
        } else {
            checkIsLauncher(true);
        }
        this.cachePackageName = charSequence;
        this.cacheClassName = charSequence2;
    }

    public static void checkIsLauncher(boolean z) {
        sHandler.removeCallbacks(mRunnable);
        ComponentName topActivityByDisplayId = DisplayUtil.getTopActivityByDisplayId(AdaptApiManager.getInstance().getCsdDisplayId());
        sHandler.postDelayed(mRunnable, 500L);
        if (topActivityByDisplayId != null) {
            Log.e(TAG, "top activity====" + topActivityByDisplayId + "=====firstCheck====" + z);
            if (topActivityByDisplayId.getPackageName().equals(MEDIACENTER_PACKAGENAME) && topActivityByDisplayId.getClassName().equals(MEDIACENTER_CLASSNAME)) {
                return;
            }
            if (topActivityByDisplayId.getPackageName().equals(HUD_PACKAGENAME) && topActivityByDisplayId.getClassName().equals(HUD_CLASSNAME)) {
                return;
            }
            if (topActivityByDisplayId.getPackageName().equals(DIMMENU_PACKAGENAME) && topActivityByDisplayId.getClassName().equals(DIMMENU_CLASSNAME)) {
                return;
            }
            if (topActivityByDisplayId.getPackageName().equals("ecarx.launcher3") && topActivityByDisplayId.getClassName().equals(LAUNCHER_CLASSNAME)) {
                Log.e(TAG, "firstCheck = true" + topActivityByDisplayId);
                BusProvider.p(new TopBean(true, false, false, false, false, false, false, topActivityByDisplayId));
                return;
            } else if (topActivityByDisplayId.getPackageName().equals("ecarx.launcher3") && topActivityByDisplayId.getClassName().equals(PANE_CLASSNAME)) {
                Log.e(TAG, "firstCheck = true");
                BusProvider.p(new TopBean(false, true, false, false, false, false, false, topActivityByDisplayId));
                return;
            } else if (topActivityByDisplayId.getPackageName().equals(CAR_PACKAGENAME) && topActivityByDisplayId.getClassName().equals(CARSETTINGS_CLASSNAME)) {
                Log.e(TAG, "firstCheck = true");
                BusProvider.p(new TopBean(false, false, true, false, false, false, false, topActivityByDisplayId));
                return;
            } else if (topActivityByDisplayId.getPackageName().equals(CAR_PACKAGENAME) && topActivityByDisplayId.getClassName().equals(SYSTEMSETTINGS_CLASSNAME)) {
                Log.e(TAG, "firstCheck = true");
                BusProvider.p(new TopBean(false, false, false, true, false, false, false, topActivityByDisplayId));
                return;
            } else if (topActivityByDisplayId.getPackageName().equals("ecarx.launcher3") && topActivityByDisplayId.getClassName().equals(MANUALMODEFORCSD_CLASSNAME)) {
                Log.e(TAG, "firstCheck = true");
                BusProvider.p(new TopBean(false, false, false, false, true, false, false, topActivityByDisplayId));
                return;
            } else if (topActivityByDisplayId.getPackageName().equals(WE_CHAT_PACKAGENAME) && topActivityByDisplayId.getClassName().equals(WE_CHAT_CLASSNAME)) {
                Log.e(TAG, "firstCheck = true");
                BusProvider.p(new TopBean(false, false, false, false, false, true, false, topActivityByDisplayId));
                return;
            }
        } else {
            Log.e(TAG, "componentName ==null");
        }
        Log.e(TAG, "firstCheck = false");
        BusProvider.p(new TopBean(false, false, false, false, false, false, false, topActivityByDisplayId));
    }

    @Override // android.app.Service
    public void onCreate() {
        Lg.i(TAG, "[JRSYS][onCreate]");
        super.onCreate();
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        super.onServiceConnected();
        Lg.w(TAG, " AppWatcherService onServiceConnected");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public static boolean isInnerApps(String str) {
        if (sBlackList == null) {
            HashSet<String> hashSet = new HashSet<>();
            sBlackList = hashSet;
            hashSet.add("com.ecarx.magicbook");
            sBlackList.add("com.ecarx.xiaoka");
            sBlackList.add(XIAOKAGUI_PACKAGENAME);
            sBlackList.add("com.ecarx.smartcloud");
            sBlackList.add("com.ecarx.multimedia");
            sBlackList.add("com.ecarx.thememanager");
            sBlackList.add("ecarx.calendar");
            sBlackList.add("ecarx.membercenter");
            sBlackList.add("com.desay_svautomotive.settings");
            sBlackList.add("com.neusoft.optimus.wheeljack.setting");
            sBlackList.add(CAR_PACKAGENAME);
            sBlackList.add("com.ecarx.appstore");
            sBlackList.add("ecarx.news");
            sBlackList.add("com.autonavi.amapauto");
            sBlackList.add("ecarx.violationinquiry");
            sBlackList.add("ecarx.autosecure");
            sBlackList.add(BTPHONE_PACKAGENAME);
            sBlackList.add("ecarx.gallery");
            sBlackList.add("com.ecarx.mycar");
        }
        return sBlackList.contains(str);
    }
}
