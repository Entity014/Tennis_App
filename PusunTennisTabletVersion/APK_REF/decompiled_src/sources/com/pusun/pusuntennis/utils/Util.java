package com.pusun.pusuntennis.utils;

/* loaded from: classes3.dex */
public class Util {
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime < 500;
        lastClickTime = currentTimeMillis;
        return z;
    }
}
