package com.ecarx.systemui.plugin.statusbar.entity;

import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;

/* loaded from: classes.dex */
public class StatusBarIconWithSubIconEntity extends StatusBarIconEntity {
    private int subIconId;

    public StatusBarIconWithSubIconEntity(EStatefulObject eStatefulObject, int i) {
        super(eStatefulObject, i);
    }

    public StatusBarIconWithSubIconEntity(EStatefulObject eStatefulObject, int i, int i2) {
        super(eStatefulObject, i);
        this.subIconId = i2;
    }

    public int getSubIconId() {
        return this.subIconId;
    }

    public void setSubIconId(int i) {
        this.subIconId = i;
    }
}
