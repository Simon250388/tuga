package com.ecarx.xui.adaptapi.pki;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class PKI extends AdaptAPI {
    public static final int SSL_AUTH_BOTH = 2;
    public static final int SSL_AUTH_NONE = 0;
    public static final int SSL_AUTH_SINGLE = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface SSLAuthType {
    }

    public static PKI create(Context context) {
        return null;
    }

    public abstract String getCertificateAlias();

    public abstract int getSSLAuthType();
}
