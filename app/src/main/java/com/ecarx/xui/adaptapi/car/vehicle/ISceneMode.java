package com.ecarx.xui.adaptapi.car.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface ISceneMode {
    public static final int SCENE_FUNC_AWAKENING = 788660736;
    public static final int SCENE_FUNC_BIOCHEMICAL_MODE = 788595712;
    public static final int SCENE_FUNC_CLEAN_MODE = 788595456;
    public static final int SCENE_FUNC_PARENT_CHILD = 788660992;
    public static final int SCENE_FUNC_SMOKING = 788660480;
    public static final int SCENE_FUNC_THEATER_MODE = 788594944;
    public static final int SCENE_FUNC_WASH_MODE = 788595200;
    public static final int SCENE_FUNC_YUEDONG = 788661248;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SceneFunction {
    }
}
