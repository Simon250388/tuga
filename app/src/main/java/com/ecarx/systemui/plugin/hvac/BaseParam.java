package com.ecarx.systemui.plugin.hvac;

/* loaded from: classes.dex */
public class BaseParam<T> {
    public static final int DEF_VALUE = -1;
    public T operator;
    public int type = -1;
    public int functionId = -1;
    public String functionIdName = "";
    public int zone = -1;
    public String zoneName = "";

    public String toString() {
        return "\n   functionIdName:" + this.functionId + "\n   zoneName:" + this.zone + "\n   functionId:" + Integer.toHexString(this.functionId) + "\n   zone:" + this.zone + "\n   operator:" + this.operator + "\n   type:" + this.type;
    }
}
