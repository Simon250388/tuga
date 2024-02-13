package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconWithTypeEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.ThaderUtils;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.binder.IConnectable;
import com.ecarx.xui.adaptapi.car.Car;
import com.ecarx.xui.adaptapi.car.ICar;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;
import com.ecarx.xui.adaptapi.car.vehicle.IWpc;

/* loaded from: classes.dex */
public class CarController extends BaseController {
    private static final String TAG = CarController.class.getSimpleName();
    private static CarController mCarController;
    private ICarFunction.IFunctionValueWatcher functionValueWatcher;
    private ICar mICar;
    private ICarFunction mICarFunction;

    @Override // com.ecarx.systemui.plugin.policy.BaseController, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
    }

    private CarController(Context context) {
        super(context);
        this.functionValueWatcher = new ICarFunction.IFunctionValueWatcher() { // from class: com.ecarx.systemui.plugin.statusbar.policy.CarController.2
            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onFunctionChanged(int i) {
                String str = CarController.TAG;
                Log.i(str, "onFunctionChanged function == " + i);
                ThaderUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.CarController.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CarController.this.update();
                    }
                });
            }

            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onFunctionValueChanged(int i, int i2, int i3) {
                ThaderUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.CarController.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        CarController.this.update();
                    }
                });
                String str = CarController.TAG;
                Log.i(str, "onFunctionValueChanged function == " + Integer.toHexString(i) + "   zone = " + i2 + " value = " + i3);
            }

            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onCustomizeFunctionValueChanged(int i, int i2, float f) {
                ThaderUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.CarController.2.3
                    @Override // java.lang.Runnable
                    public void run() {
                        CarController.this.update();
                    }
                });
                String str = CarController.TAG;
                Log.i(str, "onCustomizeFunctionValueChanged function == " + Integer.toHexString(i) + "   zone = " + i2 + " value = " + f);
            }

            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onSupportedFunctionStatusChanged(int i, int i2, FunctionStatus functionStatus) {
                String str = CarController.TAG;
                Log.i(str, "onSupportedFunctionStatusChanged function == " + Integer.toHexString(i) + "   zone = " + i2 + " status = " + functionStatus);
            }

            @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction.IFunctionValueWatcher
            public void onSupportedFunctionValueChanged(int i, int[] iArr) {
                String str = CarController.TAG;
                Log.i(str, "onSupportedFunctionValueChanged function == " + Integer.toHexString(i) + " funcValues = " + iArr);
            }
        };
    }

    public static CarController getInstance(Context context) {
        if (mCarController == null) {
            synchronized (CarController.class) {
                if (mCarController == null) {
                    CarController carController = new CarController(context);
                    mCarController = carController;
                    carController.init();
                }
            }
        }
        return mCarController;
    }

    private void init() {
        try {
            this.mICar = Car.create(this.mContext);
            String str = TAG;
            Log.i(str, "[JRSYS_WPC][init], mICar: " + this.mICar);
            IConnectable iConnectable = (IConnectable) this.mICar;
            iConnectable.connect();
            iConnectable.registerConnectWatcher(new IConnectable.IConnectWatcher() { // from class: com.ecarx.systemui.plugin.statusbar.policy.CarController.1
                @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
                public void onDisConnected() {
                }

                @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
                public void onConnected() {
                    ThaderUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.CarController.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            String str2 = CarController.TAG;
                            Log.i(str2, "[JRSYS_WPC][onConnected], mICar: " + CarController.this.mICar);
                            CarController.this.initWPC();
                        }
                    });
                }
            });
        } catch (Throwable th) {
            String str2 = TAG;
            Log.e(str2, "[JRSYS_WPC][init], throwable: " + Log.getStackTraceString(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initWPC() {
        try {
            this.mICarFunction = this.mICar.getICarFunction();
        } catch (Throwable th) {
            Lg.e(TAG, "init adapter api wpc error", th);
        }
        if (!isWPCSupported() && !isWPCAvailable()) {
            Log.w(TAG, "WPCController init, not supported");
            return;
        }
        if (this.mICarFunction != null) {
            this.mICarFunction.registerFunctionValueWatcher(new int[]{IWpc.WPC_FUNC_WORK_MODE, IWpc.WPC_FUNC_CHARGE_STATES}, this.functionValueWatcher);
        }
        Log.i(TAG, "WPCController init");
        update();
    }

    private boolean isWPCSupported() {
        ICarFunction iCarFunction = this.mICarFunction;
        if (iCarFunction != null) {
            FunctionStatus isFunctionSupported = iCarFunction.isFunctionSupported(IWpc.WPC_FUNC_WORK_MODE);
            String str = TAG;
            Log.i(str, "FunctionStatus is " + isFunctionSupported);
            int i = AnonymousClass3.$SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus[isFunctionSupported.ordinal()];
            if (i == 1 || i == 2) {
                return false;
            }
            return i == 3 || i == 4;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ecarx.systemui.plugin.statusbar.policy.CarController$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus;

        static {
            int[] iArr = new int[FunctionStatus.values().length];
            $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus = iArr;
            try {
                iArr[FunctionStatus.notavailable.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus[FunctionStatus.error.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus[FunctionStatus.active.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus[FunctionStatus.notactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private boolean isWPCAvailable() {
        ICarFunction iCarFunction = this.mICarFunction;
        if (iCarFunction != null) {
            FunctionStatus isFunctionSupported = iCarFunction.isFunctionSupported(IWpc.WPC_FUNC_CHARGE_STATES);
            String str = TAG;
            Log.i(str, "FunctionStatus is available: " + isFunctionSupported);
            int i = AnonymousClass3.$SwitchMap$com$ecarx$xui$adaptapi$FunctionStatus[isFunctionSupported.ordinal()];
            if (i == 1 || i == 2) {
                return false;
            }
            return i == 3 || i == 4;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        ICarFunction iCarFunction = this.mICarFunction;
        if (iCarFunction != null) {
            try {
                int functionValue = iCarFunction.getFunctionValue(IWpc.WPC_FUNC_WORK_MODE);
                int functionValue2 = this.mICarFunction.getFunctionValue(IWpc.WPC_FUNC_CHARGE_STATES);
                String str = TAG;
                Log.i(str, "workMode = " + functionValue + "; chargingStatus = " + functionValue2);
                if (functionValue == 637600001) {
                    setWirelessPowerIcon(true, functionValue2);
                    return;
                }
            } catch (Throwable th) {
                Lg.e(TAG, "wpc api error", th);
            }
        } else {
            Lg.e(TAG, "wpc api null");
        }
        setWirelessPowerIcon(false, 0);
    }

    private void setWirelessPowerIcon(boolean z, int i) {
        String str = TAG;
        Log.i(str, "[JRSYS_WPC][setWirelessPowerIcon], open: " + z + ", state: " + i);
        int i2 = 0;
        if (z && i != 0) {
            switch (i) {
                case IWpc.CHARGE_STATE_CHARGING /* 637665539 */:
                    i2 = R.drawable.ic_status_charge_ing;
                    break;
                case IWpc.CHARGE_STATE_FULLY_CHARGED /* 637665540 */:
                    i2 = R.drawable.ic_status_charge_complete;
                    break;
                case IWpc.CHARGE_STATE_OVERHEAT /* 637665542 */:
                case IWpc.CHARGE_STATE_OVERPOWER /* 637665543 */:
                case IWpc.CHARGE_STATE_FOD /* 637665544 */:
                case IWpc.CHARGE_STATE_OVERVOLTAGE /* 637665552 */:
                    break;
                default:
                    i2 = R.drawable.ic_status_charge_unable;
                    break;
            }
            StatusBarManager.getInstance().setIcon(new StatusBarIconWithTypeEntity(EStatefulObject.WPC, i2, i));
            return;
        }
        StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.WPC, false);
    }

    @Override // com.ecarx.systemui.plugin.policy.BaseController
    public void destroy() {
        super.destroy();
    }
}
