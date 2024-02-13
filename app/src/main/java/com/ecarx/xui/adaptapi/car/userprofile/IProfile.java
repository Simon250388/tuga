package com.ecarx.xui.adaptapi.car.userprofile;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IProfile {

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FloatProfileFuncId {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface IntProfileFuncId {
    }

    boolean containsProfileFuncId(int i, int i2);

    int[] getContainsProfileFuncIds();

    int getProfileFuncValue(int i, int i2);

    float getProfileFuncValueFloat(int i, int i2);

    int[] getProfileSupportedZones(int i);

    String toJOSNString();
}
