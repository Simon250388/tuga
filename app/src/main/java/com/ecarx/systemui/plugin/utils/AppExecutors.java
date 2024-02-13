package com.ecarx.systemui.plugin.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class AppExecutors {
    private final Executor mDiskIO;
    private final Executor mMainThread;
    private final Executor mNetworkIO;

    private void testCode() {
    }

    /* loaded from: classes.dex */
    private static class Holder {
        private static AppExecutors INSTANCE = new AppExecutors();

        private Holder() {
        }
    }

    public static AppExecutors getInstance() {
        return Holder.INSTANCE;
    }

    private AppExecutors(Executor executor, Executor executor2, Executor executor3) {
        this.mDiskIO = executor;
        this.mNetworkIO = executor2;
        this.mMainThread = executor3;
    }

    private AppExecutors() {
        this(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()), new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()), new MainThreadExecutor());
    }

    public Executor diskIO() {
        return this.mDiskIO;
    }

    public Executor networkIO() {
        return this.mNetworkIO;
    }

    public Executor mainThread() {
        return this.mMainThread;
    }

    /* loaded from: classes.dex */
    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler;

        private MainThreadExecutor() {
            this.mainThreadHandler = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.mainThreadHandler.post(runnable);
        }
    }
}
