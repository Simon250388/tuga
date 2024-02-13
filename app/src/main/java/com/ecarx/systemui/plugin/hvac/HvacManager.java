package com.ecarx.systemui.plugin.hvac;

import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.base.ICarInfo;
import com.ecarx.xui.adaptapi.car.hvac.IHvac;
import com.ecarx.xui.adaptapi.car.sensor.ISensor;
import com.ecarx.xui.adaptapi.car.sensor.ISensorEvent;

/* loaded from: classes.dex */
public class HvacManager {
    public static final int HVAC_TYPE_ELECTRIC = 100000;
    private static final String TAG = HvacManager.class.getSimpleName();
    private ICarInfo mICarInfo;

    private int getZone(boolean z, int i, int i2) {
        return i != 1 ? i != 2 ? i != 3 ? z ? 1 : 4 : i2 == 1049345 ? z ? 256 : 1024 : i2 == 1049346 ? z ? 1024 : 256 : z ? 256 : 1024 : i2 == 1049345 ? z ? 16 : 64 : i2 == 1049346 ? z ? 64 : 16 : z ? 16 : 64 : i2 == 1049345 ? z ? 1 : 4 : i2 == 1049346 ? z ? 4 : 1 : z ? 1 : 4;
    }

    public int getHvacType() {
        return -1;
    }

    public boolean isADSOn() {
        return false;
    }

    public boolean isADSclickable() {
        return false;
    }

    public boolean isHavcDisplayOnIHU() {
        return false;
    }

    public boolean isIsStartStop() {
        return false;
    }

    public boolean isSupportADS() {
        return false;
    }

    public void registerFunctionValueWatcher() {
        FunctionManager.getInstance().registerFunctionValueWatcher();
    }

    public void release() {
        FunctionManager.getInstance().release();
    }

    public int getClimateZone() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_CLIMATE_ZONE;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static HvacManager INSTANCE = new HvacManager();

