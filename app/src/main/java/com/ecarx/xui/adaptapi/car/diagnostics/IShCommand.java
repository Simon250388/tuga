package com.ecarx.xui.adaptapi.car.diagnostics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface IShCommand {
    public static final int COMMAND_CAT = 2;
    public static final int COMMAND_TOP = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CommandType {
    }

    /* loaded from: classes.dex */
    public interface ICommandCallback {
        void onCommandEnd();

        void onCommandOutput(String str);
    }

    void executeCommand(int i, String str, ICommandCallback iCommandCallback);

    void stopCommand();
}
