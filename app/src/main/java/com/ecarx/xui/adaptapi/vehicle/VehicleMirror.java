package com.ecarx.xui.adaptapi.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class VehicleMirror {
    public static final int MIRROR_DRIVER_CENTER = 4;
    public static final int MIRROR_DRIVER_LEFT = 1;
    public static final int MIRROR_DRIVER_RIGHT = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface MirrorType {
    }

    private VehicleMirror() {
    }
}
