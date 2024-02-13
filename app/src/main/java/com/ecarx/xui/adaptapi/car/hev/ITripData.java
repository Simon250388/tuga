package com.ecarx.xui.adaptapi.car.hev;

import com.ecarx.xui.adaptapi.FunctionStatus;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface ITripData {
    public static final int AUTO_RESET_OPTION_4_HOURS = 612369153;
    public static final int AUTO_RESET_OPTION_CHARGING = 612369154;
    public static final int AUTO_RESET_OPTION_PARKING = 612369156;
    public static final int AUTO_RESET_OPTION_PARKING_OIL = 612369155;
    public static final int RESET_OPTION_AUTO = 612368641;
    public static final int RESET_OPTION_MANUAL = 612368642;
    public static final int TRIP_DC_AVERAGE_FUEL_CONSUMPTION = 612369924;
    public static final int TRIP_DC_AVERAGE_SPEED = 612369922;
    public static final int TRIP_DC_SUBTOTAL_MILEAGE = 612369921;
    public static final int TRIP_DC_TRAVEL_TIME = 612369923;
    public static final int TRIP_DI_AVG_ENE_CONSUMPTION = 8195;
    public static final int TRIP_DI_AVG_SPEED = 8194;
    public static final int TRIP_DI_TOTAL_REGENERATION = 8193;
    public static final int TRIP_ED_BATTERY_CLIMATE_PERCENTAGE = 12290;
    public static final int TRIP_ED_CABIN_CLIMATE_PERCENTAGE = 12291;
    public static final int TRIP_ED_ENTERTAINMENT_PERCENTAGE = 12293;
    public static final int TRIP_ED_LIGHT_PERCENTAGE = 12292;
    public static final int TRIP_ED_OTHER_PERCENTAGE = 12543;
    public static final int TRIP_ED_PROPULSION_PERCENTAGE = 12289;
    public static final int TRIP_FUNC_AUTO_RESET_OPTION = 612369152;
    public static final int TRIP_FUNC_DRIVING_COMPUTER = 612369920;
    public static final int TRIP_FUNC_RESET = 612368896;
    public static final int TRIP_FUNC_RESET_OPTION = 612368640;
    public static final int TRIP_INFO_TYPE_AVG_ENERGY = 4096;
    public static final int TRIP_INFO_TYPE_DEFAULT = 0;
    public static final int TRIP_INFO_TYPE_DRIVING_INFO = 8192;
    public static final int TRIP_INFO_TYPE_ENERGY_DISTRIBUTION = 12288;
    public static final int TRIP_TYPE_DEFAULT = 0;
    public static final int TRIP_TYPE_TRIP2 = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AutoResetTripOption {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ComputerInfoIds {
    }

    /* loaded from: classes.dex */
    public interface IAvgEnergyInfo extends ITripInfo {
        float getAvgEleConsumption();

        float getAvgEnergyFeedback();

        float getAvgFuelConsumption();
    }

    /* loaded from: classes.dex */
    public interface IDrivingInfo extends ITripInfo {
        float getEleDrivingPercentage();

        int getTripDistance();

        int getTripDistanceByEle();

        int getTripDistanceByFuel();

        int getTripDistanceInCurrentDay();

        long getTripDuration();

        float getTripEleConsumption();

        float getTripFuelConsumption();
    }

    /* loaded from: classes.dex */
    public interface ITripInfo {
        boolean containsInfoId(int i);

        int[] getContainsInfoIds();

        int getFrequencyUnit();

        int getInfoType();

        float getInfoValue(int i);

        int getTripType();
    }

    /* loaded from: classes.dex */
    public interface ITripInfoListener extends ITripListener {
        void onTripDataReset(int i);

        void onTripInfoUpdate(ITripInfo iTripInfo);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface ITripListener {
        void onAvgEnergyInfoUpdate(IAvgEnergyInfo iAvgEnergyInfo);

        void onDrivingInfoUpdate(IDrivingInfo iDrivingInfo);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface InfoIds {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface InfoType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ResetTripOption {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TripFunction {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TripType {
    }

    IAvgEnergyInfo getLatestAvgEnergyInfo();

    IDrivingInfo getLatestDrivingInfo();

    ITripInfo getLatestTripInfo(int i, int i2);

    int[] getSupportedUpdateFrequencyUnit();

    ITripInfo[] getTripInfo(int i, int i2);

    @Deprecated
    int getUpdateFrequencyUnit();

    FunctionStatus isTripDataSupported();

    FunctionStatus isTripDataSupported(int i);

    FunctionStatus isTripDataSupported(int i, int i2);

    void registerTripListener(int i, ITripListener iTripListener);

    void registerTripListener(ITripListener iTripListener);

    @Deprecated
    void setUpdateFrequencyUnit(int i);

    void unregisterTripListener(ITripListener iTripListener);
}
