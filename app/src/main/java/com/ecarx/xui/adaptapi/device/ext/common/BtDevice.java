package com.ecarx.xui.adaptapi.device.ext.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class BtDevice implements Parcelable {
    public static final Parcelable.Creator<BtDevice> CREATOR = new Parcelable.Creator<BtDevice>() { // from class: com.ecarx.xui.adaptapi.device.ext.common.BtDevice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BtDevice[] newArray(int i) {
            return new BtDevice[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BtDevice createFromParcel(Parcel parcel) {
            BtDevice btDevice = new BtDevice();
            btDevice.address = parcel.readString();
            btDevice.name = parcel.readString();
            btDevice.supportProfile = parcel.readInt();
            btDevice.category = parcel.readInt();
            btDevice.bondState = parcel.readInt();
            btDevice.connectState = parcel.readInt();
            return btDevice;
        }
    };
    private static final String TAG = "BtDevice";
    private String address;
    private String name;
    private int supportProfile = 0;
    private int category = 0;
    private int bondState = 0;
    private int connectState = 0;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BtDevice() {
    }

    public BtDevice(String str) {
        this.address = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getSupportProfile() {
        return this.supportProfile;
    }

    public void setSupportProfile(int i) {
        this.supportProfile = i;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int i) {
        this.category = i;
    }

    public int getBondState() {
        return this.bondState;
    }

    public void setBondState(int i) {
        this.bondState = i;
    }

    public int getConnectState() {
        return this.connectState;
    }

    public void setConnectState(int i) {
        this.connectState = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.address);
        parcel.writeString(this.name);
        parcel.writeInt(this.supportProfile);
        parcel.writeInt(this.category);
        parcel.writeInt(this.bondState);
        parcel.writeInt(this.connectState);
    }
}
