package com.ecarx.xui.adaptapi.input;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.FunctionStatus;

/* loaded from: classes.dex */
public abstract class Input extends AdaptAPI {
    public static Input create(Context context) {
        return null;
    }

    public abstract boolean abandonKeysInterception(IKeyCallback iKeyCallback);

    public abstract IInputSettings getInputSettings();

    public abstract FunctionStatus isInputSettingsSupported();

    public abstract int[] requestKeysInterception(int[] iArr, IKeyCallback iKeyCallback);
}
