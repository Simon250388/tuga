package com.ecarx.systemui.plugin.statusbar.entity;

import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;

/* loaded from: classes.dex */
public class StatusBarIconWithTypeEntity extends StatusBarIconEntity {
    private int type;

    public StatusBarIconWithTypeEntity(EStatefulObject eStatefulObject, int i) {
        super(eStatefulObject, i);
    }

    public StatusBarIconWithTypeEntity(EStatefulObject eStatefulObject, int i, int i2) {
        super(eStatefulObject, i);
        this.type = i2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
