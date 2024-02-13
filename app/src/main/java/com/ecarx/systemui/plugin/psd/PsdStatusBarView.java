package com.ecarx.systemui.plugin.psd;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.policy.PsdBluetoothController;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.SystemIconContainer;
import com.ecarx.systemui.plugin.statusbar.policy.NetworkController;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.UIModeModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class PsdStatusBarView extends RelativeLayout {
    private static final String TAG = PsdStatusBarView.class.getSimpleName();
    private String lan;
    private TextView mClock;
    private Context mConfigurationContext;
    private Context mContext;
    private List<BaseController> mControllerList;
    private NetworkController mNetworkController;
    private SystemIconContainer mSystemIconContainer;

    public PsdStatusBarView(Context context) {
        this(context, null);
    }

    public PsdStatusBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PsdStatusBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.lan = Locale.getDefault().getLanguage();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i(TAG, "[JRSYS_PSD][onFinishInflate]");
        this.mClock = (TextView) findViewById(R.id.psd_status_bar_clock);
        SystemIconContainer systemIconContainer = (SystemIconContainer) findViewById(R.id.psd_darea_icons_container);
        this.mSystemIconContainer = systemIconContainer;
        StatusBarManager.init(this.mContext, systemIconContainer);
        initPsdController();
    }

    public void initPsdController() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("[JRSYS_PSD][initController], mControllerList: ");
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
        this.mControllerList.add(PsdBluetoothController.getInstance());
    }

    public void changeStatusBarUIMode(int i) {
        Context configurationContext = UIModeModel.setConfigurationContext(this.mContext, i, 0);
        this.mConfigurationContext = configurationContext;
        onUIModeChanged(configurationContext);
        this.mSystemIconContainer.onUIModeChanged(this.mConfigurationContext);
    }

    private void onUIModeChanged(Context context) {
        try {
            if (this.mConfigurationContext != null && this.mConfigurationContext.getResources() != null) {
                this.mClock.setTextColor(this.mConfigurationContext.getResources().getColor(R.color.status_bar_text_color));
            } else {
                this.mClock.setTextColor(this.mContext.getResources().getColor(R.color.status_bar_text_color));
            }
            String language = Locale.getDefault().getLanguage();
            if (this.lan.equals(language)) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            if (!DateFormat.is24HourFormat(context)) {
                simpleDateFormat = new SimpleDateFormat("hh:mm a");
            }
            String format = simpleDateFormat.format(new Date(currentTimeMillis));
            Log.d("gcgcgc", "t1 : " + format);
            this.mClock.setText(format);
            this.lan = language;
        } catch (Exception e) {
            String str = TAG;
            Lg.w(str, "[JRSYS_PSD][onUIModeChanged], e: " + Log.getStackTraceString(e));
        }
    }
}
