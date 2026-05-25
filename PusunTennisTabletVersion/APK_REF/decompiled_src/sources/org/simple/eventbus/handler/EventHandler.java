package org.simple.eventbus.handler;

import org.simple.eventbus.Subscription;

/* loaded from: classes3.dex */
public interface EventHandler {
    void handleEvent(Subscription subscription, Object obj);
}
