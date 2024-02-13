package com.ecarx.xui.adaptapi.car.hvac;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IFragrance {
    public static final int AIR_FRAGRANCE_SLOT_1 = 269157377;
    public static final int AIR_FRAGRANCE_SLOT_2 = 269157378;
    public static final int AIR_FRAGRANCE_SLOT_3 = 269157379;
    public static final int AIR_FRAGRANCE_SLOT_4 = 269157380;
    public static final int AIR_FRAGRANCE_SLOT_5 = 269157381;
    public static final int HVAC_FUNC_AIR_FRAGRANCE = 269156608;
    public static final int HVAC_FUNC_AIR_FRAGRANCE_LEVEL = 269157120;
    public static final int HVAC_FUNC_AIR_FRAGRANCE_SLOT = 269157376;
    public static final int HVAC_FUNC_AIR_FRAGRANCE_TYPE = 269156864;
    public static final int HVAC_FUNC_AIR_FRAGRANCE_TYPE_ID = 269157632;
    public static final int HVAC_FUNC_AUTO_REFRESHING_FRAGRANCE = 269160704;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AirFragranceSlot {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FragranceFunction {
    }
}
