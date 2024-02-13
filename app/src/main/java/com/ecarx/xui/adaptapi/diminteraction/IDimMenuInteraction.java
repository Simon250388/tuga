package com.ecarx.xui.adaptapi.diminteraction;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IDimMenuInteraction {
    public static final int ACTIVE_TYPE_IDLE = 0;
    public static final int ACTIVE_TYPE_MEDIA_PLAYING = 1;
    public static final int ACTIVE_TYPE_NO_CALLING = 4;
    public static final int ACTIVE_TYPE_NO_MEDIA = 3;
    public static final int ACTIVE_TYPE_PHONE_CALLING = 2;
    public static final int CENTER_STATE_CALL = 2;
    public static final int CENTER_STATE_EXIT = 0;
    public static final int CENTER_STATE_MEDIA = 1;
    public static final int ENTER_ACTION_MEDIA_CONFIRM_KEY = 1;
    public static final int ENTER_ACTION_N_SECONDS_NO_ACTION = 2;
    public static final int MENU_TAB_MUSIC = 3;
    public static final int MENU_TAB_NAVIGATION = 2;
    public static final int MENU_TAB_OTHER = 0;
    public static final int MENU_TAB_PHONE = 1;
    public static final int NAVI_MODE_AR = 4;
    public static final int NAVI_MODE_FULL = 3;
    public static final int NAVI_MODE_OFF = 1;
    public static final int NAVI_MODE_SIMPLIFY = 2;
    public static final int THEME_COLOR_COMFORT = 1;
    public static final int THEME_COLOR_ECO = 2;
    public static final int THEME_COLOR_OFFROAD = 5;
    public static final int THEME_COLOR_SNOW = 4;
    public static final int THEME_COLOR_SPORT = 3;
    public static final int THEME_DAY = 6;
    public static final int THEME_NIGHT = 7;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ControlCenterActiveType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ControlCenterState {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DimMenuTab {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DimTheme {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EnterControlCenterAction {
    }

    /* loaded from: classes.dex */
    public interface IDimMenuInteractionCallback {
        void onChangeNaviMode(int i);

        void onControlCenterStateChanged(int i);

        void onTabChanged(int i);

        void onThemeChanged(int i);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface NaviMode {
    }

    int getNaviMode();

    void notifyDimControlCenterActiveType(int i);

    void notifyDimEnterControlCenter(int i);

    void notifyDimSwitchThemeCompeted(boolean z);

    void notifyIHUReady();

    void registerDimMenuInteractionCallback(IDimMenuInteractionCallback iDimMenuInteractionCallback);

    void requestDimSwitchTabWindow(int i);

    void requestDimTheme();

    void setVolume(int i);

    void switchNaviMode(int i);

    void unregisterDimMenuInteractionCallback(IDimMenuInteractionCallback iDimMenuInteractionCallback);
}
