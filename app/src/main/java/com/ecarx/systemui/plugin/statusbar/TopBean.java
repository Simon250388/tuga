package com.ecarx.systemui.plugin.statusbar;

import android.content.ComponentName;

/* loaded from: classes.dex */
public class TopBean {
    public ComponentName componentName;
    public boolean isCar;
    public boolean isHvac;
    public boolean isLauncher;
    public boolean isManualMode;
    public boolean isPane;
    public boolean isSys;
    public boolean isWeChat;

    public TopBean(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, ComponentName componentName) {
        this.isLauncher = z;
        this.isPane = z2;
        this.isCar = z3;
        this.isSys = z4;
        this.isManualMode = z5;
        this.isWeChat = z6;
        this.isHvac = z7;
        this.componentName = componentName;
    }

    public String toString() {
        return "TopBean{isLauncher=" + this.isLauncher + ", isPane=" + this.isPane + ", isCar=" + this.isCar + ", isSys=" + this.isSys + ", isManualMode=" + this.isManualMode + ", isWeChat=" + this.isWeChat + ", isHvac=" + this.isHvac + ", componentName=" + this.componentName + '}';
    }
}
