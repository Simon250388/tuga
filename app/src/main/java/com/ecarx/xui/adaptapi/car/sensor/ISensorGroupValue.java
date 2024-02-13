package com.ecarx.xui.adaptapi.car.sensor;

/* loaded from: classes.dex */
public interface ISensorGroupValue {

    /* loaded from: classes.dex */
    public interface IAcc3dValue extends ISensorGroupValue {
        int getAxis();

        float getValuePitch();

        float getValueRoll();

        float getValueYaw();
    }

    /* loaded from: classes.dex */
    public interface IGyroValue extends ISensorGroupValue {
        int getAxis();

        float getTemperature();

        float getValuePitch();

        float getValueRoll();

        float getValueYaw();
    }

    /* loaded from: classes.dex */
    public interface ISpeedPulseValue extends ISensorGroupValue {
        float getSpeedValue();
    }

    /* loaded from: classes.dex */
    public interface IW4mValue extends ISensorGroupValue {
        int getGearState();

        float getLatAcc();

        float getLonAcc();

        float getSteerAngle();

        float getVFLSpeed();

        float getVFRSpeed();

        float getVRLSpeed();

        float getVRRSpeed();

        float getYawRate();
    }

    int getInterval();

    int getSensorGroupType();

    long getTickTime();
}
