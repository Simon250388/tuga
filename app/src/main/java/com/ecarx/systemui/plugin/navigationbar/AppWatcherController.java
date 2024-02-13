package com.ecarx.systemui.plugin.navigationbar;

import android.content.Context;
import android.content.Intent;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.utils.Lg;
import java.util.List;

/* loaded from: classes.dex */
public class AppWatcherController extends BaseController {
    private static String TAG = "AppWatcherController";
    private static AppWatcherController instance;
    private List<String> mList;

    @Override // com.ecarx.systemui.plugin.policy.BaseController, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
    }

    private AppWatcherController(Context context) {
        super(context);
    }

    public static AppWatcherController getInstance(Context context) {
        if (instance == null) {
            instance = new AppWatcherController(context.getApplicationContext());
        }
        return instance;
    }

    public void init() {
        AccessibilityHelper.enableAccessibility(this.mContext, this.mContext.getPackageName(), AppWatcherService.class.getName());
        this.mContext.startService(new Intent(this.mContext, AppWatcherService.class));
        Lg.w("registerReceiver AppWatcherService");
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController
    public void destroy() {
        super.destroy();
    }
}
