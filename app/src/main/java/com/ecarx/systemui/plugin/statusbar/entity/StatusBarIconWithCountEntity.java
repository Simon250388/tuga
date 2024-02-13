package com.ecarx.systemui.plugin.statusbar.entity;

import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;

/* loaded from: classes.dex */
public class StatusBarIconWithCountEntity extends StatusBarIconEntity {
    private int count;

    public StatusBarIconWithCountEntity(EStatefulObject eStatefulObject, int i) {
        super(eStatefulObject, i);
    }

    public StatusBarIconWithCountEntity(EStatefulObject eStatefulObject, int i, int i2) {
        super(eStatefulObject, i);
        this.count = i2;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }
}
