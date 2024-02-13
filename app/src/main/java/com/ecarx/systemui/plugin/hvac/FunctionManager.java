package com.ecarx.systemui.plugin.hvac;

import android.util.Log;

/* loaded from: classes.dex */
public class FunctionManager implements IFunctionManager, IFunctionSupport {
    private static final String TAG = FunctionManager.class.getSimpleName();
    private FunctionManagerPolicy mFunctionManagerPolicy;

    public static FunctionManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private FunctionManager() {
        this.mFunctionManagerPolicy = new FunctionManagerPolicy();
    }

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static FunctionManager INSTANCE = new FunctionManager();

        private SingletonHolder() {
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionSupport
    public BaseResult isFunctionSupport(BaseParam baseParam) {
        if (baseParam == null) {
            Log.d(TAG, "isFunctionSupport param is null");
            BaseResult baseResult = new BaseResult();
            baseResult.isFunctionSupport = false;
            return baseResult;
        }
        return FunctionSupport.getInstance().isFunctionSupport(baseParam);
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult getFunctionValue(BaseParam baseParam) {
        if (baseParam == null) {
            Log.d(TAG, "getFunctionValue param is null");
            BaseResult baseResult = new BaseResult();
            baseResult.value = Integer.MIN_VALUE;
            return baseResult;
        }
        return this.mFunctionManagerPolicy.getFunctionValue(baseParam);
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult getCustomizeFunctionValue(BaseParam baseParam) {
        if (baseParam == null) {
            Log.d(TAG, "getCustomizeFunctionValue param is null");
            BaseResult baseResult = new BaseResult();
            baseResult.customValue = Float.MIN_VALUE;
            return baseResult;
        }
        return this.mFunctionManagerPolicy.getCustomizeFunctionValue(baseParam);
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public BaseResult setFunctionValue(BaseParam baseParam) {
        if (baseParam == null) {
            Log.d(TAG, "setFunctionValue param is null");
            BaseResult baseResult = new BaseResult();
            baseResult.setFunctionSuccess = false;
            return baseResult;
        }
        return this.mFunctionManagerPolicy.setFunctionValue(baseParam);
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public void setFunctionValueListener(IFunctionValueCallback iFunctionValueCallback) {
        this.mFunctionManagerPolicy.setFunctionValueListener(iFunctionValueCallback);
    }

    @Override // com.ecarx.systemui.plugin.hvac.IFunctionManager
    public void release() {
        this.mFunctionManagerPolicy.release();
    }

    public void registerFunctionValueWatcher() {
        this.mFunctionManagerPolicy.registerFunctionValueWatcher();
    }
}
