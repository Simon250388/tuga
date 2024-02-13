package com.ecarx.systemui.plugin.hvac;

/* loaded from: classes.dex */
public interface IHvacApi {

    /* loaded from: classes.dex */
    public interface IGetFuncApi {
        boolean getAcMode();

        int getAotoFanLevel();

        boolean getAutoMode();

        int getBlowingMode();

        boolean getCirculationMode();

        int getDriverAutoSeatHeating();

        int getDriverAutoSeatVentilation();

        int getDriverSeatHeating();

        int getDriverSeatVentilation();

        float getDriverTemp();

        boolean getDualMode();

        boolean getEcoMode();

        int getFanLevel();

        boolean getFrontDefrostMaxMode();

        boolean getFrontDefrostMode();

        boolean getHvacPower();

        int getHvacTempMode();

        int getHvacTempUnit();

        boolean getMaxAcMode();

        int getMaxAutoFanLevel();

        int getMaxFanLevel();

        int getPassengerAutoSeatHeating();

        int getPassengerAutoSeatVentilation();

        int getPassengerSeatHeating();

        int getPassengerSeatVentilation();

        float getPassengerTemp();

        boolean getRearDefrostMode();

        int getSteeringWheelHeatingLevel();

        float getTempMaxValue();

        float getTempMinValue();

        float getTempStepingValue();
    }

    /* loaded from: classes.dex */
    public interface ISetFuncApi {
        boolean setAcMode(boolean z);

        boolean setAutoFanLevel(int i);

        boolean setAutoMode(boolean z);

        boolean setBlowingMode(int i);

        boolean setCirculationMode(boolean z);

        boolean setDriverSeatHeating(int i);

        boolean setDriverSeatVentilation(int i);

        boolean setDriverTemp(float f);

        boolean setDualMode(boolean z);

        boolean setFanLevel(int i);

        boolean setFrontDefrostMaxMode(boolean z);

        boolean setFrontDefrostMode(boolean z);

        boolean setHvacPower(boolean z);

        boolean setMaxAcMode(boolean z);

        boolean setPassengerSeatHeating(int i);

        boolean setPassengerSeatVentilation(int i);

        boolean setPassengerTemp(float f);

        boolean setRearDefrostMode(boolean z);

        boolean setSteeringWheelHeatingLevel(int i);

        boolean toNextBlowingMode();

        boolean toNextMainSeatHeatingLevel();

        boolean toNextSteeringWheelHeatingLevel();

        boolean toNextViceSeatHeatingLevel();
    }

    /* loaded from: classes.dex */
    public interface ISupportFuncApi {
        boolean isSupportAc();

        boolean isSupportAuto();

        boolean isSupportAutoFan();

        boolean isSupportBlowingMode();

        boolean isSupportCirculation();

        boolean isSupportDriverAutoSeatHeating();

        boolean isSupportDriverAutoSeatVentilation();

        boolean isSupportDriverSeatHeating();

        boolean isSupportDriverSeatVentilation();

        boolean isSupportDriverTemp();

        boolean isSupportDual();

        boolean isSupportEco();

        boolean isSupportFan();

        boolean isSupportFrontDefrost();

        boolean isSupportFrontDefrostMax();

        boolean isSupportMaxAc();

        boolean isSupportPassengerAutoSeatHeating();

        boolean isSupportPassengerAutoSeatVentilation();

        boolean isSupportPassengerSeatHeating();

        boolean isSupportPassengerSeatVentilation();

        boolean isSupportPassengerTemp();

        boolean isSupportPower();

        boolean isSupportRearDefrost();

        boolean isSupportSteeringWheelHeating();
    }
}
