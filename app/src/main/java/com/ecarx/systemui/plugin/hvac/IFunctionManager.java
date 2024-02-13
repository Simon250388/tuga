package com.ecarx.systemui.plugin.hvac;

/* loaded from: classes.dex */
public interface IFunctionManager {
    BaseResult getCustomizeFunctionValue(BaseParam baseParam);

    BaseResult getFunctionValue(BaseParam baseParam);

    void release();

    BaseResult setFunctionValue(BaseParam baseParam);

    void setFunctionValueListener(IFunctionValueCallback iFunctionValueCallback);
}
