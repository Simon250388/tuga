package com.ecarx.systemui.plugin.statusbar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.navigationbar.AppWatcherService;
import com.ecarx.systemui.plugin.navigationbar.NavigationBar;
import com.ecarx.systemui.plugin.navigationbar.NavigationBarView;
import com.ecarx.systemui.plugin.utils.BusProvider;
import com.ecarx.systemui.plugin.utils.IntentConstants;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.UIModeModel;

import java.util.Locale;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class StatusBarView extends RelativeLayout {
    private static final String ACTION_NAVIGATION_BAR_STATUS = "ecarx.intent.action.NAVIGATION_BAR_STATUS";
    private static final ComponentName APPPANE_ASSIST_COMPONENT = new ComponentName("ecarx.launcher3", "ecarx.launcher3.apppane.AppPaneAssistActivity");
    public static final boolean ATLEAST_M;
    public static final boolean ATLEAST_O;
    private static final String MASCOT_PACKAGENAME = "com.ecarx.xcmascotui";
    private static final int SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR_BEFORE_ANDROID_O = 16;
    private static final int SYSTEM_UI_FLAG_LIGHT_STATUS_BAR_BEFORE_ANDROID_M = 8;
    static final String TAG = "EcarxStatusBarView";
    private TextView clock;
    private String language;
    public Boolean lastIsAppPane;
    public Boolean lastIsLauncher;
    private Context mConfigurationContext;
    private Context mContext;
    private ComponentName mLastActivity;
    private int mLastNavigationBarLightMode;
    private int mLastStatusBarLightMode;
    private SystemIconContainer systemIconContainer;

    static {
        ATLEAST_M = Build.VERSION.SDK_INT >= 23;
        ATLEAST_O = Build.VERSION.SDK_INT >= 26;
    }

    public StatusBarView(Context context) {
        this(context, null);
    }

    public StatusBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StatusBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLastStatusBarLightMode = 0;
        this.mLastNavigationBarLightMode = 0;
        this.mConfigurationContext = context;
        this.mContext = context;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.clock = (TextView) findViewById(R.id.status_bar_clock);
        this.systemIconContainer = (SystemIconContainer) findViewById(R.id.darea_icons_container);
        BusProvider.r(this);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        refreshUI(this.lastIsLauncher.booleanValue());
        changeUiMode(configuration);
    }

    public void changeUiMode(Configuration configuration) {
        NavigationBarView rootView;
        int i = isLightMode(configuration.uiMode) ? 1 : 2;
        Log.d(TAG, "[JRPSD_SYSTEM][changeUiMode]--1 :, statusBarMode: " + i + ", mLastStatusBarLightMode: " + this.mLastStatusBarLightMode + ", mLastNavigationBarLightMode: " + this.mLastNavigationBarLightMode + ", uiMode: " + (configuration.uiMode & 48) + ", newConfig: " + configuration);
        if (i != this.mLastStatusBarLightMode) {
            changeStatusBarUIMode(i);
            this.mLastStatusBarLightMode = i;
        }
        if (i == this.mLastNavigationBarLightMode || (rootView = NavigationBar.getInstance(this.mConfigurationContext).getRootView()) == null) {
            return;
        }
        rootView.changNavigationBarUIMode(i);
        this.mLastNavigationBarLightMode = i;
    }

    private boolean isLightMode(int i) {
        boolean z = (i & 16) != 0;
        Log.d(TAG, "[JRPSD_SYSTEM][changeUiMode][isLightMode]--1 :, configMode: " + i + ", Configuration.UI_MODE_NIGHT_NO: 16, UI_MODE_NIGHT_YES: 32, isLight: " + z);
        return z;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onEventMainThread(new TopBean(true, false, false, false, false, false, false, null));
    }

    @Override // android.view.View
    public void setSystemUiVisibility(int i) {
        Lg.d(TAG, "setSystemUiVisibility: flag : " + Integer.toHexString(i) + " isLightStatusBar:" + isLightStatusBar(i) + " isLightNavigationBar:" + isLightNavigationBar(i));
        sendSystemUiVisiblityToMascot(i);
        NavigationBarView rootView = NavigationBar.getInstance(this.mConfigurationContext).getRootView();
        if (rootView.getHvacContent() != null) {
            rootView.getHvacContent().setSystemUiVisibility(i);
        }
    }

    private void sendSystemUiVisiblityToMascot(int i) {
        int i2 = i & 2;
        boolean z = i2 != 0;
        Intent intent = new Intent(ACTION_NAVIGATION_BAR_STATUS);
        intent.setPackage(MASCOT_PACKAGENAME);
        intent.putExtra("NAVIGATION_BAR_STATUS", z);
        Lg.d(TAG, "[JRSYS][sendSystemUiVisiblityToMascot]: flag : " + Integer.toHexString(i) + ", visibility & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION: " + i2 + ", visibility & View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION: " + (i & 512) + " navigationHide:" + z);
        this.mContext.startService(intent);
        AppWatcherService.checkIsLauncher(true);
    }

    private boolean isLightStatusBar(int i) {
        return ATLEAST_M ? (i & 8192) == 8192 : (i & 8) == 8;
    }

    private boolean isLightNavigationBar(int i) {
        return ATLEAST_O ? (i & 16) == 16 : (i & 16) == 16;
    }

    public void changeStatusBarUIMode(int i) {
        Context configurationContext = UIModeModel.setConfigurationContext(this.mContext, i, 0);
        this.mConfigurationContext = configurationContext;
        onUIModeChanged(configurationContext);
        this.systemIconContainer.onUIModeChanged(this.mConfigurationContext);
    }

    public void onUIModeChanged(Context context) {
        this.language = Locale.getDefault().getLanguage();
        try {
            if (this.mConfigurationContext != null && this.mConfigurationContext.getResources() != null) {
                Lg.i(TAG, "[JRSYS][onUIModeChanged]--1, mConfigurationContext: " + this.mConfigurationContext + ", mConfigurationContext res: " + this.mConfigurationContext.getResources() + ", package: " + this.mConfigurationContext.getPackageName());
                this.clock.setTextColor(this.mConfigurationContext.getResources().getColor(R.color.status_bar_text_color));
            } else {
                Lg.i(TAG, "[JRSYS][onUIModeChanged]--2, mContext: " + this.mContext + ", mContext res: " + this.mContext.getResources() + ", package: " + this.mContext.getPackageName());
                this.clock.setTextColor(this.mContext.getResources().getColor(R.color.status_bar_text_color));
            }
        } catch (Exception e) {
            Lg.w(TAG, "[JRSYS][onUIModeChanged], e: " + Log.getStackTraceString(e));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        StatusBar.getInstance(this.mConfigurationContext).destroy();
        BusProvider.u(this);
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Lg.w("status bar view dispatchTouchEvent ");
        return super.dispatchTouchEvent(motionEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(TopBean topBean) {
        Lg.i(TAG, "[JRSYS_StatusBar][onEventMainThread]--1, topBean: " + topBean);
        updateInHome(topBean);
    }

    private void refreshUI(boolean z) {
        String language = Locale.getDefault().getLanguage();
        if (z) {
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.status_bar_home_padding_right);
            if (language.equals("ar")) {
                setPadding(dimensionPixelSize, 0, 0, 0);
                return;
            } else {
                setPadding(0, 0, dimensionPixelSize, 0);
                return;
            }
        }
        setPadding(0, 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.status_bar_nothome_padding_right), 0);
    }

    public void updateInHome(TopBean topBean) {
        refreshUI(topBean.isLauncher);
        Boolean bool = this.lastIsLauncher;
        if (bool == null || bool.booleanValue() != topBean.isLauncher) {
            Intent intent = new Intent(IntentConstants.ACTION_IS_LAUNCHER);
            intent.putExtra("isLauncher", topBean.isLauncher);
            getContext().sendBroadcast(intent);
        }
        this.lastIsLauncher = Boolean.valueOf(topBean.isLauncher);
        Boolean bool2 = this.lastIsAppPane;
        if (bool2 == null || bool2.booleanValue() != topBean.isPane) {
            Intent intent2 = new Intent(IntentConstants.ACTION_IS_APP_PANE);
            intent2.putExtra("is_app_pane", topBean.isPane);
            this.mContext.sendBroadcast(intent2);
        }
        this.lastIsAppPane = Boolean.valueOf(topBean.isPane);
        if (topBean.isHvac) {
            Intent intent3 = new Intent(IntentConstants.ACTION_IS_HVAC);
            intent3.putExtra("is_hvac", topBean.isHvac);
            this.mContext.sendBroadcast(intent3);
        }
        if (topBean.isPane || topBean.isCar || topBean.isSys || topBean.isManualMode || topBean.isWeChat) {
            setAlpha(0.0f);
        } else {
            setAlpha(1.0f);
        }
    }
}
