package com.ecarx.xui.adaptapi.hudinteraction;

/* loaded from: classes.dex */
public interface IADASDrivingAidSyncInfo {
    int getACCStatus();

    int getAebStatus();

    int getFrontObjectDirection();

    int getFrontObjectType();

    int getFrontType();

    double getLateralXDistance();

    double getLateralZDistance();

    int getWarningStatus();
}
