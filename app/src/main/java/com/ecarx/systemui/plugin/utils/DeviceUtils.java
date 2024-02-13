package com.ecarx.systemui.plugin.utils;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.device.Device;

/* loaded from: classes.dex */
public class DeviceUtils {
    public static final String POJ_FE_5 = "FE-5";
    public static final String POJ_FE_6 = "FE-6";
    public static final String POJ_KC_2 = "KC-2";
    public static final String POJ_NL_3B = "NL3B";
    public static final String POJ_NL_5 = "NL5";
    public static final String POJ_XEA406M = "xea406m";
    private static final String TAG = DeviceUtils.class.getSimpleName();
    private static DeviceUtils mInstance;
    private static Device sDevice;
    private boolean mConnected;
    private ConnectedCallBack mConnectedCallBack;
    private Context mContext;

    /* loaded from: classes.dex */
    public interface ConnectedCallBack {
        void onConnected(boolean z);
    }

    private DeviceUtils(Context context) {
        this.mContext = context;
        try {
            Device create = Device.create(context);
            sDevice = create;
            if (create == null) {
                Log.w(TAG, "[JRSYS_INTEL][DeviceUtil] create is null");
            } else {
                boolean z = Utilities.ATLEAST_O_MR1;
            }
        } catch (Throwable th) {
            String str = TAG;
            Log.w(str, "[JRSYS_INTEL][DeviceUtil], e: " + Log.getStackTraceString(th));
        }
    }

    public static DeviceUtils init(Context context) {
        synchronized (DeviceUtils.class) {
            if (mInstance == null) {
                mInstance = new DeviceUtils(context);
            }
        }
        return mInstance;
    }

    public static DeviceUtils getInstance() {
        DeviceUtils deviceUtils;
        synchronized (DeviceUtils.class) {
            deviceUtils = mInstance;
        }
        return deviceUtils;
    }

    public void setOnConnectedCallBack(ConnectedCallBack connectedCallBack) {
        this.mConnectedCallBack = connectedCallBack;
        if (Utilities.ATLEAST_O_MR1) {
            if (sDevice != null) {
                this.mConnectedCallBack.onConnected(true);
            }
        } else if (sDevice != null) {
            this.mConnectedCallBack.onConnected(true);
        }
    }

    public boolean isDVRCameraConfigured() {
        Device device = sDevice;
        if (device == null) {
            return false;
        }
        boolean isDVRCameraConfigured = device.isDVRCameraConfigured();
        String str = TAG;
        Log.i(str, "[JRSYS_INTEL][isDVRCameraConfigured] isDVRConfig: " + isDVRCameraConfigured);
        return isDVRCameraConfigured;
    }

    public static boolean isKC2(Context context) {
        try {
            if (sDevice == null) {
                sDevice = Device.create(context);
            }
            return POJ_KC_2.equals(sDevice.getProjectCode());
        } catch (Throwable th) {
            Lg.e(TAG, th);
            return false;
        }
    }

    public static boolean isNL5(Context context) {
        try {
            if (sDevice == null) {
                sDevice = Device.create(context);
            }
            return POJ_NL_5.equals(sDevice.getProjectCode());
        } catch (Throwable th) {
            Lg.e(TAG, th);
            return false;
        }
    }

    public static boolean isFE6(Context context) {
        try {
            if (sDevice == null) {
                sDevice = Device.create(context);
            }
            return POJ_FE_6.equals(sDevice.getProjectCode());
        } catch (Throwable th) {
            Lg.e(TAG, th);
            return false;
        }
    }

    public static boolean isNL3B(Context context) {
        try {
            if (sDevice == null) {
                sDevice = Device.create(context);
            }
            return POJ_NL_3B.equals(sDevice.getProjectCode());
        } catch (Throwable th) {
            Lg.e(TAG, th);
            return false;
        }
    }

    public static boolean isXEA406M(Context context) {
        try {
            if (sDevice == null) {
                sDevice = Device.create(context);
            }
            return POJ_XEA406M.equals(sDevice.getProjectCode());
        } catch (Throwable th) {
            Lg.e(TAG, th);
            return false;
        }
    }

    public static boolean isTwoLevelSeat(Context context) {
        try {
            if (sDevice == null) {
                sDevice = Device.create(context);
            }
            if (!POJ_FE_6.equals(sDevice.getProjectCode())) {
                if (!POJ_FE_5.equals(sDevice.getProjectCode())) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            Lg.e(TAG, th);
            return false;
        }
    }
}
