package com.ecarx.xui.adaptapi.car.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IAmbienceLight {
    public static final int AMBIENCE_LIGHT_CUSTOM_MODE_BREATHE = 705167619;
    public static final int AMBIENCE_LIGHT_CUSTOM_MODE_GRADIENTS = 705167618;
    public static final int AMBIENCE_LIGHT_CUSTOM_MODE_SOLID_COLOR = 705167617;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_APPLE_GREEN = 704709132;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_BLUE = 704709126;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_GREEN = 704709124;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_ICE_BLUE = 704709129;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_INDIGO = 704709125;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_OFF = 0;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_ORANGE = 704709122;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_RED = 704709121;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_SPANISH_RED = 704709131;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_SUN_RED = 704709130;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_VIOLET = 704709127;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_WHITE = 704709128;
    public static final int AMBIENCE_LIGHT_THEME_COLOR_YELLOW = 704709123;
    public static final int AMBIENCE_LIGHT_ZONE_ALL = 1;
    public static final int AMBIENCE_LIGHT_ZONE_FRONT = 1;
    public static final int AMBIENCE_LIGHT_ZONE_HEADREST = 3;
    public static final int AMBIENCE_LIGHT_ZONE_REAR = 3;
    public static final int MOOD_LIGHT_COLOR_BLUE = 705036806;
    public static final int MOOD_LIGHT_COLOR_GREEN = 705036804;
    public static final int MOOD_LIGHT_COLOR_INDIGO = 705036805;
    public static final int MOOD_LIGHT_COLOR_OFF = 0;
    public static final int MOOD_LIGHT_COLOR_ORANGE = 705036802;
    public static final int MOOD_LIGHT_COLOR_RED = 705036801;
    public static final int MOOD_LIGHT_COLOR_VIOLET = 705036807;
    public static final int MOOD_LIGHT_COLOR_WHITE = 705036808;
    public static final int MOOD_LIGHT_COLOR_YELLOW = 705036803;
    public static final int MOOD_LIGHT_MODE_COLOR = 705037058;
    public static final int MOOD_LIGHT_MODE_OFF = 0;
    public static final int MOOD_LIGHT_MODE_TEMP = 705037057;
    public static final int MUSIC_SHOW_MODE_NORMAL = 704972802;
    public static final int MUSIC_SHOW_MODE_OFF = 0;
    public static final int MUSIC_SHOW_MODE_PASSIONATE = 704972801;
    public static final int MUSIC_SHOW_MODE_SUBDUED = 704972803;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_BOTZONES = 537527040;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_BOTZONES_COLOR_SET = 704774400;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_BOTZONES_INTENSITY = 704774656;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_BRIGHTNESS_DRIVING = 537528064;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_BRIGHTNESS_STATIONARY = 537527808;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_COLOR_SET = 537528576;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_EFFECT_SET = 705167616;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_ENDURANCE_MIL_REMINDER = 704971520;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_EXPERIENCE = 537526528;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_GOODBYE_SHOW = 704971264;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_INTENSITY_SET = 704708864;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_INTERACTIVE_EFFECT = 537528320;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_MAINCOLOR = 537526784;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_MAINZONES = 537527552;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_MAINZONES_COLOR_SET = 704905472;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_MAINZONES_INTENSITY = 704905728;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_MUSIC_SHOW_MODE = 704972800;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_OVER_SPEED_WARNING = 704972288;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_PHONE_CALL_REMINDER = 704971776;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_STANDSTILL = 704972032;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_THEME_COLOR = 704709120;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_TOPZONES = 537527296;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_TOPZONES_COLOR_SET = 704839936;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_TOPZONES_INTENSITY = 704840192;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_WELCOME_SHOW = 704971008;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_WELCOME_SHOW_MODE = 704972544;
    public static final int SETTING_FUNC_AMBIENCE_ZONES_SYNC = 704709632;
    public static final int SETTING_FUNC_BREATHE_MODE_COLOR = 704709376;
    public static final int SETTING_FUNC_MOOD_LIGHT = 705036544;
    public static final int SETTING_FUNC_MOOD_LIGHT_COLOR = 705036800;
    public static final int SETTING_FUNC_MOOD_LIGHT_MODE = 705037056;
    public static final int SETTING_FUNC_TRANSITION_END_COLOR = 705102592;
    public static final int SETTING_FUNC_TRANSITION_MODE = 705102080;
    public static final int SETTING_FUNC_TRANSITION_START_COLOR = 705102336;
    public static final int WELCOME_SHOW_MODE_NORMAL = 704972546;
    public static final int WELCOME_SHOW_MODE_OFF = 0;
    public static final int WELCOME_SHOW_MODE_PASSIONATE = 704972545;
    public static final int WELCOME_SHOW_MODE_SUBDUED = 704972547;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AmbienceLightCustomModeEffect {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AmbienceLightFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AmbienceLightZone {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AmbienceThemeColors {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ModeLightColors {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ModeLightModes {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface MusicShowModes {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface WelcomeShowModes {
    }
}
