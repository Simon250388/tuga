package com.ecarx.systemui.plugin.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.ecarx.systemui.plugin.App;
import com.ecarx.systemui.plugin.R;
import com.sensorsdata.analytics.android.autotrack.aop.SensorsDataAutoTrackHelper;


/* loaded from: classes.dex */
public class LogoutTouristDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = LogoutTouristDialog.class.getSimpleName();
    private Context mContext;
    private int overlay;
    private TextView tvCancel;
    private TextView tvFix;

    public LogoutTouristDialog(Context context) {
        super(context);
        this.overlay = 2038;
    }

    public LogoutTouristDialog(Context context, int i) {
        super(context, i);
        this.overlay = 2038;
        this.mContext = context;
        init(context);
    }

    protected LogoutTouristDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.overlay = 2038;
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_logout_tourist, (ViewGroup) null, true);
        this.tvFix = (TextView) inflate.findViewById(R.id.tv_fix);
        this.tvCancel = (TextView) inflate.findViewById(R.id.tv_cancel);
        this.tvFix.setOnClickListener(this);
        this.tvCancel.setOnClickListener(this);
        setContentView(inflate);
        int i = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        float f = context.getResources().getDisplayMetrics().density;
        float f2 = 640.0f * f;
        float f3 = f * 436.0f;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams((int) f2, (int) f3, (int) ((i - f2) / 2.0f), (int) ((i2 - f3) / 2.0f), this.overlay, 262144, -3);
        layoutParams.gravity = Gravity.START;
        layoutParams.alpha = 1.0f;
        layoutParams.dimAmount = 0.5f;
        getWindow().setAttributes(layoutParams);
        getWindow().addFlags(2);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case 2131165352 /* 2131165352 */:
                dismiss();
                break;
            case 2131165353 /* 2131165353 */:
                dismiss();
                try {
                    Settings.Global.putInt(App.getApp().getContentResolver(), "device_provisioned", 0);
                    Log.w(TAG, "ProvisionedUtil set Settings.Global DEVICE_PROVISIONED: 0");
                } catch (Exception e) {
                    e.printStackTrace();
                    String str = TAG;
                    Log.e(str, "setProvisioned, exception: " + e.getMessage());
                }
                Intent intent = new Intent();
                intent.setClassName("com.ecarx.provision", "com.ecarx.provision.guide.GuideHelloActivity");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.mContext.startActivity(intent);
                break;
        }
        SensorsDataAutoTrackHelper.trackViewOnClick(view);
    }
}
