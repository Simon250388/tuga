package com.ecarx.sdk.device;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import com.ecarx.sdk.ECarXAPIBase;
import com.ecarx.sdk.device.daynightmode.IDayNightMode;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.List;

/* loaded from: classes.dex */
public abstract class DeviceAPI extends ECarXAPIBase {
    public static final String OPERATOR_NAME_UNKNOWN = "";
    public static final int OPERATOR_UNKNOWN = 255;
    public static final String TAG = "DeviceAPI";
    private static DeviceAPI mInstance;

    public abstract void attachEcarxIdWatcher(IEcarxIdWatcher iEcarxIdWatcher);

    public abstract void dettachEcarxIdWatcher();

    public abstract boolean getBooleanValue(String str);

    public abstract String getDVRID();

    public abstract IDayNightMode getDayNightMode();

    public abstract double getDoubleValue(String str);

    public abstract String getICCID();

    public abstract String getIHUID();

    public abstract int getIntValue(String str);

    public abstract long getLongValue(String str);

    public abstract String getOpenIHUID();

    public abstract String getOpenVIN();

    public abstract int getOperatorCode();

    public abstract String getOperatorName();

    public abstract String getProjectCode();

    public abstract String getStringValue(String str);

    public abstract String getSupplierCode();

    public abstract List<ComponentName> getSupportedComponents();

    public abstract String getVIN();

    public abstract String getVehicleType();

    public abstract String getXDSN();

    public static DeviceAPI get(Context context) {
        if (mInstance == null) {
            synchronized (DeviceAPI.class) {
                if (mInstance == null) {
                    mInstance = createDeviceAPI(context);
                }
            }
        }
        return mInstance;
    }

    public static DeviceAPI createDeviceAPI(Context context) {
        if (context == null) {
            throw new InvalidParameterException("param Context is null!");
        }
        Log.i(TAG, "createDeviceAPI");
        try {
            return (DeviceAPI) Class.forName("com.ecarx.sdk.device.DeviceAPIImpl").getConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "createDeviceAPI failed");
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            Log.e(TAG, "createDeviceAPI failed");
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            Log.e(TAG, "createDeviceAPI failed");
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            Log.e(TAG, "createDeviceAPI failed");
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            Log.e(TAG, "createDeviceAPI failed");
            return null;
        }
    }
}
