package com.ecarx.hvac.app;


import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: /tmp/jadx-3078856291651330720.dex */
public class NaviBarTempBean implements Parcelable {
    public static final Parcelable.Creator<NaviBarTempBean> CREATOR = new Parcelable.Creator<NaviBarTempBean>() { // from class: ecarx.hvac.app.NaviBarTempBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviBarTempBean createFromParcel(Parcel parcel) {
            return new NaviBarTempBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviBarTempBean[] newArray(int i) {
            return new NaviBarTempBean[i];
        }
    };
    private String fanStatus;
    private int fucntionId;
    private String leftTempStatus;
    private float leftTemperature;
    private int level;
    private int maxLevel;
    private float maxTemp;
    private float minTemp;
    private String rightTempStatus;
    private float rightTemperature;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviBarTempBean() {
        this.leftTemperature = -1.0f;
        this.rightTemperature = -1.0f;
        this.minTemp = -1.0f;
        this.maxTemp = -1.0f;
        this.level = -1;
        this.maxLevel = -1;
    }

    public NaviBarTempBean(String str, String str2, String str3, float f, float f2, float f3, float f4, int i, int i2, int i3) {
        this.leftTemperature = -1.0f;
        this.rightTemperature = -1.0f;
        this.minTemp = -1.0f;
        this.maxTemp = -1.0f;
        this.level = -1;
        this.maxLevel = -1;
        this.leftTempStatus = str;
        this.rightTempStatus = str2;
        this.fanStatus = str3;
        this.leftTemperature = f;
        this.rightTemperature = f2;
        this.minTemp = f3;
        this.maxTemp = f4;
        this.level = i;
        this.maxLevel = i2;
        this.fucntionId = i3;
    }

    protected NaviBarTempBean(Parcel parcel) {
        this.leftTemperature = -1.0f;
        this.rightTemperature = -1.0f;
        this.minTemp = -1.0f;
        this.maxTemp = -1.0f;
        this.level = -1;
        this.maxLevel = -1;
        this.leftTempStatus = parcel.readString();
        this.rightTempStatus = parcel.readString();
        this.fanStatus = parcel.readString();
        this.leftTemperature = parcel.readFloat();
        this.rightTemperature = parcel.readFloat();
        this.minTemp = parcel.readFloat();
        this.maxTemp = parcel.readFloat();
        this.level = parcel.readInt();
        this.maxLevel = parcel.readInt();
        this.fucntionId = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.leftTempStatus);
        parcel.writeString(this.rightTempStatus);
        parcel.writeString(this.fanStatus);
        parcel.writeFloat(this.leftTemperature);
        parcel.writeFloat(this.rightTemperature);
        parcel.writeFloat(this.minTemp);
        parcel.writeFloat(this.maxTemp);
        parcel.writeInt(this.level);
        parcel.writeInt(this.maxLevel);
        parcel.writeInt(this.fucntionId);
    }

    public String getLeftTempStatus() {
        return this.leftTempStatus;
    }

    public void setLeftTempStatus(String str) {
        this.leftTempStatus = str;
    }

    public String getRightTempStatus() {
        return this.rightTempStatus;
    }

    public void setRightTempStatus(String str) {
        this.rightTempStatus = str;
    }

    public float getMinTemp() {
        return this.minTemp;
    }

    public void setMinTemp(float f) {
        this.minTemp = f;
    }

    public float getMaxTemp() {
        return this.maxTemp;
    }

    public void setMaxTemp(float f) {
        this.maxTemp = f;
    }

    public String getFanStatus() {
        return this.fanStatus;
    }

    public void setFanStatus(String str) {
        this.fanStatus = str;
    }

    public float getLeftTemperature() {
        return this.leftTemperature;
    }

    public void setLeftTemperature(float f) {
        this.leftTemperature = f;
    }

    public float getRightTemperature() {
        return this.rightTemperature;
    }

    public void setRightTemperature(float f) {
        this.rightTemperature = f;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public void setMaxLevel(int i) {
        this.maxLevel = i;
    }

    public int getFucntionId() {
        return this.fucntionId;
    }

    public void setFucntionId(int i) {
        this.fucntionId = i;
    }

    public String toString() {
        return "NaviBarTempBean{leftTempStatus='" + this.leftTempStatus + "', rightTempStatus='" + this.rightTempStatus + "', fanStatus='" + this.fanStatus + "', leftTemperature=" + this.leftTemperature + ", rightTemperature=" + this.rightTemperature + ", level=" + this.level + ", maxLevel=" + this.maxLevel + ", fucntionId=" + this.fucntionId + '}';
    }
}
