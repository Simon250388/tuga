package com.ecarx.systemui.plugin.psd;

import android.content.Context;
import android.util.Log;
import android.view.WindowManager;
import com.ecarx.xui.adaptapi.policy.IWindowManagerPolicy;
import com.ecarx.xui.adaptapi.policy.Policy;

/* loaded from: classes.dex */
public class WindowHelper {
    static final String TAG = WindowHelper.class.getName();
    private IWindowManagerPolicy mWindowManagerPolicy;

    public WindowHelper(Context context) {
        Policy create = Policy.create(context);
        String str = TAG;
        Log.w(str, "[JRSYS_PSD][WindowHelper], policy: " + create);
        if (create != null) {
            this.mWindowManagerPolicy = create.getWindowManagerPolicy();
        }
        if (this.mWindowManagerPolicy == null) {
            throw new NullPointerException("[JRSYS_PSD]Policy getWindowManagerPolicy return null!");
        }
        String str2 = TAG;
        Log.i(str2, "[JRSYS_PSD][WindowHelper] mWindowManagerPolicy: " + this.mWindowManagerPolicy);
    }

    public int getTypeByCode(String str) {
        return this.mWindowManagerPolicy.getWindowTypeByCode(str);
    }

    public static WindowManager.LayoutParams getDefaultLayoutParams(int i) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = i;
        layoutParams.format = -3;
        return layoutParams;
    }
}
