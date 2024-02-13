package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.utils.Lg;
import java.io.File;

/* loaded from: classes.dex */
public class MediaController extends BaseController {
    private static final String TAG = MediaController.class.getSimpleName();
    public static final int USB_STATUS_ICON_ID = 2131099797;
    private static volatile MediaController mediaController;
    private USBDevice mUSBDevice;

    private MediaController(Context context) {
        super(context);
        this.mUSBDevice = USBDevice.getInstance(this.mContext);
        if (usb1IsMounted() || usb2IsMounted()) {
            updateMedia(true);
            Lg.i(" [JRSYS_USB][MediaController] have usb disk connected");
        } else {
            Lg.i(" [JRSYS_USB][MediaController] no usb disk connected");
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_CHECKING");
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        intentFilter.addDataScheme("file");
        this.mContext.registerReceiver(this, intentFilter);
    }

    public static MediaController getInstance(Context context) {
        if (mediaController == null) {
            synchronized (MediaController.class) {
                if (mediaController == null) {
                    mediaController = new MediaController(context);
                }
            }
        }
        mediaController.initState();
        return mediaController;
    }

    public void initState() {
        if (usb1IsMounted() || usb2IsMounted()) {
            updateMedia(true);
            Lg.i(" [JRSYS_USB][initState] have usb disk connected");
            return;
        }
        Lg.i(" [JRSYS_USB][initState] no usb disk connected");
    }

    private void testCode() {
        updateMedia(true);
    }

    private void updateMedia(boolean z) {
        String str = TAG;
        Lg.i(str, "[JRSYS_USB][onReceive] isMounted: " + z);
        if (z) {
            StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.USB, R.drawable.ic_status_usb));
            return;
        }
        StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.USB, false);
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String str = TAG;
        Lg.i(str, "[JRSYS_USB][onReceive]: " + action);
        if (action.equals("android.intent.action.MEDIA_MOUNTED") || action.equals("android.intent.action.MEDIA_CHECKING")) {
            if (usb1IsMounted() || usb2IsMounted()) {
                updateMedia(true);
            }
        } else if (action.equals("android.intent.action.MEDIA_UNMOUNTED") || action.equals("android.intent.action.MEDIA_REMOVED") || action.equals("android.intent.action.MEDIA_EJECT")) {
            String str2 = TAG;
            Lg.i(str2, "[JRSYS_USB][onReceive] out isMounted: " + isMounted() + " isChecking:" + isChecking());
            if (usb1IsMounted() || usb2IsMounted()) {
                return;
            }
            updateMedia(false);
        }
    }

    private boolean isMounted() {
        try {
            if (!this.mUSBDevice.isMounted(1)) {
                if (!this.mUSBDevice.isMounted(2)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            Lg.e(TAG, th);
            return false;
        }
    }

    private boolean isChecking() {
        try {
            if (!this.mUSBDevice.isChecking(1)) {
                if (!this.mUSBDevice.isChecking(2)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            Lg.e(TAG, "isChecking", th);
            return false;
        }
    }

    private boolean usb1IsMounted() {
        boolean z;
        try {
            z = isPathExits(this.mUSBDevice.getVolumeFullPath(1));
        } catch (Throwable th) {
            Log.e(TAG, "[JRSYS_USB][usb1IsMounted] usb1IsMounted", th);
            z = false;
        }
        String str = TAG;
        Lg.i(str, "[JRSYS_USB][usb1IsMounted] isMounted: " + z);
        return z;
    }

    private boolean usb2IsMounted() {
        boolean z;
        try {
            z = isPathExits(this.mUSBDevice.getVolumeFullPath(2));
        } catch (Throwable th) {
            Log.e(TAG, "[JRSYS_USB][usb2IsMounted] usb2IsMounted", th);
            z = false;
        }
        String str = TAG;
        Lg.i(str, "[JRSYS_USB][usb2IsMounted] isMounted: " + z);
        return z;
    }

    private boolean isPathExits(String str) {
        return str != null && new File(str).exists();
    }
}
