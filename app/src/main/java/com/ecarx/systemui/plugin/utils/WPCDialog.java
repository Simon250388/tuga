package com.ecarx.systemui.plugin.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.ecarx.systemui.plugin.R;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class WPCDialog extends Dialog {
    public static final int INT_DISMISS_DIALOG_5S = 5000;
    private static final int MSG_DISMISS_DIALOG = 1048577;
    private static final String TAG = WPCDialog.class.getSimpleName();
    private MyHandler mHandler;
    private int overlay;

    public WPCDialog(Context context) {
        super(context);
        this.mHandler = new MyHandler(this);
        this.overlay = 2038;
    }

    public WPCDialog(Context context, int i, String str) {
        super(context, i);
        this.mHandler = new MyHandler(this);
        this.overlay = 2038;
        init(context, str);
    }

    private void init(Context context, String str) {
        String str2 = TAG;
        Log.d(str2, "info: " + str);
        View inflate = LayoutInflater.from(context).inflate(R.layout.wpc_dialog_view, (ViewGroup) null, true);
        ((TextView) inflate.findViewById(R.id.massage)).setText(str);
        setContentView(inflate);
        int i = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        float f = context.getResources().getDisplayMetrics().density;
        float f2 = 420.0f * f;
        float f3 = f * 220.0f;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams((int) f2, (int) f3, (int) ((i - f2) / 2.0f), (int) ((i2 - f3) / 2.0f), this.overlay, 262144, -3);
        layoutParams.gravity = Gravity.START;
        layoutParams.alpha = 1.0f;
        layoutParams.dimAmount = 0.5f;
        getWindow().setAttributes(layoutParams);
        getWindow().addFlags(2);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        if (!isShowing()) {
            show();
        }
        dismissOnDelayTime(5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class MyHandler extends Handler {
        private WeakReference<WPCDialog> mReference;

        public MyHandler(WPCDialog wPCDialog) {
            this.mReference = new WeakReference<>(wPCDialog);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WPCDialog wPCDialog = this.mReference.get();
            if (wPCDialog != null && message.what == WPCDialog.MSG_DISMISS_DIALOG) {
                wPCDialog.cancel();
            }
        }
    }

    public void dismissOnDelayTime(long j) {
        this.mHandler.removeMessages(MSG_DISMISS_DIALOG);
        if (j <= 0) {
            if (isShowing()) {
                cancel();
                return;
            }
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(MSG_DISMISS_DIALOG, j);
    }
}
