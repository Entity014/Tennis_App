package org.simple.eventbus;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public class SubsciberMethodHunter {
    Map<EventType, CopyOnWriteArrayList<Subscription>> mSubcriberMap;

    public SubsciberMethodHunter(Map<EventType, CopyOnWriteArrayList<Subscription>> map) {
        this.mSubcriberMap = map;
    }

    public List<Subscription> findSubcribeMethods(Object obj) {
        Class<?>[] parameterTypes;
        if (this.mSubcriberMap == null) {
            throw new NullPointerException("the mSubcriberMap is null. ");
        }
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        for (Class<?> cls = obj.getClass(); cls != null && !isSystemCalss(cls.getName()); cls = cls.getSuperclass()) {
            for (Method method : cls.getDeclaredMethods()) {
                Subcriber subcriber = (Subcriber) method.getAnnotation(Subcriber.class);
                if (subcriber != null && (parameterTypes = method.getParameterTypes()) != null && parameterTypes.length == 1) {
                    subscibe(new EventType(parameterTypes[0], subcriber.tag()), new TargetMethod(method, parameterTypes[0], subcriber.mode()), obj);
                }
            }
        }
        Log.e("", "### found all ");
        return copyOnWriteArrayList;
    }

    public void removeMethodsFromMap(Object obj) {
        Iterator<CopyOnWriteArrayList<Subscription>> it = this.mSubcriberMap.values().iterator();
        while (it.hasNext()) {
            CopyOnWriteArrayList<Subscription> next = it.next();
            if (next != null) {
                LinkedList linkedList = new LinkedList();
                Iterator<Subscription> it2 = next.iterator();
                while (it2.hasNext()) {
                    Subscription next2 = it2.next();
                    if (next2.subscriber.equals(obj)) {
                        Log.d("", "### 移除订阅 " + obj.getClass().getName());
                        linkedList.add(next2);
                    }
                }
                next.removeAll(linkedList);
            }
            if (next == null || next.size() == 0) {
                it.remove();
            }
        }
    }

    private void subscibe(EventType eventType, TargetMethod targetMethod, Object obj) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.mSubcriberMap.get(eventType);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        }
        Subscription subscription = new Subscription(obj, targetMethod);
        if (copyOnWriteArrayList.contains(subscription)) {
            return;
        }
        copyOnWriteArrayList.add(subscription);
        this.mSubcriberMap.put(eventType, copyOnWriteArrayList);
    }

    private boolean isSystemCalss(String str) {
        return str.startsWith("java.") || str.startsWith("javax.") || str.startsWith("android.");
    }
}
