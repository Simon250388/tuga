package com.ecarx.systemui.plugin.hvac;

import android.content.Context;
import android.util.Log;
import com.ecarx.systemui.plugin.hvac.HvacController;
import com.ecarx.systemui.plugin.hvac.IHvacApi;
import com.ecarx.systemui.plugin.utils.KtList;
import com.ecarx.systemui.plugin.utils.LogUtils;
import com.ecarx.systemui.plugin.utils.ThreadUtils;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.binder.IConnectable;
import com.ecarx.xui.adaptapi.car.Car;
import com.ecarx.xui.adaptapi.car.ICar;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;
import com.ecarx.xui.adaptapi.car.hvac.IHvac;

/* loaded from: classes.dex */
public class HvacController implements IHvacApi.ISupportFuncApi, IHvacApi.ISetFuncApi, IHvacApi.IGetFuncApi {
    private static HvacController INSTANCE;
    private static final int[] functionArray = {IHvac.HVAC_FUNC_POWER, IHvac.HVAC_FUNC_AUTO, IHvac.HVAC_FUNC_AC_MAX, IHvac.HVAC_FUNC_AC, IHvac.HVAC_FUNC_FAN_SPEED, IHvac.HVAC_FUNC_AUTO_FAN_SETTING, IHvac.HVAC_FUNC_BLOWING_TEMP_COLOR, IHvac.HVAC_FUNC_CIRCULATION, IHvac.HVAC_FUNC_DEFROST_FRONT_MAX, IHvac.HVAC_FUNC_SEAT_VENTILATION, IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION, IHvac.HVAC_FUNC_SEAT_HEATING, IHvac.HVAC_FUNC_AUTO_SEAT_HEATING, 268828928, IHvac.HVAC_FUNC_BLOWING_MODE, IHvac.HVAC_FUNC_ECO_SWITCH, IHvac.HVAC_FUNC_STEERING_WHEEL_HEAT, IHvac.HVAC_FUNC_TEMP_DUAL, 268830208};
    private final ICar mCar;
    private ICarFunction mCarFunction;
    private final Context mContext;
    private final String TAG = HvacController.class.getSimpleName();
    private final KtList<FunctionValueObserver> mObservers = new KtList<>(new FunctionValueObserver[0]);
    private final ICarFunction.IFunctionValueWatcher callback = new ICarFunction.IFunctionValueWatcher() { // from class: com.ecarx.systemui.plugin.hvac.HvacController.2
        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onFunctionChanged(int i) {
        }

        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onFunctionValueChanged(int i, int i2, int i3) {
            LogUtils.d(HvacController.this.TAG, "onFunctionValueChanged functionName is : " + i + ";zone : " + i2);
            HvacController.this.printLog(i, i2);
            for (int size = HvacController.this.mObservers.size() + (-1); size >= 0; size--) {
                ((FunctionValueObserver) HvacController.this.mObservers.get(size)).change(i, i2, i3);
            }
        }

        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onCustomizeFunctionValueChanged(int i, int i2, float f) {
            LogUtils.d(HvacController.this.TAG, "onCustomizeFunctionValueChanged functionName is : " + i + ";zone : " + i2);
            HvacController.this.printLog(i, i2);
            for (int size = HvacController.this.mObservers.size() + (-1); size >= 0; size--) {
                ((FunctionValueObserver) HvacController.this.mObservers.get(size)).change(i, i2, f);
            }
        }

        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onSupportedFunctionStatusChanged(int i, int i2, FunctionStatus functionStatus) {
            for (int size = HvacController.this.mObservers.size() - 1; size >= 0; size--) {
                ((FunctionValueObserver) HvacController.this.mObservers.get(size)).change(i, i2, functionStatus);
            }
        }

        @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
        public void onSupportedFunctionValueChanged(int i, int[] iArr) {
            for (int size = HvacController.this.mObservers.size() - 1; size >= 0; size--) {
                ((FunctionValueObserver) HvacController.this.mObservers.get(size)).change(i, iArr);
            }
        }
    };

    /* loaded from: classes.dex */
    public static class SimpleFunctionValueObserver extends FunctionValueObserver {
        @Override // com.ecarx.systemui.plugin.hvac.HvacController.FunctionValueObserver
        protected void onChanged(int i, int i2, float f) {
        }

