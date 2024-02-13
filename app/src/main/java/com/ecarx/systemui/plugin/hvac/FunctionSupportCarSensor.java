package com.ecarx.systemui.plugin.hvac;

import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.sensor.ISensor;

/* loaded from: classes.dex */
public class FunctionSupportCarSensor implements IFunctionSupport {
    private static final String TAG = FunctionSupportCarSensor.class.getSimpleName();
    private ISensor mISensor = CarUtil.getInstance().getISensor();

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionSupport
    public BaseResult isFunctionSupport(BaseParam baseParam) {
        BaseResult baseResult = new BaseResult();
        baseResult.isFunctionSupport = false;
        if (this.mISensor == null) {
            Log.d(TAG, "isSensorSupported ISensor is null");
            return baseResult;
        }
        BaseResult isSensorSupported = isSensorSupported(baseParam.functionId);
        BaseConfig.mSensorIds.add(Integer.valueOf(baseParam.functionId));
        return isSensorSupported;
    }

    private BaseResult isSensorSupported(int i) {
        String str = TAG;
        Log.d(str, "isSensorSupported functionId: " + Integer.toHexString(i));
        BaseResult baseResult = new BaseResult();
        baseResult.isFunctionSupport = false;
        try {
            FunctionStatus isSensorSupported = this.mISensor.isSensorSupported(i);
            baseResult.functionStatus = isSensorSupported;
            baseResult.isFunctionSupport = getBooleanByFunctionStatus(isSensorSupported);
            return baseResult;
        } catch (Exception e) {
            String str2 = TAG;
            Log.d(str2, "isFunctionSupported error2: " + e.getMessage());
            return baseResult;
        } catch (NoSuchMethodError e2) {
            String str3 = TAG;
            Log.d(str3, "isFunctionSupported error1: " + e2.getMessage());
            return baseResult;
        }
    }

    private boolean getBooleanByFunctionStatus(FunctionStatus functionStatus) {
        String str = TAG;
        Log.d(str, "getBooleanByFunctionStatus functionStatus" + functionStatus);
        if (functionStatus == null) {
            return false;
        }
        return functionStatus == FunctionStatus.active || functionStatus == FunctionStatus.notactive;
    }
}
