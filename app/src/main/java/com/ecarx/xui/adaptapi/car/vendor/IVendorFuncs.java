package com.ecarx.xui.adaptapi.car.vendor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IVendorFuncs {
    public static final int CUSTOM_KEY_TYPE_360_PANORAMA = -2147417854;
    public static final int CUSTOM_KEY_TYPE_CAR_SETTING = -2147417849;
    public static final int CUSTOM_KEY_TYPE_COLLECT_FAV = -2147417850;
    public static final int CUSTOM_KEY_TYPE_DIM_FULL_SCREEN_MAP = -2147417852;
    public static final int CUSTOM_KEY_TYPE_DVR = -2147417855;
    public static final int CUSTOM_KEY_TYPE_NAVIGATION = -2147417853;
    public static final int CUSTOM_KEY_TYPE_SOUND_SWITCH = -2147417851;
    public static final int CUSTOM_KEY_TYPE_WHEEL_HEAT = -2147417848;
    public static final int VENDOR_FUNC_CUSTOM_KEY = -2147417856;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CustomKeyType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VendorFunctionFlt {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VendorFunctionInt {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VendorFunctionValues {
    }
}
