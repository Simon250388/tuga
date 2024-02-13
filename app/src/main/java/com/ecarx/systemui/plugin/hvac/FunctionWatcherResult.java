package com.ecarx.systemui.plugin.hvac;

import com.ecarx.xui.adaptapi.FunctionStatus;

/* loaded from: classes.dex */
public class FunctionWatcherResult<T> {
    public int[] funcValues;
    public int functionId;
    public String functionIdName;
    public FunctionStatus functionStatus;
    public T operator;
    public int zone;
    public String zoneName;

    public String toString() {
        return "\n   functionIdName:" + this.functionId + "\n   zoneName:" + this.zone + "\n   functionId:" + Integer.toHexString(this.functionId) + "\n   zone:" + this.zone + "\n   operator:" + this.operator + "\n   funcValues:" + this.funcValues + "\n   functionStatus:" + this.functionStatus;
    }
}
