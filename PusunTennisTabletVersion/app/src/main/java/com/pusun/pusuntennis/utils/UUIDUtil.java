package com.pusun.pusuntennis.utils;

/* loaded from: classes3.dex */
public class UUIDUtil {
    private static final String base_uuid_regex = "0000([0-9a-f][0-9a-f][0-9a-f][0-9a-f])-0000-1000-8000-00805f9b34fb";

    public static boolean isBaseUUID(String str) {
        return str.toLowerCase().matches(base_uuid_regex);
    }

    public static String UUID_128_to_16bit(String str, boolean z) {
        if (str.length() != 36) {
            return null;
        }
        if (z) {
            return str.substring(4, 8).toLowerCase();
        }
        return str.substring(4, 8).toUpperCase();
    }

    public static String UUID_16bit_128bit(String str, boolean z) {
        if (z) {
            return ("0000" + str + "-0000-1000-8000-00805f9b34fb").toLowerCase();
        }
        return ("0000" + str + "-0000-1000-8000-00805f9b34fb").toUpperCase();
    }
}