        @Override // com.ecarx.systemui.plugin.hvac.HvacController.FunctionValueObserver
        protected void onChanged(int i, int i2, int i3) {
        }

        @Override // com.ecarx.systemui.plugin.hvac.HvacController.FunctionValueObserver
        protected void onChanged(int i, int i2, FunctionStatus functionStatus) {
        }

        @Override // com.ecarx.systemui.plugin.hvac.HvacController.FunctionValueObserver
        protected void onChanged(int i, int[] iArr) {
        }
    }

    public static HvacController init(Context context) {
        HvacController hvacController = new HvacController(context);
        INSTANCE = hvacController;
        return hvacController;
    }

    public static HvacController getInstance() {
        HvacController hvacController = INSTANCE;
        if (hvacController != null) {
            return hvacController;
        }
        throw new RuntimeException("HvacController 没有调用 init");
    }

    public HvacController(Context context) {
        this.mContext = context;
        ICar create = Car.create(context);
        this.mCar = create;
        if (create == null || !(create instanceof IConnectable)) {
            return;
        }
        ((IConnectable) create).registerConnectWatcher(new AnonymousClass1(context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ecarx.systemui.plugin.hvac.HvacController$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements IConnectable.IConnectWatcher {
        final /* synthetic */ Context val$context;

        @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
        public void onDisConnected() {
        }

        AnonymousClass1(Context context) {
            this.val$context = context;
        }

        @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
        public void onConnected() {
            final Context context = this.val$context;
            ThreadUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.hvac.-$$Lambda$HvacController$1$7JFAH_moNgwETDhilenD5emCGjg
                @Override // java.lang.Runnable
                public final void run() {
                    HvacController.AnonymousClass1.this.lambda$onConnected$0$HvacController$1(context);
                }
            });
        }

        public /* synthetic */ void lambda$onConnected$0$HvacController$1(Context context) {
            HvacController hvacController = HvacController.this;
            hvacController.mCarFunction = hvacController.mCar.getICarFunction();
            HvacController.this.registerCallback();
            LogUtils.d(HvacController.this.TAG, "CarService is connected");
            HvacLeftBar.getInstance(context).refreshView();
            HvacRightBar.getInstance(context).refreshView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerCallback() {
        String str = this.TAG;
        LogUtils.d(str, "registerCallback : " + this.mCarFunction);
        ICarFunction iCarFunction = this.mCarFunction;
        if (iCarFunction != null) {
            iCarFunction.registerFunctionValueWatcher(functionArray, this.callback);
        }
    }

    public void simulationEvent(int i) {
        Log.i(this.TAG, "simulationEvent  mode: " + i);
        for (int size = this.mObservers.size() + (-1); size >= 0; size--) {
            this.mObservers.get(size).change(i, 0, 0);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class FunctionValueObserver {
        protected int lastCode;

        protected abstract void onChanged(int i, int i2, float f);

        protected abstract void onChanged(int i, int i2, int i3);

        protected abstract void onChanged(int i, int i2, FunctionStatus functionStatus);

        protected abstract void onChanged(int i, int[] iArr);

        public void onRegistered() {
        }

        public void onUnregister() {
        }

        public void change(final int i, final int i2, final int i3) {
            ThreadUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.hvac.-$$Lambda$HvacController$FunctionValueObserver$oi5qSPkvXTaYgUfaUMhXIobKT_k
                @Override // java.lang.Runnable
                public final void run() {
                    HvacController.FunctionValueObserver.this.lambda$change$0$HvacController$FunctionValueObserver(i, i2, i3);
                }
            });
        }

        public /* synthetic */ void lambda$change$0$HvacController$FunctionValueObserver(int i, int i2, int i3) {
            onChanged(i, i2, i3);
            this.lastCode = i;
        }

        public void change(final int i, final int i2, final float f) {
            ThreadUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.hvac.-$$Lambda$HvacController$FunctionValueObserver$go7bGFGumcmr8Hr6wxPBsM8EeBk
                @Override // java.lang.Runnable
                public final void run() {
                    HvacController.FunctionValueObserver.this.lambda$change$1$HvacController$FunctionValueObserver(i, i2, f);
                }
            });
        }

        public /* synthetic */ void lambda$change$1$HvacController$FunctionValueObserver(int i, int i2, float f) {
            onChanged(i, i2, f);
            this.lastCode = i;
        }

        public void change(final int i, final int i2, final FunctionStatus functionStatus) {
            ThreadUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.hvac.-$$Lambda$HvacController$FunctionValueObserver$WfPJHVxN6XCj0nnDHSvWeUnXMIk
                @Override // java.lang.Runnable
                public final void run() {
                    HvacController.FunctionValueObserver.this.lambda$change$2$HvacController$FunctionValueObserver(i, i2, functionStatus);
                }
            });
        }

