package com.ecarx.xui.adaptapi.hudinteraction;

/* loaded from: classes.dex */
public interface IADASLaneSyncInfo {
    double[] getLeftLaneLeftDetectStartEnd();

    double[] getLeftLaneRightDetectStartEnd();

    double[] getLeftLowerLaneCoordinate();

    double[] getLeftUpperLaneCoordinate();

    double[] getRightLaneLeftDetectStartEnd();

    double[] getRightLaneRightDetectStartEnd();

    double[] getRightLowerLaneCoordinate();

    double[] getRightUpperLaneCoordinate();

    int getVehiclePressureLineStatus();
}
