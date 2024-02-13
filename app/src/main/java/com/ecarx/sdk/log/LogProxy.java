package com.ecarx.sdk.log;

import android.util.Log;

/* loaded from: classes.dex */
public final class LogProxy {
    private boolean enable;
    private int level;
    private ILog logImpl;

    public void setLogEnable(boolean z) {
        this.enable = z;
    }

    public void setLogLevel(int i) {
        this.level = i;
    }

    public void setLogImpl(ILog iLog) {
        this.logImpl = iLog;
    }

    public void v(String str, String str2) {
        if (!this.enable || this.level > 2) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.v(str, str2);
        } else {
            iLog.v(str, str2);
        }
    }

    public void v(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 2) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.v(str, str2, th);
            return;
        }
        iLog.v(str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public void d(String str, String str2) {
        if (!this.enable || this.level > 3) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.d(str, str2);
        } else {
            iLog.d(str, str2);
        }
    }

    public void d(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 3) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.d(str, str2, th);
            return;
        }
        iLog.d(str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public void i(String str, String str2) {
        if (!this.enable || this.level > 4) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.i(str, str2);
        } else {
            iLog.i(str, str2);
        }
    }

    public void i(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 4) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.i(str, str2, th);
            return;
        }
        iLog.i(str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public void w(String str, String str2) {
        if (!this.enable || this.level > 5) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.w(str, str2);
        } else {
            iLog.w(str, str2);
        }
    }

    public void w(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 5) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.w(str, str2, th);
            return;
        }
        iLog.w(str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public void e(String str, String str2) {
        if (!this.enable || this.level > 6) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.e(str, str2);
        } else {
            iLog.e(str, str2);
        }
    }

    public void e(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 6) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.e(str, str2, th);
            return;
        }
        iLog.e(str, str2 + '\n' + Log.getStackTraceString(th));
    }
}
