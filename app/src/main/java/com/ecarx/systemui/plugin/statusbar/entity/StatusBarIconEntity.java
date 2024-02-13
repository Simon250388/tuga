package com.ecarx.systemui.plugin.statusbar.entity;

import com.ecarx.systemui.plugin.statusbar.iconview.EStatefulObject;

/* loaded from: classes.dex */
public class StatusBarIconEntity {
    private int iconId;
    private EStatefulObject statefulObject;

    public StatusBarIconEntity(EStatefulObject eStatefulObject, int i) {
        this.statefulObject = eStatefulObject;
        this.iconId = i;
    }

    public EStatefulObject getStatefulObject() {
        return this.statefulObject;
    }

    public void setStatefulObject(EStatefulObject eStatefulObject) {
        this.statefulObject = eStatefulObject;
    }

    public int getIconId() {
        return this.iconId;
    }

    public void setIconId(int i) {
        this.iconId = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof StatusBarIconEntity) {
            StatusBarIconEntity statusBarIconEntity = (StatusBarIconEntity) obj;
            return statusBarIconEntity.getIconId() == getIconId() && statusBarIconEntity.getStatefulObject().equals(getStatefulObject());
        }
        return false;
    }
}
