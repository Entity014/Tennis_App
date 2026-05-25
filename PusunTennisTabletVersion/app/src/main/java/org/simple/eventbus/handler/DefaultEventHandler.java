package org.simple.eventbus.handler;

import java.lang.reflect.InvocationTargetException;
import org.simple.eventbus.Subscription;

/* loaded from: classes3.dex */
public class DefaultEventHandler implements EventHandler {
    @Override // org.simple.eventbus.handler.EventHandler
    public void handleEvent(Subscription subscription, Object obj) {
        if (subscription == null || subscription.subscriber == null) {
            return;
        }
        try {
            subscription.targetMethod.invoke(subscription.subscriber, obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }
}
