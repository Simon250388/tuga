package com.ecarx.systemui.plugin.hvac;

import com.ecarx.xui.adaptapi.FunctionStatus;

/* loaded from: classes.dex */
public class BaseResult {
    public static final int ERROR_CAR_FUNCTION_IS_NULL = -65537;
    public float customValue;
    public int functionId;
    public String functionName;
    public FunctionStatus functionStatus;
    public boolean isFunctionSupport;
    public String pcode;
    public String prefix;
    public boolean setFunctionSuccess;
    public int[] supportValues;
    public int value;

    public String toString() {
        return "\n  isFunctionSupport:" + this.isFunctionSupport + "\n  functionStatus:" + this.functionStatus + "\n  supportValues:" + this.supportValues + "\n  setFunctionSuccess:" + this.setFunctionSuccess + "\n  value:" + this.value + "\n  customValue:" + this.customValue + "\n  functionName:" + this.functionName + "\n  functionId:" + Integer.toHexString(this.functionId) + "\n  prefix:" + this.prefix + "\n  pcode:" + this.pcode;
    }
}
