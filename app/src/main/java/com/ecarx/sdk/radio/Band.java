package com.ecarx.sdk.radio;

/* loaded from: classes.dex */
public enum Band {
    FM(0),
    AM(1);
    
    private int id;

    Band(int i) {
        this.id = i;
    }

    public static Band bandFromId(int i) {
        Band[] values;
        for (Band band : values()) {
            if (band.getId() == i) {
                return band;
            }
        }
        return FM;
    }

    public int getId() {
        return this.id;
    }
}
