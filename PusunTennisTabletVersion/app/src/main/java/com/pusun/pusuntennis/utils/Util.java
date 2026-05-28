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

    public static int getDeviceVersion(com.clj.fastble.data.BleDevice bleDevice) {
        if (bleDevice == null) {
            return 0;
        }
        try {
            String name = bleDevice.getName();
            if (name != null) {
                String trimmed = name.trim();
                if (trimmed.length() >= 9) {
                    return Integer.parseInt(trimmed.substring(3, 9));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getDeviceName(com.clj.fastble.data.BleDevice bleDevice) {
        if (bleDevice == null) {
            return "";
        }
        try {
            String name = bleDevice.getName();
            if (name != null) {
                return name.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
