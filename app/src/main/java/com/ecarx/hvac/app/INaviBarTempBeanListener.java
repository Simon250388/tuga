package com.ecarx.hvac.app;


import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: /tmp/jadx-3078856291651330720.dex */
public interface INaviBarTempBeanListener extends IInterface {
    void initTempData(NaviBarTempBean naviBarTempBean) throws RemoteException;

    void onNaviBarTempBeanChange(NaviBarTempBean naviBarTempBean) throws RemoteException;

    /* loaded from: /tmp/jadx-3078856291651330720.dex */
    public static abstract class Stub extends Binder implements INaviBarTempBeanListener {
        private static final String DESCRIPTOR = "ecarx.hvac.app.INaviBarTempBeanListener";
        static final int TRANSACTION_initTempData = 1;
        static final int TRANSACTION_onNaviBarTempBeanChange = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INaviBarTempBeanListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof INaviBarTempBeanListener)) {
                return (INaviBarTempBeanListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == TRANSACTION_initTempData) {
                parcel.enforceInterface(DESCRIPTOR);
                initTempData(parcel.readInt() != 0 ? NaviBarTempBean.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            } else if (i != TRANSACTION_onNaviBarTempBeanChange) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                onNaviBarTempBeanChange(parcel.readInt() != 0 ? NaviBarTempBean.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
        }

        /* loaded from: /tmp/jadx-3078856291651330720.dex */
        private static class Proxy implements INaviBarTempBeanListener {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // ecarx.hvac.app.INaviBarTempBeanListener
            public void initTempData(NaviBarTempBean naviBarTempBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (naviBarTempBean != null) {
                        obtain.writeInt(Stub.TRANSACTION_initTempData);
                        naviBarTempBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_initTempData, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.INaviBarTempBeanListener
            public void onNaviBarTempBeanChange(NaviBarTempBean naviBarTempBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (naviBarTempBean != null) {
                        obtain.writeInt(Stub.TRANSACTION_initTempData);
                        naviBarTempBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onNaviBarTempBeanChange, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
