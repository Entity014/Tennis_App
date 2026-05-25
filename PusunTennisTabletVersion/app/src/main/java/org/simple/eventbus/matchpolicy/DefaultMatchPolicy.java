package org.simple.eventbus.matchpolicy;

import java.util.LinkedList;
import java.util.List;
import org.simple.eventbus.EventType;

/* loaded from: classes3.dex */
public class DefaultMatchPolicy implements MatchPolicy {
    @Override // org.simple.eventbus.matchpolicy.MatchPolicy
    public List<EventType> findMatchEventTypes(EventType eventType, Object obj) {
        LinkedList linkedList = new LinkedList();
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            linkedList.add(new EventType(cls, eventType.tag));
            addInterfaces(linkedList, cls.getInterfaces(), eventType.tag);
        }
        return linkedList;
    }

    private void addInterfaces(List<EventType> list, Class<?>[] clsArr, String str) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(new EventType(cls, str));
                addInterfaces(list, cls.getInterfaces(), str);
            }
        }
    }
}
