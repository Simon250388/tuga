package com.ecarx.xui.adaptapi.car.sensor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IVirtualSensor {
    public static final int MAINTENANCE_REMIND_STATE_ACTIVE = 4207106;
    public static final int MAINTENANCE_REMIND_STATE_NORMAL = 4207105;
    public static final int MAINTENANCE_REMIND_STATE_UNKNOWN = -1;
    public static final int TYPE_ADDITIONAL_EV_CONSUMPTION_ALL = 4214784;
    public static final int TYPE_ADDITIONAL_EV_CONSUMPTION_CLIMATE = 4215040;
    public static final int TYPE_AVG_ENERGY_CONSUMPTION = 4195328;
    public static final int TYPE_AVG_FUEL_CONSUMPTION = 4194560;
    public static final int TYPE_AVG_FUEL_CONSUMPTION_ONE_IGNITION = 4195072;
    public static final int TYPE_CHARGING_ENDURANCE_MILEAGE = 4210944;
    public static final int TYPE_DISCHARGING_ENDURANCE_MILEAGE = 4211200;
    public static final int TYPE_EV_BATTERY_PERCENTAGE = 4210688;
    public static final int TYPE_INS_FUEL_CONSUMPTION = 4194816;
    public static final int TYPE_MAINTENANCE_MILEAGE = 4206592;
    public static final int TYPE_MAINTENANCE_MILEAGE_REMIND = 4207104;
    public static final int TYPE_MAINTENANCE_TIME = 4206848;
    public static final int TYPE_MAINTENANCE_TIME_REMIND = 4207360;
    public static final int TYPE_POTENTIAL_ENDURANCE_MILEAGE = 4211456;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface MaintenanceRemindState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Type {
    }
}
