package com.ecarx.systemui.plugin.navigationbar;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecarx.systemui.plugin.NoDoubleClickListener;
import com.ecarx.systemui.plugin.PluginContext;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.compat.LauncherActivityInfoCompat;
import com.ecarx.systemui.plugin.compat.LauncherAppsCompat;
import com.ecarx.systemui.plugin.compat.UserHandleCompat;
import com.ecarx.systemui.plugin.navigationbar.recyclerview.RecentAdapter;
import com.ecarx.systemui.plugin.utils.AlphaImageView;
import com.ecarx.systemui.plugin.utils.AppExecutors;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.LogoutTouristDialog;
import com.ecarx.systemui.plugin.utils.ShortUtils;
import com.ecarx.systemui.plugin.utils.StatisticsUtil;
import com.ecarx.systemui.plugin.utils.UIModeModel;
import com.ecarx.systemui.plugin.utils.Utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class NavigationBarView extends FrameLayout implements View.OnSystemUiVisibilityChangeListener {
    private static final String CSD_HVAC_ACTION = "android.intent.action.HVAC_WIDGET";
    private static final String CSD_HVAC_FUNCTION = "createHvacBarWidget";
    private static final String CSD_SETTINGS_CAR_SETTINGS = "ecarx.intent.action.ECARX_LAUNCH_APP_CAR_SETTINGS";
    private static final String CSD_SETTINGS_CAR_SETTINGS_CLOSE = "ecarx.intent.action.ECARX_LAUNCH_APP_CAR_SETTINGS_CLOSE";
    private static final String DISMISS_DRIVING_MODE_KEY = "requestDismiss";
    private static final String DISSMIS_DRIVING_MODE_ACTION = "ecarx.settings.ACTION_DRIVE_MODE";
    public static final String IS_TOURIST_MODEL = "com.android.provision.model.action.istourist";
    private static final int MSG_CHANGE_IMG = 1048578;
    private static final int MSG_CHANGE_IMG_AV = 1048579;
    private static final String TAG = NavigationBarView.class.getSimpleName();
    private AlphaImageView appPaneIv;
    private NoDoubleClickListener clickListener;
    private Handler handler;
    private AlphaImageView homeIv;
    private FrameLayout hvacBar;
    private FrameLayout mCarGoBack;
    private FrameLayout openNavi;
    private View hvacContent;
    private boolean isTourist;
    private LinkedList<String> list;
    private AlphaImageView mCarIv;
    private Context mConfigurationContext;
    private Context mContext;
    private IntentFilter mFilter;
    private LinearLayoutManager mLayoutManager;
    private LogoutTouristDialog mLogoutTouristDialog;
    private FrameLayout mNavContent;
    private ContentObserver mScreenshotButtonShowObserver;
    private AlphaImageView mUserIv;
    private RecentAdapter recentAdapter;
    private RecyclerView recentRecyclerView;

    public NavigationBarView(Context context) {
        this(context, null);
    }

    public NavigationBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFilter = null;
        this.isTourist = false;
        this.list = new LinkedList<>();
        this.handler = new Handler() { // from class: com.ecarx.systemui.plugin.navigationbar.NavigationBarView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                String str = NavigationBarView.TAG;
                Lg.d(str, "[JRSYS][HandlerLeak] msgwhat" + message.what);
                int i2 = message.what;
            }
        };
        this.mScreenshotButtonShowObserver = new ContentObserver(this.handler) { // from class: com.ecarx.systemui.plugin.navigationbar.NavigationBarView.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                String str = NavigationBarView.TAG;
                Lg.d(str, "[JRSYS][ContentObserver] value" + Settings.Global.getInt(NavigationBarView.this.mContext.getContentResolver(), "device_provisioned", 0) + "");
                int i2 = Settings.Global.getInt(NavigationBarView.this.mContext.getContentResolver(), "device_provisioned", 0);
                if (i2 == 0) {
                    NavigationBarView.this.handler.sendEmptyMessageDelayed(NavigationBarView.MSG_CHANGE_IMG_AV, 300L);
                    NavigationBarView.this.isTourist = false;
                } else if (i2 == 2) {
                    NavigationBarView.this.isTourist = true;
                    NavigationBarView.this.handler.sendEmptyMessageDelayed(NavigationBarView.MSG_CHANGE_IMG, 300L);
                } else {
                    NavigationBarView.this.isTourist = false;
                }
            }
        };
        this.clickListener = new NoDoubleClickListener() { // from class: com.ecarx.systemui.plugin.navigationbar.NavigationBarView.4
            @Override // com.ecarx.systemui.plugin.NoDoubleClickListener
            public void onNoDoubleClick(View view) {
                NavigationBarView.this.dismissDrivingMode();
                if (R.id.navigation_bar_home_iv == view.getId()) {
                    Lg.i(NavigationBarView.TAG, "[JRSYS][onNoDoubleClick] home");
                    StatisticsUtil.recoardSensorsEvent(StatisticsUtil.SENSORS_EVENT_HOME_CLK);
                    NavigationBarView.this.gotoHome();
                    NavigationBarView.this.closeHavc();
                } else if (R.id.navigation_bar_app_pane_iv == view.getId()) {
                    Lg.i(NavigationBarView.TAG, "[JRSYS][onNoDoubleClick] appPaneIv");
                    StatisticsUtil.recoardSensorsEvent(StatisticsUtil.SENSORS_EVENT_APPPANE_CLK);
                    NavigationBarView.this.closeCarSetting();
                    NavigationBarView.this.closeHavc();
                    NavigationBarView.this.gotoAppPane();
                } else if (R.id.navigation_bar_car_iv == view.getId()) {
                    Lg.i(NavigationBarView.TAG, "[JRSYS][onNoDoubleClick] mCarIv");
                    StatisticsUtil.recoardSensorsEvent(StatisticsUtil.SENSORS_EVENT_BACK_CLK);
                    NavigationBarView.this.closeHavc();
                    NavigationBarView.this.closeAppPane();
                    NavigationBarView.this.gotoCar();
                }
                else if (R.id.navigation_bar_go_back == view.getId()) {
                    NavigationBarView.this.gotoBack();
                } else if (R.id.navigation_bar_go_to_navi == view.getId()) {
                    NavigationBarView.this.openNavi();
                }
            }
        };
        this.mContext = context;
        this.mConfigurationContext = context;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Lg.i(TAG, "[JRSYS][onFinishInflate]");
        this.mNavContent = findViewById(R.id.navigation_bar_content);
        AlphaImageView alphaImageView = findViewById(R.id.navigation_bar_home_iv);
        this.homeIv = alphaImageView;
        alphaImageView.setOnClickListener(this.clickListener);
        AlphaImageView alphaImageView2 = findViewById(R.id.navigation_bar_app_pane_iv);
        this.appPaneIv = alphaImageView2;
        alphaImageView2.setOnClickListener(this.clickListener);
        AlphaImageView alphaImageView3 = findViewById(R.id.navigation_bar_car_iv);
        this.mCarIv = alphaImageView3;
        alphaImageView3.setOnClickListener(this.clickListener);
        this.hvacBar = findViewById(R.id.navigation_bar_hvac);
        this.recentRecyclerView = findViewById(R.id.nav_recent_rv);
        // its i do
        this.mCarGoBack = findViewById(R.id.navigation_bar_go_back);
        this.mCarGoBack.setOnClickListener(this.clickListener);
        this.openNavi = findViewById(R.id.navigation_bar_app_pane_iv);
        this.openNavi.setOnClickListener(this.clickListener);

        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("device_provisioned"), false, this.mScreenshotButtonShowObserver);
        HvacController.getInstance(PluginContext.getHostContext()).initHvac(this.hvacBar, CSD_HVAC_ACTION, CSD_HVAC_FUNCTION);
    }

    public ImageView getUserView() {
        return this.mUserIv;
    }

    public View getHvacContent() {
        return this.hvacContent;
    }

    public void refreshRecentIcon(List<String> list) {
        RecentAdapter recentAdapter = this.recentAdapter;
        if (recentAdapter == null) {
            RecentAdapter recentAdapter2 = new RecentAdapter(list);
            this.recentAdapter = recentAdapter2;
            this.recentRecyclerView.setAdapter(recentAdapter2);
            this.mLayoutManager = new LinearLayoutManager(this.mContext, RecyclerView.HORIZONTAL, false);
            final int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.app_icon_space);
            final int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.app_icon_margin_end);
            this.recentRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.ecarx.systemui.plugin.navigationbar.NavigationBarView.3
                @Override // android.support.v7.widget.RecyclerView.ItemDecoration
                public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                    if (NavigationBarView.this.recentAdapter.getItemCount() <= 1) {
                        rect.set(0, 0, dimensionPixelSize2, 0);
                    } else if (childAdapterPosition != 0) {
                        if (childAdapterPosition == NavigationBarView.this.recentAdapter.getItemCount() - 1) {
                            rect.set(dimensionPixelSize, 0, dimensionPixelSize2, 0);
                            return;
                        }
                        int i = dimensionPixelSize;
                        rect.set(i, 0, i, 0);
                    } else {
                        rect.set(0, 0, 0, 0);
                    }
                }
            });
            this.recentRecyclerView.setLayoutManager(this.mLayoutManager);
            return;
        }
        recentAdapter.refresh(list);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Lg.d(TAG, "onDetachedFromWindow");
        NavigationBar.getInstance(this.mConfigurationContext).destroy();
        super.onDetachedFromWindow();
        setOnSystemUiVisibilityChangeListener(null);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnSystemUiVisibilityChangeListener(this);
    }

    public void changNavigationBarUIMode(int i) {
        this.mConfigurationContext = UIModeModel.setConfigurationContext(this.mContext, i, 1);
        onUIModeChanged(i);
        Lg.d("changNavigationBarUIMode:" + i);
    }

    private void onUIModeChanged(int i) {
        String str = TAG;
        Lg.d(str, "[JRSYS][onUIModeChanged], flag: " + i + "isTourist" + this.isTourist);
        AlphaImageView alphaImageView = this.mCarIv;
        if (alphaImageView != null) {
            alphaImageView.setImageResource(R.drawable.ic_nav_car);
        }
        AlphaImageView alphaImageView2 = this.homeIv;
        if (alphaImageView2 != null) {
            alphaImageView2.setImageResource(R.drawable.ic_nav_home);
        }
        AlphaImageView alphaImageView3 = this.appPaneIv;
        if (alphaImageView3 != null) {
            alphaImageView3.setImageResource(R.drawable.ic_nav_apppane);
        }
        this.mNavContent.setBackground(this.mContext.getDrawable(R.drawable.nav_content_window_bg_new));
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        Lg.w("onWindowSystemUiVisibilityChanged:" + i);
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public void onSystemUiVisibilityChange(int i) {
        String str = TAG;
        Lg.i(str, "onSystemUiVisibilityChange diff=" + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissDrivingMode() {
        Intent intent = new Intent(DISSMIS_DRIVING_MODE_ACTION);
        intent.putExtra(DISMISS_DRIVING_MODE_KEY, true);
        intent.setPackage(AppWatcherService.CAR_PACKAGENAME);
        try {
            this.mContext.startService(intent);
        } catch (Exception e) {
            Lg.e(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoHome() {
        Lg.i(TAG, "[JRSYS] gotoHome");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("ecarx.launcher3", AppWatcherService.LAUNCHER_CLASSNAME));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            Lg.e(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoAppPane() {
        Lg.i(TAG, "[JRSYS] gotoAppPane");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("ecarx.launcher3", AppWatcherService.PANE_CLASSNAME));
        intent.putExtra(Utilities.EXIT_TYPE, 4);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            String str = TAG;
            Lg.e(str, "start app pane failed : " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeHavc() {
        Lg.i(TAG, "[JRSYS] closeHavc");
        Intent intent = new Intent();
        intent.setAction("ecarx.hvac.app.HvacAppService");
        intent.setPackage(AppWatcherService.HVAC_PACKAGENAME);
        intent.putExtra("closeHvac", 1);
        this.mContext.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint("WrongConstant")
    public void closeCarSetting() {
        Lg.i(TAG, "[JRSYS] closeCarSetting");
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(AppWatcherService.CAR_PACKAGENAME);
        ComponentName componentName = new ComponentName(AppWatcherService.CAR_PACKAGENAME, AppWatcherService.CARSETTINGS_CLASSNAME);
        if (launchIntentForPackage != null) {
            try {
                launchIntentForPackage.setComponent(componentName).addCategory("android.intent.category.LAUNCHER").addFlags(270532608);
                launchIntentForPackage.setAction(CSD_SETTINGS_CAR_SETTINGS_CLOSE);
            } catch (Exception e) {
                String str = TAG;
                Lg.e(str, "[JRSYS] start car failed : " + e.getMessage());
                return;
            }
        }
        if (isForeground("CarSettingActivity")) {
            this.mContext.startActivity(launchIntentForPackage);
        }
    }

    // its i do
    public void openNavi() {
        Lg.i(TAG, "[JRSYS] openNavi");
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(AppWatcherService.YANDEX_NAVI_PACKAGENAME);
        this.mContext.startActivity(launchIntentForPackage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeAppPane() {
        Lg.i(TAG, "[JRSYS] closeAppPane");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("ecarx.launcher3", AppWatcherService.PANE_CLASSNAME));
        intent.putExtra(Utilities.EXIT_TYPE, 3);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            if (canOpenAppPane()) {
                return;
            }
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            String str = TAG;
            Lg.e(str, "start app pane failed : " + e.getMessage());
        }
    }

    private boolean isForeground(String str) {
        if (this.mContext != null && !TextUtils.isEmpty(str)) {
            for (ActivityManager.RunningTaskInfo runningTaskInfo : ((ActivityManager) this.mContext.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1)) {
                if (runningTaskInfo.topActivity.getShortClassName().contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canOpenAppPane() {
        List<String> topProcess = Utilities.getTopProcess(this.mContext);
        boolean z = true;
        if (topProcess.size() > 0) {
            for (String str : topProcess) {
                if (Utilities.APPPANE_PROCESS.equals(str)) {
                    z = false;
                }
            }
        }
        String str2 = TAG;
        Log.w(str2, "[JRSYS][canOpen], Utilities.APPPANE_PROCESS: ecarx.launcher3:appsvr, canOpen: " + z);
        return z;
    }

    private void gotoBack() {
        Lg.i(TAG, "[JRSYS][gotoBack]--1");
        final Instrumentation instrumentation = new Instrumentation();
        AppExecutors.getInstance().diskIO().execute(new Runnable() { // from class: com.ecarx.systemui.plugin.navigationbar.NavigationBarView.5
            @Override // java.lang.Runnable
            public void run() {
                Lg.i(NavigationBarView.TAG, "[JRSYS][gotoBack]--NavigationBar back send KeyEvent.KEYCODE_BACK");
                instrumentation.sendKeyDownUpSync(4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint("WrongConstant")
    public void gotoCar() {
        Lg.i(TAG, "[JRSYS][gotoCar]--1");
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(AppWatcherService.CAR_PACKAGENAME);
        launchIntentForPackage.setComponent(new ComponentName(AppWatcherService.CAR_PACKAGENAME, AppWatcherService.CARSETTINGS_CLASSNAME)).addCategory("android.intent.category.LAUNCHER").addFlags(270532608);
        launchIntentForPackage.setAction(CSD_SETTINGS_CAR_SETTINGS);
        try {
            this.mContext.startActivity(launchIntentForPackage);
        } catch (Exception e) {
            String str = TAG;
            Lg.e(str, "[JRSYS] start car failed : " + e.getMessage());
        }
        ShortUtils.addShortListToDb(this.mContext, "", AppWatcherService.CAR_PACKAGENAME, AppWatcherService.CARSETTINGS_CLASSNAME);
    }

    @SuppressLint("WrongConstant")
    private void gotoMemberCenter() {
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage("ecarx.membercenter");
        launchIntentForPackage.setAction("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").addFlags(270532608);
        String str = TAG;
        Lg.i(str, "[JRSYS][gotoCar]--1, intent: " + launchIntentForPackage);
        try {
            this.mContext.startActivity(launchIntentForPackage);
        } catch (Exception e) {
            Lg.e(e);
        }
    }

    private void updateRecentViews(ComponentName componentName) {
        if (componentName == null || componentName.getPackageName() == null) {
            Lg.i(TAG, "[JRSYS][updateRecentViews] is null.");
            return;
        }
        String packageName = componentName.getPackageName();
        String str = TAG;
        Lg.i(str, "[JRSYS][updateRecentViews], topActivity: " + componentName + ", cur List: " + this.list.toString());
        if (this.list.size() > 0) {
            if (this.list.get(0).equalsIgnoreCase(packageName)) {
                return;
            }
            if (isShowInRecent(this.list.get(0))) {
                if (this.list.size() > 1 && this.list.get(0).equalsIgnoreCase(this.list.get(1))) {
                    String str2 = TAG;
                    Lg.i(str2, "[JRSYS][updateRecentViews] [0, 1] eqauls: " + this.list.toString());
                    this.list.add(0, packageName);
                    this.list.remove(1);
                    return;
                }
                this.list.add(0, packageName);
                if (this.list.size() > 3) {
                    this.list.remove(3);
                }
                String str3 = TAG;
                Lg.w(str3, "[JRSYS][updateRecentViews] change recentPackages:" + this.list.toString());
                ArrayList arrayList = new ArrayList(this.list);
                if (arrayList.size() > 0) {
                    arrayList.remove(0);
                    refreshRecentIcon(arrayList);
                    return;
                }
                return;
            }
            this.list.remove(0);
            this.list.add(0, packageName);
            return;
        }
        this.list.add(0, packageName);
    }

    private boolean isShowInRecent(String str) {
        boolean z = false;
        if (str == null) {
            return false;
        }
        List<LauncherActivityInfoCompat> activityList = LauncherAppsCompat.getInstance(this.mContext).getActivityList(str, UserHandleCompat.myUserHandle());
        for (LauncherActivityInfoCompat launcherActivityInfoCompat : activityList) {
            ComponentName componentName = launcherActivityInfoCompat.getComponentName();
            String str2 = TAG;
            Lg.w(str2, "[JRSYS][isShowInRecent], componentName: " + componentName + ", apps size: " + activityList.size());
            if (componentName != null && str.contains(componentName.getPackageName()) && !"ecarx.launcher3".equals(str)) {
                z = true;
            }
        }
        String str3 = TAG;
        Lg.w(str3, "[JRSYS][isShowInRecent], apps size: " + activityList.size() + ", packageName: " + str + ", canShowInLauncher: " + z);
        return z;
    }
}
