package com.ecarx.systemui.plugin.hvac;

import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;

/* loaded from: classes.dex */
public class FunctionSupportCarFunction implements IFunctionSupport {
    private static final String TAG = FunctionSupportCarFunction.class.getSimpleName();
    private ICarFunction mICarFunction = CarUtil.getInstance().getICarFunction();

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionSupport
    public BaseResult isFunctionSupport(BaseParam<Integer> baseParam) {
        BaseResult isFunctionSupport;
        BaseResult baseResult = new BaseResult();
        baseResult.isFunctionSupport = false;
        if (this.mICarFunction == null) {
            Log.d(TAG, "isFunctionSupport mICarFunction is null");
            return baseResult;
        }
        if (baseParam.zone == -1 && baseParam.operator == 0) {
            isFunctionSupport = isFunctionSupport(baseParam.functionId);
        } else if (baseParam.operator == 0) {
            isFunctionSupport = isFunctionSupport(baseParam.functionId, baseParam.zone);
        } else {
            isFunctionSupport = isFunctionSupport(baseParam.functionId, baseParam.zone, ((Integer) baseParam.operator).intValue());
        }
        BaseConfig.mFunctionIds.add(Integer.valueOf(baseParam.functionId));
        return isFunctionSupport;
    }

    private BaseResult isFunctionSupport(int i) {
        String str = TAG;
        Log.d(str, "isFunctionSupported functionId: " + Integer.toHexString(i));
        BaseResult baseResult = new BaseResult();
        baseResult.isFunctionSupport = false;
        try {
            baseResult.supportValues = this.mICarFunction.getSupportedFunctionValue(i);
            FunctionStatus isFunctionSupported = this.mICarFunction.isFunctionSupported(i);
            baseResult.functionStatus = isFunctionSupported;
            baseResult.isFunctionSupport = getBooleanByFunctionStatus(isFunctionSupported);
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

    private BaseResult isFunctionSupport(int i, int i2) {
        String str = TAG;
        Log.d(str, "isFunctionSupported functionId: " + Integer.toHexString(i) + "  zone:" + i2);
        BaseResult baseResult = new BaseResult();
        baseResult.isFunctionSupport = false;
        try {
            baseResult.supportValues = this.mICarFunction.getSupportedFunctionValue(i, i2);
            FunctionStatus isFunctionSupported = this.mICarFunction.isFunctionSupported(i, i2);
            String str2 = TAG;
            Log.i(str2, "functionId =  " + Integer.toHexString(i) + "   functionStatus = " + isFunctionSupported.name());
            baseResult.functionStatus = isFunctionSupported;
            baseResult.isFunctionSupport = getBooleanByFunctionStatus(isFunctionSupported);
            return baseResult;
        } catch (Exception e) {
            String str3 = TAG;
            Log.d(str3, "isFunctionSupported error2: " + e.getMessage());
            return baseResult;
        } catch (NoSuchMethodError e2) {
            String str4 = TAG;
            Log.d(str4, "isFunctionSupported error1: " + e2.getMessage());
            return baseResult;
        }
    }

    private BaseResult isFunctionSupport(int i, int i2, int i3) {
        String str = TAG;
        Log.d(str, "isFunctionSupported functionId: " + Integer.toHexString(i) + "  zone:" + i2 + " value:" + i3);
        BaseResult baseResult = new BaseResult();
        baseResult.isFunctionSupport = false;
        try {
            baseResult.supportValues = this.mICarFunction.getSupportedFunctionValue(i);
            FunctionStatus isFunctionSupported = this.mICarFunction.isFunctionSupported(i, i2, i3);
            baseResult.functionStatus = isFunctionSupported;
            baseResult.isFunctionSupport = getBooleanByFunctionStatus(isFunctionSupported);
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
        Log.d(str, "getBooleanByFunctionStatus functionStatus:" + functionStatus.name());
        if (functionStatus == null) {
            return false;
        }
        return functionStatus == FunctionStatus.active || functionStatus == FunctionStatus.notactive;
    }
}
