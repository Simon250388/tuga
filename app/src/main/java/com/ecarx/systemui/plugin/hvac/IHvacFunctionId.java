package com.ecarx.systemui.plugin.hvac;

import com.ecarx.xui.adaptapi.car.hvac.IHvac;
import com.ecarx.xui.adaptapi.car.sensor.ISensor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IHvacFunctionId extends IHvac, ISensor {
    public static final int HVAC_FUNC_PCODE = 269549824;
    public static final int INT_RESOURCE_PREFIX = 269549825;
    public static final int TYPE_CAR_FUNCTION = 2;
    public static final int TYPE_CAR_SENSOR = 1;
    public static final int TYPE_PCODE = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FunctionIdPCode {
    }
}
