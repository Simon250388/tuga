package com.ecarx.xui.adaptapi.car.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface ISafety {
    public static final int ENGINE_OFF_UNLOCKING_ALL_DOORS = 739246849;
    public static final int ENGINE_OFF_UNLOCKING_OFF = 0;
    public static final int ENGINE_OFF_UNLOCKING_SINGLE_DOOR = 739246850;
    public static final int SETTING_FUNC_ANY_DOOR_LOCK_WARNING = 738329600;
    public static final int SETTING_FUNC_APPROACH_TAIL_UNLOCK = 738264320;
    public static final int SETTING_FUNC_APPROACH_UNLOCK = 738263296;
    public static final int SETTING_FUNC_AUDIBLE_LOCKING_FEEDBACK = 537920256;
    public static final int SETTING_FUNC_AUTO_POWER_DOOR = 738265344;
    public static final int SETTING_FUNC_AWAY_LOCK = 738263552;
    public static final int SETTING_FUNC_CENTRAL_LOCK = 537921792;
    public static final int SETTING_FUNC_ENGINE_OFF_UNLOCKING = 739246848;
    public static final int SETTING_FUNC_KEYLESS_TRUNK_UNLOCK = 738264576;
    public static final int SETTING_FUNC_KEYLESS_UNLOCKING = 537920512;
    public static final int SETTING_FUNC_KEY_INCAR_REMINDER = 738329088;
    public static final int SETTING_FUNC_PASSIVE_ARMING = 537921280;
    public static final int SETTING_FUNC_REDUCED_GUARD = 537921536;
    public static final int SETTING_FUNC_REMOTE_UNLOCKING = 537920768;
    public static final int SETTING_FUNC_SPEED_AUTO_LOCKING_MODE = 739247104;
    public static final int SETTING_FUNC_SPEED_LOCKING = 537921024;
    public static final int SETTING_FUNC_TERMINAL_NOT_OFF_WARN = 738328832;
    public static final int SETTING_FUNC_TRUNK_OPENING_POSITION = 738265088;
    public static final int SETTING_FUNC_TRUNK_UNLOCK_DISTANCE = 738264832;
    public static final int SETTING_FUNC_TWOSTEP_UNLOCKING = 537922048;
    public static final int SETTING_FUNC_VISIBLE_LOCKING_FEEDBACK = 537919744;
    public static final int SETTING_FUNC_VISIBLE_UNLOCKING_FEEDBACK = 537920000;
    public static final int SETTING_FUNC_WARNING_TYPE = 738329344;
    public static final int SPEED_AUTO_LOCKING_MODE_10KM = 739247105;
    public static final int SPEED_AUTO_LOCKING_MODE_20KM = 739247106;
    public static final int SPEED_AUTO_LOCKING_MODE_OFF = 0;
    public static final int TRUNK_OPENING_POSITION_LEVEL_1 = 738265089;
    public static final int TRUNK_OPENING_POSITION_LEVEL_2 = 738265090;
    public static final int TRUNK_OPENING_POSITION_LEVEL_3 = 738265091;
    public static final int TRUNK_OPENING_POSITION_LEVEL_4 = 738265092;
    public static final int TRUNK_OPENING_POSITION_LEVEL_5 = 738265093;
    public static final int TRUNK_OPENING_POSITION_OFF = 0;
    public static final int TRUNK_UNLOCK_DISTANCE_LEVEL_1 = 738264833;
    public static final int TRUNK_UNLOCK_DISTANCE_LEVEL_2 = 738264834;
    public static final int TRUNK_UNLOCK_DISTANCE_OFF = 0;
    public static final int WARNING_TYPE_LIGHT = 738329345;
    public static final int WARNING_TYPE_LIGHT_VOICE = 738329346;
    public static final int WARNING_TYPE_OFF = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EngineOffUnlockingValue {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SafetyFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SpeedAutoLockingMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TrunkOpenPositionLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TrunkUnlockDistanceLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface WarningType {
    }
}
