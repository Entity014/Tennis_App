package org.simple.eventbus.matchpolicy;

import java.util.List;
import org.simple.eventbus.EventType;

/* loaded from: classes3.dex */
public interface MatchPolicy {
    List<EventType> findMatchEventTypes(EventType eventType, Object obj);
}
