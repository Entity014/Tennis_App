package com.pusun.pusuntennis.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class CollectionUtil {
    public static DecimalFormat df = new DecimalFormat("###0.00");

    public static String divide100(Long l) {
        return new BigDecimal(l.longValue()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).toString();
    }

    public static float divide100byFloat(Long l) {
        return new BigDecimal(l.longValue()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).floatValue();
    }

    public static String getCollectionValue(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(collection.size() * 16);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next != collection) {
                sb.append(next);
            }
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String getCollectionValue2(HashMap hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            sb.append(((String) entry.getKey()) + "=" + ((String) entry.getValue()));
            if (it.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    public static <T> ArrayList<T> removeDuplicate(List<T> list) {
        return new ArrayList<>(new HashSet(list));
    }
}
