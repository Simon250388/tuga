package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.Context;
import android.util.Log;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.utils.ThaderUtils;
import com.ecarx.xui.adaptapi.binder.IConnectable;
import com.ecarx.xui.adaptapi.peripherals.Peripherals;
import com.ecarx.xui.adaptapi.peripherals.wear.IIntelligentKey;

/* loaded from: classes.dex */
public class IntelligentKeyController extends BaseController {
    private static final String TAG = IntelligentKeyController.class.getSimpleName();
    private static IntelligentKeyController iKeyController;
    private boolean mConnected;
    private boolean mInitSucceed;
    private Peripherals mPeripherals;
    private int mStateConnected;

    private void initState() {
    }

    private IntelligentKeyController(Context context) {
        super(context);
        this.mStateConnected = -1;
        init();
    }

    public static IntelligentKeyController getInstance(Context context) {
        if (iKeyController == null) {
            synchronized (IntelligentKeyController.class) {
                if (iKeyController == null) {
                    iKeyController = new IntelligentKeyController(context);
                }
            }
        }
        iKeyController.initState();
        return iKeyController;
    }

    private void init() {
        try {
            this.mStateConnected = 2;
            Peripherals create = Peripherals.create(this.mContext);
            this.mPeripherals = create;
            if (create == null) {
                Log.w(TAG, "[JRSYS_INTEL] int Peripherals is null");
            } else if (create instanceof IConnectable) {
                IConnectable iConnectable = (IConnectable) create;
                iConnectable.registerConnectWatcher(new AnonymousClass1());
                iConnectable.connect();
            }
        } catch (Throwable th) {
            String str = TAG;
            Log.e(str, "[JRSYS_INTEL] init error: " + Log.getStackTraceString(th));
            this.mInitSucceed = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ecarx.systemui.plugin.statusbar.policy.IntelligentKeyController$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements IConnectable.IConnectWatcher {
        @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
        public void onDisConnected() {
        }

        AnonymousClass1() {
        }

        @Override // com.ecarx.xui.adaptapi.binder.IConnectable.IConnectWatcher
        public void onConnected() {
            ThaderUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.IntelligentKeyController.1.1
                @Override // java.lang.Runnable
                public void run() {
                    IntelligentKeyController.this.mConnected = true;
                    if (!IntelligentKeyController.this.mPeripherals.isIntelligentKeySupport()) {
                        Log.w(IntelligentKeyController.TAG, "[JRSYS_INTEL] int ikey fail, not support");
                        return;
                    }
                    IIntelligentKey intelligentKey = IntelligentKeyController.this.mPeripherals.getIntelligentKey();
                    intelligentKey.registerIntelligentKeyConnectionStateCallback(new IIntelligentKey.IIntelligentKeyConnectionStateCallback() { // from class: com.ecarx.systemui.plugin.statusbar.policy.IntelligentKeyController.1.1.1
                        @Override // com.ecarx.xui.adaptapi.peripherals.wear.IIntelligentKey.IIntelligentKeyConnectionStateCallback
                        public void onStateChanged(int i, int i2) {
                            IntelligentKeyController.this.update(i2);
                        }
                    });
                    IntelligentKeyController.this.mInitSucceed = true;
                    IntelligentKeyController.this.update(intelligentKey.getConnectionState());
                    Log.i(IntelligentKeyController.TAG, "[JRSYS_INTEL] init ikey succeed");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update(int i) {
        if (!this.mInitSucceed) {
            Log.i(TAG, "[JRSYS_INTEL] update fail, mInitSucceed = false");
            return;
        }
        String str = TAG;
        Log.i(str, "[JRSYS_INTEL] update connectionState = " + i + ": mStateConnected = " + this.mStateConnected);
        this.mStateConnected = i;
        if (i == 2) {
            StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.INTELLIGENT_WEARING_EQUIPMENT, R.drawable.ic_status_ikey));
            return;
        }
        StatusBarManager.getInstance().updateIconVisibility(EStatefulObject.INTELLIGENT_WEARING_EQUIPMENT, false);
    }

    private void testCode() {
        this.mInitSucceed = true;
        update(2);
    }
}
