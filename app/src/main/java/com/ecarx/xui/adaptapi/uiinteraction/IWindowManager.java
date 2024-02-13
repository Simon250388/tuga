package com.ecarx.xui.adaptapi.uiinteraction;

/* loaded from: classes.dex */
public interface IWindowManager {

    /* loaded from: classes.dex */
    public interface IWindow {
        int getDisplayId();

        String getPackage();

        int getType();

        int getUID();

        int getViewVisibility();
    }

    /* loaded from: classes.dex */
    public interface IWindowObserver {
        void onWindowAdded(IWindow iWindow);

        void onWindowRemoved(IWindow iWindow);
    }

    IWindow[] getWindowList();

    boolean registerWindowObserver(IWindowObserver iWindowObserver);

    boolean unregisterWindowObserver(IWindowObserver iWindowObserver);
}
