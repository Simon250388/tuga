package com.ecarx.systemui.plugin.navigationbar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ecarx.systemui.plugin.BaseBar;
import com.ecarx.systemui.plugin.PluginContext;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.statusbar.policy.UserController;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.SensorSDKWrapper;
import com.ecarx.systemui.plugin.utils.ThemeHelper;

/* loaded from: classes.dex */
public class NavigationBar implements BaseBar {
    private static final String TAG = NavigationBar.class.getSimpleName();
    private static volatile NavigationBar mInstance;
    private Context mContext;
    private NavigationBarView mRootView;

    private NavigationBar(Context context) {
        this.mContext = new PluginContext(context);
    }

    public static NavigationBar getInstance(Context context) {
        if (mInstance == null) {
            synchronized (NavigationBar.class) {
                if (mInstance == null) {
                    mInstance = new NavigationBar(context);
                    mInstance.init();
                }
            }
        }
        return mInstance;
    }

    private void init() {
        try {
            Lg.i(TAG, "[JRSYS--1][init]-----");
            SensorSDKWrapper.init(PluginContext.getHostContext());
            getRootView();
            UserController.getInstance();
            AppWatcherController.getInstance(this.mContext).init();
            ThemeHelper.getDefault().init(this.mContext);
        } catch (Throwable th) {
            Lg.e(TAG, th);
        }
    }

    @Override // com.ecarx.systemui.plugin.BaseBar
    public NavigationBarView getRootView() {
        Log.d("=====================================>", "mRootView: " + this.mRootView);
        if (this.mRootView == null) {
            this.mRootView = (NavigationBarView) LayoutInflater.from(this.mContext).inflate(R.layout.navigation_bar, (ViewGroup) null, false);
            Log.d("=====================================>", "mRootView: " + this.mRootView);
        }
        return this.mRootView;
    }

    @Override // com.ecarx.systemui.plugin.BaseBar
    public void destroy() {
        AppWatcherController.getInstance(this.mContext).destroy();
    }
}
