package com.ecarx.systemui.plugin;

import android.app.UiModeManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.ecarx.systemui.plugin.navigationbar.NavigationBar;
import com.ecarx.systemui.plugin.navigationbar.NavigationBarView;
import com.ecarx.systemui.plugin.statusbar.StatusBar;
import com.ecarx.systemui.plugin.statusbar.StatusBarView;
import com.ecarx.systemui.plugin.utils.Lg;
import com.sensorsdata.analytics.android.autotrack.aop.SensorsDataAutoTrackHelper;

/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    private View navView;
    private ViewGroup navibarLayout;
    private View statusView;
    private ViewGroup statusbarLayout;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Log.e("gc222222222222", "onCreate:onCreate(Bundle savedInstanceState)");
        if (this.navView == null) {
            this.navView = createNavigationBarWidget(this, null);
            Log.e("gc2222222222222222", "");
        }
        if (this.statusView == null) {
            this.statusView = createSystemUIWidget(this, null);
            Log.e("========>", "");
        }
        this.navibarLayout = (ViewGroup) findViewById(R.id.navi_bar_layout);
        if (this.navView != null) {
            Lg.e("navView is not null");
            ViewGroup viewGroup = (ViewGroup) this.navView.getParent();
            this.navibarLayout.removeAllViews();
            this.navibarLayout.addView(this.navView);
        } else {
            Lg.e("navView is null");
        }
        this.statusbarLayout = (ViewGroup) findViewById(R.id.status_bar_layout);
        if (this.statusView != null) {
            Lg.e("statusView is not null");
            ViewGroup viewGroup2 = (ViewGroup) this.statusView.getParent();
            this.statusbarLayout.removeAllViews();
            this.statusbarLayout.addView(this.statusView);
        } else {
            Lg.e("statusView is null");
        }
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.ecarx.systemui.plugin.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.changeDayNightMode();
                SensorsDataAutoTrackHelper.trackViewOnClick(view);
            }
        });
    }

    public static View createNavigationBarWidget(Context context, Bundle bundle) {
        Lg.w("[JRSYS] systemUIPLugin createNavigationBarWidget invoked start");
        NavigationBarView rootView = NavigationBar.getInstance(context).getRootView();
        Lg.w("systemUIPLugin createNavigationBarWidget invoked " + rootView);
        return rootView;
    }

    public static View createSystemUIWidget(Context context, Bundle bundle) {
        Lg.w("[JRSYS] systemUIPLugin createSystemUIWidget invoked start");
        StatusBarView rootView = StatusBar.getInstance(context).getRootView();
        Lg.w("systemUIPLugin createSystemUIWidget invoked " + rootView);
        return rootView;
    }

    public void changeDayNightMode() {
        UiModeManager uiModeManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
        uiModeManager.enableCarMode(0);
        uiModeManager.setNightMode(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES ? UiModeManager.MODE_NIGHT_NO : UiModeManager.MODE_NIGHT_YES);
        ViewGroup viewGroup = this.navibarLayout;
        if (viewGroup != null && viewGroup.getChildCount() > 0) {
            this.navibarLayout.removeAllViews();
        }
        ViewGroup viewGroup2 = this.statusbarLayout;
        if (viewGroup2 == null || viewGroup2.getChildCount() <= 0) {
            return;
        }
        this.statusbarLayout.removeAllViews();
    }
}