        public /* synthetic */ void lambda$change$2$HvacController$FunctionValueObserver(int i, int i2, FunctionStatus functionStatus) {
            onChanged(i, i2, functionStatus);
            this.lastCode = i;
        }

        public void change(final int i, final int[] iArr) {
            ThreadUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.hvac.-$$Lambda$HvacController$FunctionValueObserver$XBcyquDGOEL5s90CKvJBedP49JY
                @Override // java.lang.Runnable
                public final void run() {
                    HvacController.FunctionValueObserver.this.lambda$change$3$HvacController$FunctionValueObserver(i, iArr);
                }
            });
        }

        public /* synthetic */ void lambda$change$3$HvacController$FunctionValueObserver(int i, int[] iArr) {
            onChanged(i, iArr);
            this.lastCode = i;
        }
    }

    public void registerObserver(FunctionValueObserver functionValueObserver) {
        if (functionValueObserver == null) {
            throw new NullPointerException("observer not can null");
        }
        this.mObservers.add(functionValueObserver);
        functionValueObserver.onRegistered();
    }

    public void unregisterObserver(FunctionValueObserver functionValueObserver) {
        this.mObservers.remove(functionValueObserver);
        functionValueObserver.onUnregister();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printLog(int i, int i2) {
        LogUtils.d(this.TAG, "------>refresh start-------");
        switch (i) {
            case IHvac.HVAC_FUNC_POWER /* 268501248 */:
                String str = this.TAG;
                LogUtils.d(str, "powerStatus = " + getPowerStatus());
                String str2 = this.TAG;
                LogUtils.d(str2, "powerValue = " + getHvacPower());
                break;
            case IHvac.HVAC_FUNC_AUTO /* 268501504 */:
                String str3 = this.TAG;
                LogUtils.d(str3, "autoStatus = " + getAutoStatus());
                String str4 = this.TAG;
                LogUtils.d(str4, "autoValue = " + getAutoMode());
                break;
            case IHvac.HVAC_FUNC_AC_MAX /* 268502016 */:
                String str5 = this.TAG;
                LogUtils.d(str5, "acMaxStatus = " + getAcMaxStatus());
                String str6 = this.TAG;
                LogUtils.d(str6, "acMaxValue = " + getMaxAcMode());
                break;
            case IHvac.HVAC_FUNC_FAN_SPEED /* 268566784 */:
                String str7 = this.TAG;
                LogUtils.d(str7, "fanSpeedStatus = " + getWindSpeedStatus());
                String str8 = this.TAG;
                LogUtils.d(str8, "fanSpeedValue = " + getFanLevel());
                break;
            case IHvac.HVAC_FUNC_CIRCULATION /* 268632320 */:
                String str9 = this.TAG;
                LogUtils.d(str9, "circulationStatus = " + getCirculationModeStatus());
                String str10 = this.TAG;
                LogUtils.d(str10, "circulationValue = " + getCirculationMode());
                break;
            case IHvac.HVAC_FUNC_DEFROST_FRONT_MAX /* 268698112 */:
                String str11 = this.TAG;
                LogUtils.d(str11, "frontDefrostMaxStatus = " + getFrontDefrostMaxStatus());
                String str12 = this.TAG;
                LogUtils.d(str12, "frontDefrostMaxValue = " + getFrontDefrostMaxMode());
                break;
            case IHvac.HVAC_FUNC_SEAT_VENTILATION /* 268763392 */:
            case IHvac.HVAC_FUNC_SEAT_HEATING /* 268763648 */:
                String str13 = this.TAG;
                LogUtils.d(str13, "DriverSeatHeatingStatus = " + getDriverSeatHeatStatus());
                String str14 = this.TAG;
                LogUtils.d(str14, "PassengerSeatHeatingStatus = " + getPassengerSeatHeatStatus());
                String str15 = this.TAG;
                LogUtils.d(str15, "DriverSeatVentilationStatus = " + getDriverSeatVentilationStatus());
                String str16 = this.TAG;
                LogUtils.d(str16, "PassengerSeatVentilationStatus = " + getPassengerSeatVentilationStatus());
                String str17 = this.TAG;
                LogUtils.d(str17, "DriverSeatHeating = " + getDriverSeatHeating());
                String str18 = this.TAG;
                LogUtils.d(str18, "PassengerSeatHeating = " + getPassengerSeatHeating());
                String str19 = this.TAG;
                LogUtils.d(str19, "DriverSeatVentilation = " + getDriverSeatVentilation());
                String str20 = this.TAG;
                LogUtils.d(str20, "PassengerSeatVentilation = " + getPassengerSeatVentilation());
                break;
            case IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION /* 268763904 */:
            case IHvac.HVAC_FUNC_AUTO_SEAT_HEATING /* 268764416 */:
                String str21 = this.TAG;
                LogUtils.d(str21, "DriverAutoSeatHeatingStatus = " + getDriverAutoSeatHeatStatus());
                String str22 = this.TAG;
                LogUtils.d(str22, "PassengerAutoSeatHeatingStatus = " + getPassengerAutoSeatHeatStatus());
                String str23 = this.TAG;
                LogUtils.d(str23, "DriverAutoSeatVentilationStatus = " + getDriverAutoSeatVentilationStatus());
                String str24 = this.TAG;
                LogUtils.d(str24, "PassengerAutoSeatVentilationStatus = " + getPassengerAutoSeatVentilationStatus());
                String str25 = this.TAG;
                LogUtils.d(str25, "DriverAutoSeatHeating = " + getDriverAutoSeatHeating());
                String str26 = this.TAG;
                LogUtils.d(str26, "PassengerAutoSeatHeating = " + getPassengerAutoSeatHeating());
                String str27 = this.TAG;
                LogUtils.d(str27, "DriverAutoSeatVentilation = " + getDriverAutoSeatVentilation());
                String str28 = this.TAG;
                LogUtils.d(str28, "PassengerAutoSeatVentilation = " + getPassengerAutoSeatVentilation());
                break;
            case 268828928:
                String str29 = this.TAG;
                LogUtils.d(str29, "DriverTemp = " + getDriverTemp());
                String str30 = this.TAG;
                LogUtils.d(str30, "DriverTempStatus = " + getDriverTempStatus());
                String str31 = this.TAG;
                LogUtils.d(str31, "PassengerTemp = " + getPassengerTemp());
                String str32 = this.TAG;
                LogUtils.d(str32, "PassengerTempStatus = " + getPassengerTempStatus());
                break;
            case IHvac.HVAC_FUNC_BLOWING_MODE /* 268894464 */:
                String str33 = this.TAG;
                LogUtils.d(str33, "blowModeStatus = " + getWindDirectionStatus());
                String str34 = this.TAG;
                LogUtils.d(str34, "blowModeValue = " + getBlowingMode());
                break;
            case IHvac.HVAC_FUNC_ECO_SWITCH /* 268960000 */:
                String str35 = this.TAG;
                LogUtils.d(str35, "ecoStatus = " + getEcoStatus());
                String str36 = this.TAG;
                LogUtils.d(str36, "ecoValue = " + getEcoMode());
                break;
        }
        LogUtils.d(this.TAG, "------>refresh end-------");
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportPower() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_POWER));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportDriverTemp() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(268828928, 1));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportPassengerTemp() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(268828928, 4));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportBlowingMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_BLOWING_MODE, 8));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportFan() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_FAN_SPEED, 8));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportAutoFan() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_FAN_SETTING, 8));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ecarx.systemui.plugin.hvac.HvacController$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus;

        static {
            int[] iArr = new int[FunctionStatus.values().length];
            $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus = iArr;
            try {
                iArr[FunctionStatus.active.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus[FunctionStatus.notactive.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus[FunctionStatus.error.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus[FunctionStatus.notavailable.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public boolean getIsSuppordByFunctionStatus(FunctionStatus functionStatus) {
        return AnonymousClass3.$SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus[functionStatus.ordinal()] == 1;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportAuto() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportCirculation() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_CIRCULATION));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportAc() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AC));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportMaxAc() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AC_MAX));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportFrontDefrost() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_DEFROST_FRONT));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportFrontDefrostMax() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_DEFROST_FRONT_MAX));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportRearDefrost() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_DEFROST_REAR));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportDriverSeatHeating() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_SEAT_HEATING, 1));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportPassengerSeatHeating() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_SEAT_HEATING, 4));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportDriverSeatVentilation() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_SEAT_VENTILATION, 1));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportPassengerSeatVentilation() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_SEAT_VENTILATION, 4));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportDriverAutoSeatHeating() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_SEAT_HEATING, 1));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportPassengerAutoSeatHeating() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_SEAT_HEATING, 4));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportDriverAutoSeatVentilation() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION, 1));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportPassengerAutoSeatVentilation() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION, 4));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportDual() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_TEMP_DUAL));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportEco() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_ECO_SWITCH));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getMaxFanLevel() {
        try {
            if (this.mCarFunction == null) {
                return 0;
            }
            return this.mCarFunction.getSupportedFunctionValue(IHvac.HVAC_FUNC_FAN_SPEED, 8).length - 2;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return 0;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getMaxAutoFanLevel() {
        try {
            if (this.mCarFunction == null) {
                return 0;
            }
            return this.mCarFunction.getSupportedFunctionValue(IHvac.HVAC_FUNC_AUTO_FAN_SETTING, 8).length;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return 0;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getSteeringWheelHeatingLevel() {
        try {
            if (this.mCarFunction == null) {
                return 0;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_STEERING_WHEEL_HEAT);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return 0;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getHvacTempMode() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_BLOWING_TEMP_COLOR);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getHvacTempUnit() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(268830208);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISupportFuncApi
    public boolean isSupportSteeringWheelHeating() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return getIsSuppordByFunctionStatus(this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_STEERING_WHEEL_HEAT));
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setHvacPower(boolean z) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setHvacPower : " + z);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_POWER, z ? 1 : 0);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setDriverTemp(float f) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setDriverTemp : " + f);
            return this.mCarFunction.setCustomizeFunctionValue(268828928, 1, f);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setPassengerTemp(float f) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setPassengerTemp : " + f);
            return this.mCarFunction.setCustomizeFunctionValue(268828928, 4, f);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setBlowingMode(int i) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setBlowingMode : " + i);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_BLOWING_MODE, 8, i);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setFanLevel(int i) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setFanLevel : " + i);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_FAN_SPEED, 8, i);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setAutoMode(boolean z) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setAutoMode : " + z);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_AUTO, z ? 1 : 0);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setCirculationMode(boolean z) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setCirculationMode : " + z);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_CIRCULATION, z ? IHvac.CIRCULATION_INNER : IHvac.CIRCULATION_OUTSIDE);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setAcMode(boolean z) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setAcMode : " + z);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_AC, z ? 1 : 0);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setMaxAcMode(boolean z) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setMaxAcMode : " + z);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_AC_MAX, z ? 1 : 0);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setFrontDefrostMode(boolean z) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setFrontDefrostMode : " + z);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_DEFROST_FRONT, z ? 1 : 0);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setFrontDefrostMaxMode(boolean z) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setFrontDefrostMaxMode : " + z);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_DEFROST_FRONT_MAX, z ? 1 : 0);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setRearDefrostMode(boolean z) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setRearDefrostMode : " + z);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_DEFROST_REAR, z ? 1 : 0);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setDriverSeatHeating(int i) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setDriverSeatHeating : " + i);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_SEAT_HEATING, 1, i);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setPassengerSeatHeating(int i) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setPassengerSeatHeating : " + i);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_SEAT_HEATING, 4, i);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setDriverSeatVentilation(int i) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setDriverSeatVentilation : " + i);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_SEAT_VENTILATION, 1, i);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setPassengerSeatVentilation(int i) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setPassengerSeatVentilation : " + i);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_SEAT_VENTILATION, 4, i);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setDualMode(boolean z) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setDualMode : " + z);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_TEMP_DUAL, z ? 1 : 0);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setSteeringWheelHeatingLevel(int i) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_STEERING_WHEEL_HEAT, i);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean toNextSteeringWheelHeatingLevel() {
        KtList ktList = new KtList(0, Integer.valueOf((int) IHvac.STEERING_WHEEL_HEAT_LOW), Integer.valueOf((int) IHvac.STEERING_WHEEL_HEAT_MID), Integer.valueOf((int) IHvac.STEERING_WHEEL_HEAT_HIGH));
        if (ktList.indexOfPreviousOrLast(Integer.valueOf(getSteeringWheelHeatingLevel())) == null) {
            return false;
        }
        return setSteeringWheelHeatingLevel(((Integer) ktList.indexOfPreviousOrLast(Integer.valueOf(getSteeringWheelHeatingLevel()))).intValue());
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean toNextMainSeatHeatingLevel() {
        KtList ktList = new KtList(0, 268763649, 268763650, 268763651);
        if (ktList.indexOfPreviousOrLast(Integer.valueOf(getDriverSeatHeating())) == null) {
            return false;
        }
        return setDriverSeatHeating(((Integer) ktList.indexOfPreviousOrLast(Integer.valueOf(getDriverSeatHeating()))).intValue());
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean toNextViceSeatHeatingLevel() {
        KtList ktList = new KtList(0, 268763649, 268763650, 268763651);
        if (ktList.indexOfPreviousOrLast(Integer.valueOf(getPassengerSeatHeating())) == null) {
            return false;
        }
        return setPassengerSeatHeating(((Integer) ktList.indexOfPreviousOrLast(Integer.valueOf(getPassengerSeatHeating()))).intValue());
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean toNextBlowingMode() {
        KtList ktList = new KtList(268894465, Integer.valueOf((int) IHvac.BLOWING_MODE_FACE_AND_LEG), Integer.valueOf((int) IHvac.BLOWING_ALL), 268894466, Integer.valueOf((int) IHvac.BLOWING_MODE_LEG_AND_FRONT_WINDOW), Integer.valueOf((int) IHvac.BLOWING_MODE_FRONT_WINDOW), Integer.valueOf((int) IHvac.BLOWING_MODE_FACE_AND_FRONT_WINDOW), Integer.valueOf((int) IHvac.BLOWING_MODE_AUTO_SWITCH));
        if (ktList.indexOfNextOrFirst(Integer.valueOf(getBlowingMode())) == null) {
            return false;
        }
        return setBlowingMode(((Integer) ktList.indexOfNextOrFirst(Integer.valueOf(getBlowingMode()))).intValue());
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.ISetFuncApi
    public boolean setAutoFanLevel(int i) {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            String str = this.TAG;
            LogUtils.d(str, "setFanLevel : " + i);
            return this.mCarFunction.setFunctionValue(IHvac.HVAC_FUNC_AUTO_FAN_SETTING, 8, i);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getHvacPower() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_POWER) == 1;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public float getDriverTemp() {
        try {
            if (this.mCarFunction == null) {
                return Float.MIN_VALUE;
            }
            return this.mCarFunction.getCustomizeFunctionValue(268828928, 1);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Float.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public float getPassengerTemp() {
        try {
            if (this.mCarFunction == null) {
                return Float.MIN_VALUE;
            }
            return this.mCarFunction.getCustomizeFunctionValue(268828928, 4);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Float.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getBlowingMode() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_BLOWING_MODE, 8);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getFanLevel() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_FAN_SPEED, 8);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getAotoFanLevel() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_AUTO_FAN_SETTING, 8);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    public int getBlowingLevel() {
        int autoFanLevel = HvacManager.getInstance().getAutoFanLevel(8);
        LogUtils.stack("getBlowingLevel", Integer.valueOf(autoFanLevel));
        if (autoFanLevel == 255) {
            return HvacManager.getInstance().getFanLevel(8);
        }
        return HvacManager.getInstance().getAutoFanLevel(8);
    }

    public int getBlowingMaxLevel() {
        int autoFanLevel = HvacManager.getInstance().getAutoFanLevel(8);
        LogUtils.stack("getBlowingMaxLevel", Integer.valueOf(autoFanLevel));
        return autoFanLevel == 255 ? 9 : 5;
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getAutoMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_AUTO) == 1;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getCirculationMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_CIRCULATION) == 268632321;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getAcMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_AC) == 1;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getMaxAcMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_AC_MAX) == 1;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getFrontDefrostMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_DEFROST_FRONT) == 1;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getFrontDefrostMaxMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_DEFROST_FRONT_MAX) == 1;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getRearDefrostMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_DEFROST_REAR) == 1;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getEcoMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_ECO_SWITCH) == 1;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getDriverSeatHeating() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_SEAT_HEATING, 1);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getPassengerSeatHeating() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_SEAT_HEATING, 4);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getDriverSeatVentilation() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_SEAT_VENTILATION, 1);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getPassengerSeatVentilation() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_SEAT_VENTILATION, 4);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getDriverAutoSeatHeating() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_AUTO_SEAT_HEATING, 1);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getPassengerAutoSeatHeating() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_AUTO_SEAT_HEATING, 4);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getDriverAutoSeatVentilation() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION, 1);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public int getPassengerAutoSeatVentilation() {
        try {
            if (this.mCarFunction == null) {
                return Integer.MIN_VALUE;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION, 4);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public float getTempMinValue() {
        try {
            if (this.mCarFunction == null) {
                return Float.MIN_VALUE;
            }
            return this.mCarFunction.getCustomizeFunctionValue(268829440);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Float.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public float getTempMaxValue() {
        try {
            if (this.mCarFunction == null) {
                return Float.MIN_VALUE;
            }
            return this.mCarFunction.getCustomizeFunctionValue(268829184);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Float.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public float getTempStepingValue() {
        try {
            if (this.mCarFunction == null) {
                return Float.MIN_VALUE;
            }
            return this.mCarFunction.getCustomizeFunctionValue(IHvac.HVAC_FUNC_TEMP_STEP);
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return Float.MIN_VALUE;
        }
    }

    @Override // com.ecarx.systemui.plugin.hvac.IHvacApi.IGetFuncApi
    public boolean getDualMode() {
        try {
            if (this.mCarFunction == null) {
                return false;
            }
            return this.mCarFunction.getFunctionValue(IHvac.HVAC_FUNC_TEMP_DUAL) == 1;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return false;
        }
    }

    public FunctionStatus getPowerStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_POWER);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getAutoStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getAutoFanStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_FAN_SETTING, 8);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getAcMaxStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AC_MAX);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getAcStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AC);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getDriverTempStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(268828928, 1);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getPassengerTempStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(268828928, 4);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getWindSpeedStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_FAN_SPEED);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getWindDirectionStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_BLOWING_MODE);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getCirculationModeStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_CIRCULATION);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getFrontDefrostStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_DEFROST_FRONT);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getFrontDefrostMaxStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_DEFROST_FRONT_MAX);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getRearDefrostStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_DEFROST_REAR);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getDualStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_TEMP_DUAL);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getFanSpeedStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_FAN_SPEED);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getDriverSeatHeatStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_SEAT_HEATING, 1);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getPassengerSeatHeatStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_SEAT_HEATING, 4);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getDriverSeatVentilationStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_SEAT_VENTILATION, 1);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getPassengerSeatVentilationStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_SEAT_VENTILATION, 4);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getDriverAutoSeatHeatStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_SEAT_HEATING, 1);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getPassengerAutoSeatHeatStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_SEAT_HEATING, 4);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getDriverAutoSeatVentilationStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION, 1);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getPassengerAutoSeatVentilationStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION, 4);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }

    public FunctionStatus getEcoStatus() {
        try {
            if (this.mCarFunction != null) {
                return this.mCarFunction.isFunctionSupported(IHvac.HVAC_FUNC_ECO_SWITCH);
            }
            return FunctionStatus.error;
        } catch (Exception e) {
            LogUtils.e(this.TAG, e.toString());
            return FunctionStatus.error;
        }
    }
}
