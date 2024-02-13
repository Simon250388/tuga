package com.ecarx.systemui.plugin.statusbar.policy;

import android.content.Context;
import android.util.Log;
import com.ecarx.systemui.plugin.R;
import com.ecarx.systemui.plugin.policy.BaseController;
import com.ecarx.systemui.plugin.statusbar.StatusBarManager;
import com.ecarx.systemui.plugin.statusbar.entity.StatusBarIconEntity;
import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;
import com.ecarx.systemui.plugin.statusbar.policy.DVRController;
import com.ecarx.systemui.plugin.utils.DeviceUtils;
import com.ecarx.systemui.plugin.utils.Lg;
import com.ecarx.systemui.plugin.utils.ThaderUtils;
import com.ecarx.systemui.plugin.utils.ThreadUtils;
import com.ecarx.xui.adaptapi.dvr.forp.Dvr;
import com.ecarx.xui.adaptapi.dvr.forp.IDvrManager;

/* loaded from: classes.dex */
public class DVRController extends BaseController {
    static final String TAG = DVRController.class.getSimpleName();
    private static DVRController dvrController;
    private boolean isEmergencyRecordinged;
    private boolean isRecordinged;
    private boolean mInitSucceed;

    private void initState() {
    }

    private DVRController(Context context) {
        super(context);
        init();
    }

    public static DVRController getInstance(Context context) {
        if (dvrController == null) {
            synchronized (DVRController.class) {
                if (dvrController == null) {
                    dvrController = new DVRController(context);
                }
            }
        }
        dvrController.initState();
        return dvrController;
    }

    private void init() {
        DeviceUtils.init(this.mContext).setOnConnectedCallBack(new DeviceUtils.ConnectedCallBack() { // from class: com.ecarx.systemui.plugin.statusbar.policy.-$$Lambda$DVRController$_ZAJZNEk-Wajd3RSeqrkxoyCCrg
            @Override // com.ecarx.systemui.plugin.utils.DeviceUtils.ConnectedCallBack
            public final void onConnected(boolean z) {
                DVRController.this.lambda$init$0$DVRController(z);
            }
        });
    }

    public /* synthetic */ void lambda$init$0$DVRController(boolean z) {
        initDvr();
    }

    private void initDvr() {
        try {
            boolean isDVRCameraConfigured = DeviceUtils.getInstance().isDVRCameraConfigured();
            String str = TAG;
            Log.i(str, "[JRSYS_DVR][support], support: " + isDVRCameraConfigured);
            this.mInitSucceed = true;
            Dvr create = Dvr.create(this.mContext);
            if (create == null) {
                return;
            }
            final IDvrManager dvrManager = create.getDvrManager();
            dvrManager.registerOperationObserver(new AnonymousClass1());
            ThreadUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.-$$Lambda$DVRController$HxNr1d3dB7jE7QEYu7QwaAM4m1k
                @Override // java.lang.Runnable
                public final void run() {
                    DVRController.this.lambda$initDvr$1$DVRController(dvrManager);
                }
            });
        } catch (Throwable th) {
            Lg.e(TAG, th);
            this.mInitSucceed = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ecarx.systemui.plugin.statusbar.policy.DVRController$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements IDvrManager.IDvrObserver {
        @Override // com.ecarx.xui.adaptapi.dvr.forp.IDvrManager.IDvrObserver
        public void onOperationError(int i, int i2) {
        }

        @Override // com.ecarx.xui.adaptapi.dvr.forp.IDvrManager.IDvrObserver
        public void onOperationStatus(int i, int i2) {
        }

        @Override // com.ecarx.xui.adaptapi.dvr.forp.IDvrManager.IDvrObserver
        public void onSDCardStateChanged(int i) {
        }

        AnonymousClass1() {
        }

        public /* synthetic */ void lambda$onDvrStateChanged$0$DVRController$1(int i) {
            DVRController.this.update(i);
        }

        @Override // com.ecarx.xui.adaptapi.dvr.forp.IDvrManager.IDvrObserver
        public void onDvrStateChanged(final int i) {
            ThaderUtils.post(new Runnable() { // from class: com.ecarx.systemui.plugin.statusbar.policy.-$$Lambda$DVRController$1$x2Y8rZ2zqcPCuCuOCY8SfzNxWX0
                @Override // java.lang.Runnable
                public final void run() {
                    DVRController.AnonymousClass1.this.lambda$onDvrStateChanged$0$DVRController$1(i);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initDvr$1$DVRController(IDvrManager iDvrManager) {
        update(iDvrManager.getCurrentDvrStates());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update(int i) {
        String str = TAG;
        Lg.w(str, "[JRSYS_DVR][update] mode:" + i);
        if (this.mInitSucceed) {
            if (i == 2) {
                this.isRecordinged = true;
                StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.DVR, R.drawable.ic_status_dvr_recording));
            } else if (i == 3) {
                this.isRecordinged = false;
                update();
            } else if (i == 4 || i == 5) {
                this.isEmergencyRecordinged = true;
                StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.DVR, R.drawable.ic_status_dvr_emergency));
            } else {
                if (i != 25) {
                    switch (i) {
                        case 8:
                            break;
                        case 9:
                        case 10:
                            this.isEmergencyRecordinged = false;
                            update();
                            return;
                        default:
                            switch (i) {
                                case 18:
                                case 19:
                                case 20:
                                case 21:
                                case 22:
                                    break;
                                default:
                                    return;
                            }
                    }
                }
                StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.DVR, R.drawable.ic_status_dvr_error));
            }
        }
    }

    private void update() {
        String str = TAG;
        Lg.w(str, "[JRSYS_DVR][update] isRecordinged: " + this.isRecordinged + "   isEmergencyRecordinged: " + this.isEmergencyRecordinged);
        if (this.isRecordinged || this.isEmergencyRecordinged) {
            if (this.isEmergencyRecordinged) {
                StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.DVR, R.drawable.ic_status_dvr_emergency));
                return;
            } else {
                StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.DVR, R.drawable.ic_status_dvr_recording));
                return;
            }
        }
        StatusBarManager.getInstance().setIcon(new StatusBarIconEntity(EStatefulObject.DVR, R.drawable.ic_status_dvr_suspended));
    }
}
