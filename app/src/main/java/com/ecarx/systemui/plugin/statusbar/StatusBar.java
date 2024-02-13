package com.ecarx.systemui.plugin.statusbar;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ecarx.systemui.plugin.BaseBar;
import com.ecarx.systemui.plugin.PluginContext;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.statusbar.policy.BluetoothController;
import com.ecarx.systemui.plugin.statusbar.policy.CarController;
import com.ecarx.systemui.plugin.statusbar.policy.DVRController;
import com.ecarx.systemui.plugin.statusbar.policy.IntelligentKeyController;
import com.ecarx.systemui.plugin.statusbar.policy.MediaController;
import com.ecarx.systemui.plugin.statusbar.policy.MessageController;
import com.ecarx.systemui.plugin.statusbar.policy.NetworkController;
import com.ecarx.systemui.plugin.utils.APKVersionInfoUtils;
import com.ecarx.systemui.plugin.utils.CarUtils;
import com.ecarx.systemui.plugin.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class StatusBar implements BaseBar {
    private static final String TAG = StatusBar.class.getSimpleName();
    private static volatile StatusBar mInstance;
    private Context mContext;
    private List<BaseController> mControllerList;
    private NetworkController mNetworkController;
    private StatusBarView mRootView;
    private SystemIconContainer mSystemIconContainer;

    private StatusBar(Context context) {
        this.mContext = new PluginContext(context);
        start();
    }

    public static StatusBar getInstance(Context context) {
        if (mInstance == null) {
            synchronized (StatusBar.class) {
                if (mInstance == null) {
                    mInstance = new StatusBar(context);
                }
            }
        }
        return mInstance;
    }

    private void start() {
        Log.i(TAG, "[JRSYS_RESTART][JRSYS_NET][start]");
        CarUtils.initCarUtils(this.mContext);
        StatusBarView statusBarView = (StatusBarView) LayoutInflater.from(this.mContext).inflate(R.layout.status_bar, (ViewGroup) null, false);
        this.mRootView = statusBarView;
        SystemIconContainer systemIconContainer = (SystemIconContainer) statusBarView.findViewById(R.id.darea_icons_container);
        this.mSystemIconContainer = systemIconContainer;
        StatusBarManager.init(this.mContext, systemIconContainer);
        this.mRootView.onUIModeChanged(this.mContext);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.postMessage.count");
        Context context = this.mContext;
        context.registerReceiver(MessageController.getInstance(context).getBroadcastReceiver(), intentFilter);
        initController();
        LogUtil.d("SystemUIPlugin VersionCode：" + APKVersionInfoUtils.getVersionCode(this.mContext));
        LogUtil.d("SystemUIPlugin VersionName：" + APKVersionInfoUtils.getVersionName(this.mContext));
    }

    public void initController() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("[JRSYS_RESTART][JRSYS_NET][initController], mControllerList: ");
        sb.append(this.mControllerList);
        sb.append(", mControllerList size: ");
        List<BaseController> list = this.mControllerList;
        sb.append(list == null ? "null" : Integer.valueOf(list.size()));
        Log.i(str, sb.toString());
        List<BaseController> list2 = this.mControllerList;
        if (list2 == null) {
            this.mControllerList = new ArrayList();
        } else if (!list2.isEmpty()) {
            return;
        }
        this.mControllerList.add(BluetoothController.getInstance(this.mContext));
        this.mControllerList.add(MediaController.getInstance(this.mContext));
        this.mControllerList.add(CarController.getInstance(this.mContext));
        this.mControllerList.add(DVRController.getInstance(this.mContext));
        this.mControllerList.add(MessageController.getInstance(this.mContext));
        this.mControllerList.add(IntelligentKeyController.getInstance(this.mContext));
    }

    public NetworkController getNetworkController() {
        return this.mNetworkController;
    }

    @Override // com.ecarx.systemui.plugin.BaseBar
    public StatusBarView getRootView() {
        return this.mRootView;
    }

    @Override // com.ecarx.systemui.plugin.BaseBar
    public void destroy() {
        Log.i(TAG, "[JRSYS_RESTART][JRSYS_NET][destroy]");
        List<BaseController> list = this.mControllerList;
        if (list != null) {
            try {
                for (BaseController baseController : list) {
                    baseController.destroy();
                }
            } catch (Exception e) {
                String str = TAG;
                Log.e(str, "[JRSYS_StatusBar][destroy] e:" + e.getMessage());
            }
            this.mControllerList.clear();
        }
        mInstance = null;
        Context context = this.mContext;
        context.unregisterReceiver(MessageController.getInstance(context).getBroadcastReceiver());
    }
}
