package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.Context;
import android.util.Log;
import com.ecarx.systemui.plugin.PluginContext;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconWithTypeEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.ThaderUtils;
import com.ecarx.xui.adaptapi.wpc.WPC;

/* loaded from: classes.dex */
public class WPCController extends BaseController {
    private static final String TAG = WPCController.class.getSimpleName();
    private static WPCController wpcController;
    private WPC mWPCApi;

    private WPCController(Context context) {
        super(context);
        init();
    }

    public static WPCController getInstance(Context context) {
        if (wpcController == null) {
            synchronized (WPCController.class) {
                if (wpcController == null) {
                    wpcController = new WPCController(context);
                }
            }
        }
        return wpcController;
    }

    private void init() {
        WPC create = null;
        try {
            create = WPC.create(PluginContext.getHostContext());
            this.mWPCApi = create;
        } catch (Throwable th) {
            Lg.e(TAG, th);
        }
        if (create == null) {
            Log.w(TAG, "[JRSYS_WPC][init] mWPCApi is null");
            return;
        }
        create.setStateListener(new WPC.StateListener() { // from class: com.ecarx.systemui.plugin.statusbar.policy.WPCController.1
            @Override // com.ecarx.xui.adaptapi.wpc.WPC.StateListener
            public void onWorkingMode(int i) {
                ThaderUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.WPCController.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WPCController.this.update();
                    }
                });
            }

            @Override // com.ecarx.xui.adaptapi.wpc.WPC.StateListener
            public void onChargingStatus(int i) {
                ThaderUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.WPCController.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        WPCController.this.update();
                    }
                });
            }
        });
        update();
    }

    private void testCode() {
        setWirelessPowerIcon(true, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        WPC wpc = this.mWPCApi;
        if (wpc != null) {
            int workingMode = wpc.getWorkingMode();
            int chargingStatus = this.mWPCApi.getChargingStatus();
            if (workingMode == 2) {
                setWirelessPowerIcon(true, chargingStatus);
                return;
            }
        } else {
            Lg.e(TAG, "wpc api null");
        }
        setWirelessPowerIcon(false, 0);
    }

    private void setWirelessPowerIcon(boolean z, int i) {
        if (z && i != 1) {
            int i2 = R.drawable.ic_status_charge_ing;
            if (i != 2 && i != 3) {
                i2 = R.drawable.ic_status_charge_unable;
            }
            StatusBarManager.getInstance().setIcon(new StatusBarIconWithTypeEntity(EStatefulObject.WPC, i2, i));
            return;
        }
        StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.WPC, false);
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController
    public void destroy() {
        super.destroy();
    }
}
