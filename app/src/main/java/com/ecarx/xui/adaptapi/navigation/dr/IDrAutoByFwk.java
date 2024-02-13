package com.ecarx.xui.adaptapi.navigation.dr;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IDrAutoByFwk {
    public static final int EDR_STATUS_EDR = 1;
    public static final int EDR_STATUS_EDRGNSS = 2;
    public static final int EDR_STATUS_EGNSS = 0;
    public static final int EMOVE_STATUS_BACK = 2;
    public static final int EMOVE_STATUS_INVALID = 0;
    public static final int EMOVE_STATUS_NORMAL = 1;
    public static final int EMOVE_STATUS_STILL = 6;
    public static final int EMOVE_STATUS_TURN_LEFT = 3;
    public static final int EMOVE_STATUS_TURN_RIGHR = 4;
    public static final int EMOVE_STATUS_UTURN = 5;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EDrStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EMoveStatus {
    }

    /* loaded from: classes.dex */
    public interface IDrPos {
        float getCourse();

        float getCourseAcc();

        int getDRStatus();

        float getDeltaAlt();

        float getDeltaAltAcc();

        String getEW();

        String getGPSStatus();

        float getHdop();

        double getLat();

        double getLon();

        double getMoveDist();

        int getMoveStatus();

        String getNS();

        float getPosAcc();

        int getSatNum();

        float getSlopeAcc();

        float getSlopeValue();

        float getSpeed();

        float getSpeedAcc();

        long getTicktime();

        long getTime();

        boolean isDeltaAltAccValid();

        boolean isDeltaAltValid();

        boolean isMoveDistValid();

        boolean isSlopeAccValid();

        boolean isSlopeValueValid();
    }

    /* loaded from: classes.dex */
    public interface IDrPosListener {
        void onDrPosChanged(IDrPos iDrPos);
    }

    void registerListener(IDrPosListener iDrPosListener);

    void unregisterListener(IDrPosListener iDrPosListener);

    void updateFeedback(IDrPos iDrPos, IDrFeedback iDrFeedback);
}
