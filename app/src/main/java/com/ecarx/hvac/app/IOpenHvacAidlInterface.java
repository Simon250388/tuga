package com.ecarx.hvac.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: /tmp/jadx-3078856291651330720.dex */
public interface IOpenHvacAidlInterface extends IInterface {
    void closeHvacLeftTemp() throws RemoteException;

    void getBarBean() throws RemoteException;

    void handleValue(int i, int i2) throws RemoteException;

    void handleView(boolean z) throws RemoteException;

    boolean isHvacShow() throws RemoteException;

    void openHvac() throws RemoteException;

    void openHvacLeftTemp() throws RemoteException;

    void openHvacMain() throws RemoteException;

    void openHvacRightTemp() throws RemoteException;

    void openHvacRightTempToast() throws RemoteException;

    void openHvacSeat() throws RemoteException;

    void openPm25() throws RemoteException;

    void registerListener(INaviBarTempBeanListener iNaviBarTempBeanListener) throws RemoteException;

    void unregisterListener(INaviBarTempBeanListener iNaviBarTempBeanListener) throws RemoteException;

    /* loaded from: /tmp/jadx-3078856291651330720.dex */
    public static abstract class Stub extends Binder implements IOpenHvacAidlInterface {
        private static final String DESCRIPTOR = "ecarx.hvac.app.IOpenHvacAidlInterface";
        static final int TRANSACTION_closeHvacLeftTemp = 4;
        static final int TRANSACTION_getBarBean = 12;
        static final int TRANSACTION_handleValue = 11;
        static final int TRANSACTION_handleView = 10;
        static final int TRANSACTION_isHvacShow = 9;
        static final int TRANSACTION_openHvac = 7;
        static final int TRANSACTION_openHvacLeftTemp = 3;
        static final int TRANSACTION_openHvacMain = 1;
        static final int TRANSACTION_openHvacRightTemp = 5;
        static final int TRANSACTION_openHvacRightTempToast = 6;
        static final int TRANSACTION_openHvacSeat = 2;
        static final int TRANSACTION_openPm25 = 8;
        static final int TRANSACTION_registerListener = 13;
        static final int TRANSACTION_unregisterListener = 14;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOpenHvacAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOpenHvacAidlInterface)) {
                return (IOpenHvacAidlInterface) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case TRANSACTION_openHvacMain /* 1 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    openHvacMain();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_openHvacSeat /* 2 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    openHvacSeat();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_openHvacLeftTemp /* 3 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    openHvacLeftTemp();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_closeHvacLeftTemp /* 4 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeHvacLeftTemp();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_openHvacRightTemp /* 5 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    openHvacRightTemp();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_openHvacRightTempToast /* 6 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    openHvacRightTempToast();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_openHvac /* 7 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    openHvac();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_openPm25 /* 8 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    openPm25();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_isHvacShow /* 9 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isHvacShow = isHvacShow();
                    parcel2.writeNoException();
                    parcel2.writeInt(isHvacShow ? 1 : 0);
                    return true;
                case TRANSACTION_handleView /* 10 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    handleView(parcel.readInt() != 0 ? true : false);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_handleValue /* 11 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    handleValue(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getBarBean /* 12 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    getBarBean();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_registerListener /* 13 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(INaviBarTempBeanListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_unregisterListener /* 14 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(INaviBarTempBeanListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: /tmp/jadx-3078856291651330720.dex */
        private static class Proxy implements IOpenHvacAidlInterface {
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

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void openHvacMain() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_openHvacMain, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void openHvacSeat() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_openHvacSeat, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void openHvacLeftTemp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_openHvacLeftTemp, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void closeHvacLeftTemp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_closeHvacLeftTemp, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void openHvacRightTemp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_openHvacRightTemp, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void openHvacRightTempToast() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_openHvacRightTempToast, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void openHvac() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_openHvac, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void openPm25() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_openPm25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public boolean isHvacShow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_isHvacShow, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void handleView(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? Stub.TRANSACTION_openHvacMain : 0);
                    this.mRemote.transact(Stub.TRANSACTION_handleView, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void handleValue(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_handleValue, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void getBarBean() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getBarBean, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void registerListener(INaviBarTempBeanListener iNaviBarTempBeanListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iNaviBarTempBeanListener != null ? iNaviBarTempBeanListener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_registerListener, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // ecarx.hvac.app.IOpenHvacAidlInterface
            public void unregisterListener(INaviBarTempBeanListener iNaviBarTempBeanListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iNaviBarTempBeanListener != null ? iNaviBarTempBeanListener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_unregisterListener, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
