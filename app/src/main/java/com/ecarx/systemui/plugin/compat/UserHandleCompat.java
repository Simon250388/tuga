package com.ecarx.systemui.plugin.compat;

import android.content.Intent;
import android.os.Process;
import android.os.UserHandle;
import com.ecarx.systemui.plugin.utils.Utilities;

/* loaded from: classes.dex */
public class UserHandleCompat {
    private UserHandle mUser;

    private UserHandleCompat(UserHandle userHandle) {
        this.mUser = userHandle;
    }

    private UserHandleCompat() {
    }

    public static UserHandleCompat myUserHandle() {
        if (Utilities.ATLEAST_JB_MR1) {
            return new UserHandleCompat(Process.myUserHandle());
        }
        return new UserHandleCompat();
    }

    public static UserHandleCompat fromUser(UserHandle userHandle) {
        if (userHandle == null) {
            return null;
        }
        return new UserHandleCompat(userHandle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserHandle getUser() {
        return this.mUser;
    }

    public String toString() {
        return Utilities.ATLEAST_JB_MR1 ? this.mUser.toString() : "";
    }

    public boolean equals(Object obj) {
        if (obj instanceof UserHandleCompat) {
            if (Utilities.ATLEAST_JB_MR1) {
                return this.mUser.equals(((UserHandleCompat) obj).mUser);
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (Utilities.ATLEAST_JB_MR1) {
            return this.mUser.hashCode();
        }
        return 0;
    }

    public void addToIntent(Intent intent, String str) {
        UserHandle userHandle;
        if (!Utilities.ATLEAST_LOLLIPOP || (userHandle = this.mUser) == null) {
            return;
        }
        intent.putExtra(str, userHandle);
    }
}