        private SingletonHolder() {
        }
    }

    public static HvacManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private HvacManager() {
        Log.d(TAG, "HvacManager");
        HvacLiveDataPolicy.getInstance();
        this.mICarInfo = CarUtil.getInstance().getICarInfo();
    }

    public boolean isHavcAvailable() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = 2097408;
        int i = FunctionManager.getInstance().getFunctionValue(baseParam).value;
        String str = TAG;
        Log.i(str, "isHavcAvailable = " + i);
        return (i == 2097409 || i == 2097410 || i == 2097411 || i == 2097414 || i == 2097412) ? false : true;
    }

    public boolean isSupportIGN() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = 2097408;
        return FunctionManager.getInstance().isFunctionSupport(baseParam).isFunctionSupport;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setAC(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AC;
        if (i != -1) {
            baseParam.zone = i;
        }
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setHvacRear2On(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_POWER;
        baseParam.zone = 128;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setHvacRear3On(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_POWER;
        baseParam.zone = 2048;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean isHvacRear2On() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_POWER;
        baseParam.zone = 128;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public boolean isHvacRear3On() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_POWER;
        baseParam.zone = 2048;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public BaseResult isSupportACPower(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_POWER;
        if (i != -1) {
            baseParam.zone = i;
        }
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setPower(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_POWER;
        if (i != -1) {
            baseParam.zone = i;
        }
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean isACPower(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_POWER;
        if (i != -1) {
            baseParam.zone = i;
        }
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public boolean isACPowerOne() {
        int autoFanLevel = getAutoFanLevel(8);
        String str = TAG;
        Log.d(str, "isACPowerOne blowerLevel:" + autoFanLevel);
        return autoFanLevel == 255 ? getFanLevel(8) > 0 : getAutoFanLevel(8) > 0;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setAutoMode(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO;
        if (i != -1) {
            baseParam.zone = i;
        }
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setAcMax(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AC_MAX;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public BaseResult isSupportBlowingMode(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_BLOWING_MODE;
        if (i != -1) {
            baseParam.zone = i;
        }
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setBlowingMode(int i, int i2) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_BLOWING_MODE;
        if (i2 != -1) {
            baseParam.zone = i2;
        }
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setFrontDefrosting(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DEFROST_FRONT;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setRearDefrosting(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DEFROST_REAR;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public BaseResult isSupportInnerLoopMode() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_CIRCULATION;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setInnerLoopMode(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_CIRCULATION;
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Integer] */
    public boolean setInnerLoopModeLong() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_CIRCULATION_LONG_TOUCH;
        baseParam.operator = 2;
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setSync(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_TEMP_DUAL;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setECO(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_ECO_SWITCH;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public BaseResult isSupportECO() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_ECO_SWITCH;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public boolean getECO() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_ECO_SWITCH;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setParkingAirPreConditioningOnOff(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_PRE_CLIMATISATION;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean isParkingAirPreConditioningOn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_PRE_CLIMATISATION;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public boolean setDriverSeatHeating(int i) {
        return setSeatHeating(true, i, 1);
    }

    public boolean setPassengerSeatHeating(int i) {
        return setSeatHeating(false, i, 1);
    }

    public boolean setRearLeftSeatHeating(int i) {
        return setSeatHeating(true, i, 2);
    }

    public boolean setRearRightSeatHeating(int i) {
        return setSeatHeating(false, i, 2);
    }

    public boolean setDriverAutoSeatHeating(int i) {
        return setAutoSeatHeating(true, i, 1);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Integer] */
    public boolean setSeatHeating(boolean z, int i, int i2) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_HEATING;
        baseParam.zone = getDriverVehicleSeatZone(z, i2);
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Integer] */
    public boolean setAutoSeatHeating(boolean z, int i, int i2) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_HEATING;
        baseParam.zone = getDriverVehicleSeatZone(z, i2);
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean setDriverVentilationLevel(int i) {
        return setVentilationLevel(true, i, 1);
    }

    public boolean setPassengerVentilationLevel(int i) {
        return setVentilationLevel(false, i, 1);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Integer] */
    public boolean setVentilationLevel(boolean z, int i, int i2) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_VENTILATION;
        baseParam.zone = getDriverVehicleSeatZone(z, i2);
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Integer] */
    public boolean setSeatMassageLevel(boolean z, int i, int i2) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_VENTILATION;
        baseParam.zone = getDriverVehicleSeatZone(z, i2);
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public BaseResult isSupportSteeringWheelHeat() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_STEERING_WHEEL_HEAT;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setSteeringWheelHeatLevel(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_STEERING_WHEEL_HEAT;
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public int[] getWheelHeatValue() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_STEERING_WHEEL_HEAT;
        return FunctionManager.getInstance().isFunctionSupport(baseParam).supportValues;
    }

    public int getWheelHeatLevel() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_STEERING_WHEEL_HEAT;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public int getSeatHeatingDuration(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_HEATING_TIME;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        switch (FunctionManager.getInstance().getFunctionValue(baseParam).value) {
            case 268764673:
                return 0;
            case 268764674:
            default:
                return 1;
            case IHvac.AUTO_SEAT_HEATING_TIME_3 /* 268764675 */:
                return 2;
        }
    }

    public BaseResult isSupportSeatHeatingDuration(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_HEATING_TIME;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public int getWheelHeatingDuration() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT_TIME;
        switch (FunctionManager.getInstance().getFunctionValue(baseParam).value) {
            case IHvac.AUTO_STEERING_WHEEL_HEAT_TIME_1 /* 269026049 */:
                return 0;
            case IHvac.AUTO_STEERING_WHEEL_HEAT_TIME_2 /* 269026050 */:
            default:
                return 1;
            case IHvac.AUTO_STEERING_WHEEL_HEAT_TIME_3 /* 269026051 */:
                return 2;
        }
    }

    public BaseResult isSupportWheelHeatingDuration() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT_TIME;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportVentilationDuration(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION_TIME;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public int getSeatVentilationDuration(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION_TIME;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        switch (FunctionManager.getInstance().getFunctionValue(baseParam).value) {
            case 268764161:
                return 0;
            case 268764162:
            default:
                return 1;
            case IHvac.AUTO_SEAT_VENTILATION_TIME_3 /* 268764163 */:
                return 2;
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Integer] */
    public boolean setSeatHeatingDuration(boolean z, int i, int i2) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_HEATING_TIME;
        baseParam.zone = getDriverVehicleSeatZone(z, i2);
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setWheelHeatingDuration(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT_TIME;
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Integer] */
    public boolean setSeatMassageDuration(boolean z, int i, int i2) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_MASSAGE;
        baseParam.zone = getDriverVehicleSeatZone(z, i2);
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Integer] */
    public boolean setSeatVentilationDuration(boolean z, int i, int i2) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION_TIME;
        baseParam.zone = getDriverVehicleSeatZone(z, i2);
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public BaseResult isSupportSync() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_TEMP_DUAL;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportAuto(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO;
        if (i != -1) {
            baseParam.zone = i;
        }
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportACMax() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AC_MAX;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportAC(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AC;
        if (i != -1) {
            baseParam.zone = i;
        }
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportRearDefrost() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DEFROST_REAR;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportFrontDefrost() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DEFROST_FRONT;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setIons(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_IONS_SWITCH;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public BaseResult isSupportIons() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_ION;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public boolean getIonsOn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_ION;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public BaseResult isSupportTwoRearSeatHvac() {
        BaseResult baseResult = new BaseResult();
        baseResult.functionStatus = FunctionStatus.notavailable;
        return baseResult;
    }

    public BaseResult isSupportThreeRearSeatHvac() {
        BaseResult baseResult = new BaseResult();
        baseResult.functionStatus = FunctionStatus.notavailable;
        return baseResult;
    }

    public BaseResult isSupportAirPreConditioning() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_PRE_CLIMATISATION;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportSeatHeatAuto(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_HEATING;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportAutoSeatHeat(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_HEATING;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportAutoSeatVentilation(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportAutoSeatMassage(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_MASSAGE;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public boolean isAutoSeatHeatOn(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_SEAT_HEATING;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public BaseResult isSupportSeatHeat(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_HEATING;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportSeatVentilation(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_VENTILATION;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportSeatMassage(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_MASSAGE;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportAQS() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AQS_SWITCH;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setAQS(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AQS_SWITCH;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean isSyncOn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_TEMP_DUAL;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public boolean isACOn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AC;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public boolean getAutoOn(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO;
        if (i != -1) {
            baseParam.zone = i;
        }
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public boolean getAcMaxOn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AC_MAX;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public boolean getRearDefrosting() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DEFROST_REAR;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public boolean getFrontDefrosting() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DEFROST_FRONT;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public BaseResult isSupportFrontDefrostMax() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DEFROST_FRONT_MAX;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public boolean getFrontDefrostingMax() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DEFROST_FRONT_MAX;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setFrontDefrostingMax(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DEFROST_FRONT_MAX;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean setADS(boolean z) {
        String str = TAG;
        Log.d(str, "setADS  flag:" + z);
        return false;
    }

    public boolean getAQSOn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AQS_SWITCH;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public int getCirculationMode() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_CIRCULATION;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setFragranceLevel(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 269157120;
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setFragranceType(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 269156864;
        baseParam.operator = Integer.valueOf(i);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean isFragranceOn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 269156608;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public BaseResult isSupportFragrance() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 269156608;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public int getFragranceType() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 269156864;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public int getFragranceLevel() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 269157120;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public boolean isIsEngineRunning() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = 2097408;
        int i = FunctionManager.getInstance().getFunctionValue(baseParam).value;
        String str = TAG;
        Log.i(str, "isIsEngineRunning = " + i);
        return i == 2097415;
    }

    public BaseResult isSupportVehicleInPM25() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = 1049344;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportVehicleOutPM25() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = 1049088;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportAmbientTemperature() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = 1051392;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public int getIndoorPM25AQI() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = 1049344;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public int getAmbientPM25AQI() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = 1049088;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public int getPM25SensorStateIn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = ISensor.SENSOR_TYPE_PM25_STATE_INDOOR;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public int getPM25SensorStateOut() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = ISensor.SENSOR_TYPE_PM25_STATE_AMBIENT;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public BaseResult isSupportAQIAmbient() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = ISensor.SENSOR_TYPE_AQI_LEVEL_AMBIENT;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public int getAQIAmbientLevel() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = ISensor.SENSOR_TYPE_AQI_LEVEL_AMBIENT;
        int i = FunctionManager.getInstance().getFunctionValue(baseParam).value;
        String str = TAG;
        Log.i(str, "getAQIAmbientLevel = " + i);
        if (i != -1) {
            switch (i) {
                case ISensorEvent.AQI_LEVEL_NO_POLLUTION /* 2106113 */:
                    return 2;
                case ISensorEvent.AQI_LEVEL_LOW_POLLUTION /* 2106114 */:
                    return 3;
                case ISensorEvent.AQI_LEVEL_MEDIUM_POLLUTION /* 2106115 */:
                    return 4;
                case ISensorEvent.AQI_LEVEL_HIGH_POLLUTION /* 2106116 */:
                case ISensorEvent.AQI_LEVEL_HIGHER_POLLUTION /* 2106117 */:
                case ISensorEvent.AQI_LEVEL_LOWER_POLLUTION /* 2106118 */:
                    return 5;
                default:
                    return 1;
            }
        }
        return 1;
    }

    public float getAmbientTemperature() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 1;
        baseParam.functionId = 1051392;
        return FunctionManager.getInstance().getFunctionValue(baseParam).customValue;
    }

    public boolean isLockOn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_CLIMATE_LOCK;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    public BaseResult isSupportLock() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_CLIMATE_LOCK;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setControlLock(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_CLIMATE_LOCK;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public int getBlowingMode(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_BLOWING_MODE;
        if (i != -1) {
            baseParam.zone = i;
        }
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Integer] */
    public boolean setFanLevel(int i, int i2) {
        int i3 = 268566785;
        switch (i) {
            case 2:
                i3 = 268566786;
                break;
            case 3:
                i3 = IHvac.FAN_SPEED_LEVEL_3;
                break;
            case 4:
                i3 = IHvac.FAN_SPEED_LEVEL_4;
                break;
            case 5:
                i3 = IHvac.FAN_SPEED_LEVEL_5;
                break;
            case 6:
                i3 = IHvac.FAN_SPEED_LEVEL_6;
                break;
            case 7:
                i3 = IHvac.FAN_SPEED_LEVEL_7;
                break;
            case 8:
                i3 = IHvac.FAN_SPEED_LEVEL_8;
                break;
            case 9:
                i3 = IHvac.FAN_SPEED_LEVEL_9;
                break;
        }
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_FAN_SPEED;
        baseParam.operator = Integer.valueOf(i3);
        if (i2 != -1) {
            baseParam.zone = i2;
        }
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.Integer] */
    public boolean setAutoFanLevel(int i, int i2) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_FAN_SETTING;
        int i3 = 268567041;
        if (i == 1) {
            i3 = IHvac.AUTO_FAN_SETTING_QUIETER;
        } else if (i != 2) {
            if (i == 3) {
                i3 = 268567042;
            } else if (i == 4) {
                i3 = IHvac.AUTO_FAN_SETTING_HIGH;
            } else if (i == 5) {
                i3 = IHvac.AUTO_FAN_SETTING_HIGHER;
            }
        }
        baseParam.operator = Integer.valueOf(i3);
        baseParam.zone = i2;
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public BaseResult isSupportFan() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_FAN_SPEED;
        baseParam.zone = 8;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public BaseResult isSupportAutoFan() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_FAN_SETTING;
        baseParam.zone = 8;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public int getFanMaxLevel() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_FAN_SPEED;
        baseParam.zone = 8;
        int[] iArr = FunctionManager.getInstance().isFunctionSupport(baseParam).supportValues;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("getFanMaxLevel == ");
        sb.append(iArr == null ? "null" : Integer.valueOf(iArr.length - 1));
        Log.i(str, sb.toString());
        if (iArr == null || iArr.length == 0) {
            return 9;
        }
        return iArr.length - 1;
    }

    public int getFanMaxLevelId() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_FAN_SPEED;
        int[] iArr = FunctionManager.getInstance().isFunctionSupport(baseParam).supportValues;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("getFanMaxLevel == ");
        sb.append(iArr == null ? "null" : Integer.valueOf(iArr.length - 1));
        Log.i(str, sb.toString());
        return iArr == null ? IHvac.FAN_SPEED_LEVEL_9 : iArr[iArr.length - 1];
    }

    public int getAutoFanMaxLevel() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_FAN_SETTING;
        baseParam.zone = 8;
        int[] iArr = FunctionManager.getInstance().isFunctionSupport(baseParam).supportValues;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("getAutoFanMaxLevel == ");
        sb.append(iArr == null ? "null" : Integer.valueOf(iArr.length));
        Log.i(str, sb.toString());
        if (iArr == null || iArr.length == 0) {
            return 5;
        }
        return iArr.length;
    }

    public int getAutoFanMaxLevelId() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_FAN_SETTING;
        int[] iArr = FunctionManager.getInstance().isFunctionSupport(baseParam).supportValues;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("getAutoFanMaxLevel == ");
        sb.append(iArr == null ? "null" : Integer.valueOf(iArr.length));
        Log.i(str, sb.toString());
        return iArr == null ? IHvac.AUTO_FAN_SETTING_HIGHER : iArr[iArr.length];
    }

    public int getFanLevel(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_FAN_SPEED;
        if (i != -1) {
            baseParam.zone = i;
        }
        int i2 = FunctionManager.getInstance().getFunctionValue(baseParam).value;
        switch (i2) {
            case 268566785:
                return 1;
            case 268566786:
                return 2;
            case IHvac.FAN_SPEED_LEVEL_3 /* 268566787 */:
                return 3;
            case IHvac.FAN_SPEED_LEVEL_4 /* 268566788 */:
                return 4;
            case IHvac.FAN_SPEED_LEVEL_5 /* 268566789 */:
                return 5;
            case IHvac.FAN_SPEED_LEVEL_6 /* 268566790 */:
                return 6;
            case IHvac.FAN_SPEED_LEVEL_7 /* 268566791 */:
                return 7;
            case IHvac.FAN_SPEED_LEVEL_8 /* 268566792 */:
                return 8;
            case IHvac.FAN_SPEED_LEVEL_9 /* 268566793 */:
                return 9;
            default:
                return i2;
        }
    }

    public int getAutoFanLevel(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_FAN_SETTING;
        baseParam.zone = i;
        int i2 = FunctionManager.getInstance().getFunctionValue(baseParam).value;
        switch (i2) {
            case 268567041:
                return 2;
            case 268567042:
                return 3;
            case IHvac.AUTO_FAN_SETTING_HIGH /* 268567043 */:
                return 4;
            case IHvac.AUTO_FAN_SETTING_QUIETER /* 268567044 */:
                return 1;
            case IHvac.AUTO_FAN_SETTING_HIGHER /* 268567045 */:
                return 5;
            default:
                return i2;
        }
    }

    public int[] getSeatHeatingSupportValue(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_HEATING;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam).supportValues;
    }

    public int getSeatHeatingLevel(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_HEATING;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        int i2 = FunctionManager.getInstance().getFunctionValue(baseParam).value;
        String str = TAG;
        Log.d(str, "getSeatHeatingLevel isDriver:" + z + "   level:" + i2);
        return i2;
    }

    public int[] getSeatVentilationSupportValue(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_VENTILATION;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam).supportValues;
    }

    public int getSeatVentilationLevel(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_VENTILATION;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public int[] getSeatMassageSupportValue(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_MASSAGE;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().isFunctionSupport(baseParam).supportValues;
    }

    public int getSeatMassageLevel(boolean z, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_SEAT_MASSAGE;
        baseParam.zone = getDriverVehicleSeatZone(z, i);
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public BaseResult isSupportTemperature(int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268828928;
        baseParam.zone = i;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public float getLeftTemperature() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268828928;
        baseParam.zone = 1;
        float f = FunctionManager.getInstance().getCustomizeFunctionValue(baseParam).customValue;
        String str = TAG;
        Log.i(str, "getLeftTemperature = " + f);
        return f;
    }

    public float getRightTemperature() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268828928;
        baseParam.zone = 4;
        float f = FunctionManager.getInstance().getCustomizeFunctionValue(baseParam).customValue;
        String str = TAG;
        Log.i(str, "getRightTemperature = " + f);
        return f;
    }

    public float getRearTemperature() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268828928;
        baseParam.zone = 16;
        float f = FunctionManager.getInstance().getCustomizeFunctionValue(baseParam).customValue;
        String str = TAG;
        Log.i(str, "getRearTemperature = " + f);
        return f;
    }

    public float getMinTemperature() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268829440;
        float f = FunctionManager.getInstance().getCustomizeFunctionValue(baseParam).customValue;
        String str = TAG;
        Log.d(str, "getMinTemperature level:" + f);
        return f;
    }

    public float getMaxTemperature() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268829184;
        float f = FunctionManager.getInstance().getCustomizeFunctionValue(baseParam).customValue;
        String str = TAG;
        Log.d(str, "getMaxTemperature level:" + f);
        return f;
    }

    public float getTemperatureStep() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_TEMP_STEP;
        float f = FunctionManager.getInstance().getCustomizeFunctionValue(baseParam).customValue;
        String str = TAG;
        Log.d(str, "getTemperatureStep step:" + f);
        return f;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Float, T] */
    public boolean setLeftTemperature(float f) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268828928;
        baseParam.zone = 1;
        baseParam.operator = Float.valueOf(f);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Float, T] */
    public boolean setRightTemperature(float f) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268828928;
        baseParam.zone = 4;
        baseParam.operator = Float.valueOf(f);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Float, T] */
    public boolean setRearTemperature(float f) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268828928;
        baseParam.zone = 16;
        baseParam.operator = Float.valueOf(f);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public int getDriverVehicleSeatZone(boolean z, int i) {
        ICarInfo iCarInfo = this.mICarInfo;
        if (iCarInfo == null) {
            Log.d(TAG, "getDriverVehicleSeatZone ICarInfo is null");
            return getZone(z, i, ICarInfo.DRIVER_SIDE_LEFT);
        }
        int carInfoConfig = iCarInfo.getCarInfoConfig(1049344);
        String str = TAG;
        Log.i(str, "driverSide == " + carInfoConfig);
        return getZone(z, i, carInfoConfig);
    }

    public boolean isDriver(int i, int i2) {
        ICarInfo iCarInfo = this.mICarInfo;
        if (iCarInfo == null) {
            Log.i(TAG, "getDriverVehicleSeatZone ICarInfo is null");
            return i2 == 1 ? i == 1 : i2 == 2 ? i == 16 : i2 != 3 || i == 256;
        }
        int carInfoConfig = iCarInfo.getCarInfoConfig(1049344);
        String str = TAG;
        Log.i(str, "driverSide == " + carInfoConfig + " zone = " + i + "   index = " + i2);
        return carInfoConfig == 1049345 ? i2 == 1 ? i == 1 : i2 == 2 ? i == 16 : i2 != 3 || i == 256 : carInfoConfig == 1049346 ? i2 == 1 ? i == 4 : i2 == 2 ? i == 64 : i2 != 3 || i == 1024 : i2 == 1 ? i == 1 : i2 == 2 ? i == 16 : i2 != 3 || i == 256;
//        return true;
    }

    public boolean isSupportAutoDefrostPop() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_DEFROST_REQUEST;
        return FunctionManager.getInstance().isFunctionSupport(baseParam).isFunctionSupport;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setAutoDefrostOn(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_DEFROST_CONFIRM;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean isSupportAutoDehumidIfication() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_DEHUMIDIFICATION_REQUEST;
        return FunctionManager.getInstance().isFunctionSupport(baseParam).isFunctionSupport;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setAutoDehumidIficationOn(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_DEHUMIDIFICATION_CONFIRM;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean isSupportAutoCloseWindow() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND_REQUEST;
        return FunctionManager.getInstance().isFunctionSupport(baseParam).isFunctionSupport;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setAutoCloseWindowOn(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND_CONFIRM;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public boolean isSupportTempUnit() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268830208;
        return FunctionManager.getInstance().isFunctionSupport(baseParam).isFunctionSupport;
    }

    public int getTempUnit() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = 268830208;
        int i = FunctionManager.getInstance().getFunctionValue(baseParam).value;
        String str = TAG;
        Log.i(str, "getTempUnit  = " + i);
        return i;
    }

    public boolean isSupportDisPlayWindowTab() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_DISPLAY_WINDOW_TAB;
        return FunctionManager.getInstance().isFunctionSupport(baseParam).isFunctionSupport;
    }

    public BaseResult isSupportGClean() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_G_CLEAN;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public boolean isGCleanOn() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_G_CLEAN;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value == 1;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setGClean(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_G_CLEAN;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }

    public BaseResult isSupportBlowingColor() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_BLOWING_TEMP_COLOR;
        return FunctionManager.getInstance().isFunctionSupport(baseParam);
    }

    public int getBlowingColor() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_BLOWING_TEMP_COLOR;
        return FunctionManager.getInstance().getFunctionValue(baseParam).value;
    }

    public boolean isSupportSmartHvacRecommend() {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_INTELLIGENT_RECOMMENDATION;
        return FunctionManager.getInstance().isFunctionSupport(baseParam).isFunctionSupport;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Integer] */
    public boolean setTempOptimize(boolean z) {
        BaseParam baseParam = new BaseParam();
        baseParam.type = 2;
        baseParam.functionId = IHvac.HVAC_FUNC_TEMP_OPTIMIZE;
        baseParam.operator = Integer.valueOf(z ? 1 : 0);
        return FunctionManager.getInstance().setFunctionValue(baseParam).setFunctionSuccess;
    }
}
