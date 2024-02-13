package com.ecarx.systemui.plugin.psd;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.ecarx.systemui.plugin.App;
import com.ecarx.systemui.plugin.NoDoubleClickListener;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.navigationbar.HvacController;
import com.ecarx.systemui.plugin.navigationbar.NavigationBar;
import com.ecarx.systemui.plugin.utils.AlphaImageView;
import com.ecarx.systemui.plugin.utils.CarUtils;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.UIModeModel;
import java.util.Locale;

/* loaded from: classes.dex */
public class PsdNavigationBarView extends FrameLayout implements View.OnSystemUiVisibilityChangeListener {
    private static final String PSD_HVAC_ACTION = "android.intent.action.PSD_HVAC_WIDGET";
    private static final String PSD_HVAC_FUNCTION = "createPsdHvacBarWidget";
    private AlphaImageView appPaneIv;
    private NoDoubleClickListener clickListener;
    private AlphaImageView homeIv;
    private FrameLayout hvacBar;
    private String lan;
    private Context mConfigurationContext;
    private Context mContext;
    private int mLastNavigationBarLightMode;
    private int mLastStatusBarLightMode;
    private FrameLayout mNavContent;
    private AlphaImageView sceneModeIv;
    private static final String TAG = PsdNavigationBarView.class.getSimpleName();
    public static final ComponentName PSD_LAUNCHER = new ComponentName("ecarx.launcher3", "ecarx.launcher3.psd.LauncherForPsd");
    public static final ComponentName PSD_APPPANE = new ComponentName("ecarx.launcher3", "ecarx.launcher3.psd.AppPaneForPsd");
    public static final ComponentName PSD_SCENEMODE = new ComponentName("ecarx.launcher3", "ecarx.launcher3.smartcard.view.activity.ManualModeForPsd");

    public PsdNavigationBarView(Context context) {
        this(context, null);
    }

    public PsdNavigationBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PsdNavigationBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLastStatusBarLightMode = 0;
        this.mLastNavigationBarLightMode = 0;
        this.clickListener = new NoDoubleClickListener() { // from class: com.ecarx.systemui.plugin.psd.PsdNavigationBarView.1
            @Override // com.ecarx.systemui.plugin.NoDoubleClickListener
            public void onNoDoubleClick(View view) {
                if (R.id.psd_navigation_bar_app_pane_iv == view.getId()) {
                    Lg.i(PsdNavigationBarView.TAG, "[JRSYS_PSD][onNoDoubleClick] appPaneIv");
                    PsdNavigationBarView.this.gotoAppPane();
                }
            }
        };
        this.mContext = context;
        this.mConfigurationContext = context;
        this.lan = Locale.getDefault().getLanguage();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mNavContent = (FrameLayout) findViewById(R.id.psd_navigation_bar_content);
        AlphaImageView alphaImageView = (AlphaImageView) findViewById(R.id.psd_navigation_bar_app_pane_iv);
        this.appPaneIv = alphaImageView;
        alphaImageView.setOnClickListener(this.clickListener);
        this.hvacBar = (FrameLayout) findViewById(R.id.psd_navigation_bar_hvac);
        Lg.i(TAG, "[JRSYS_PSD][onFinishInflate], to addHvacView.");
        HvacController.getInstance(App.getApp().getApplicationContext()).initHvac(this.hvacBar, PSD_HVAC_ACTION, PSD_HVAC_FUNCTION);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Lg.d(TAG, "[JRSYS_PSD] onDetachedFromWindow");
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
        Lg.d("[JRSYS_PSD][changNavigationBarUIMode] flag:" + i);
    }

    private void onUIModeChanged(int i) {
        String str = TAG;
        Lg.d(str, "[JRSYS_PSD][onUIModeChanged], flag: " + i);
        this.appPaneIv.setImageResource(R.drawable.ic_nav_home);
        this.mNavContent.setBackground(this.mContext.getDrawable(R.drawable.psd_nav_content_window_bg_new));
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        Lg.w("[JRSYS_PSD]onWindowSystemUiVisibilityChanged:" + i);
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public void onSystemUiVisibilityChange(int i) {
        String str = TAG;
        Lg.i(str, "[JRSYS_PSD]onSystemUiVisibilityChange diff=" + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoAppPane() {
        Intent intent = new Intent();
        intent.setComponent(PSD_APPPANE);
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchDisplayId(CarUtils.getInstance().getDisplayByType(102));
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        try {
            this.mContext.startActivity(intent, makeBasic.toBundle());
        } catch (Exception e) {
            String str = TAG;
            Log.w(str, "[JRSYS_PSD][gotoAppPane], e: " + Log.getStackTraceString(e));
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        changeUiMode(configuration);
    }

    public void changeUiMode(Configuration configuration) {
        int i = isLightMode(configuration.uiMode) ? 1 : 2;
        String str = TAG;
        Log.d(str, "[JRPSD_SYSTEM][changeUiMode]--1 :, statusBarMode: " + i + ", mLastStatusBarLightMode: " + this.mLastStatusBarLightMode + ", mLastNavigationBarLightMode: " + this.mLastNavigationBarLightMode + ", uiMode: " + (configuration.uiMode & 48) + ", newConfig: " + configuration);
        if (i != this.mLastNavigationBarLightMode) {
            changNavigationBarUIMode(i);
            this.mLastNavigationBarLightMode = i;
        }
        String language = Locale.getDefault().getLanguage();
        if (i == this.mLastStatusBarLightMode && this.lan.equals(language)) {
            return;
        }
        View rootView = PsdNavigationController.getInstance().getRootView(true);
        if (rootView != null && (rootView instanceof PsdStatusBarView)) {
            ((PsdStatusBarView) rootView).changeStatusBarUIMode(i);
            this.mLastStatusBarLightMode = i;
        }
        this.lan = language;
    }

    private boolean isLightMode(int i) {
        boolean z = (i & 16) != 0;
        String str = TAG;
        Log.d(str, "[JRPSD_SYSTEM][changeUiMode][isLightMode]--1 :, configMode: " + i + ", Configuration.UI_MODE_NIGHT_NO: 16, UI_MODE_NIGHT_YES: 32, isLight: " + z);
        return z;
    }
}
