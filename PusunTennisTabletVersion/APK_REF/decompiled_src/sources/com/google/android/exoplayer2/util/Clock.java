package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;

@Deprecated
/* loaded from: classes.dex */
public interface Clock {
    public static final Clock DEFAULT = new SystemClock();

    HandlerWrapper createHandler(Looper looper, Handler.Callback callback);

    long currentTimeMillis();

    long elapsedRealtime();

    long nanoTime();

    void onThreadBlocked();

    long uptimeMillis();
}
