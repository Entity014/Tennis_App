package org.simple.eventbus.config;

import org.simple.eventbus.handler.AsyncEventHandler;
import org.simple.eventbus.handler.DefaultEventHandler;
import org.simple.eventbus.handler.EventHandler;
import org.simple.eventbus.handler.UIThreadEventHandler;
import org.simple.eventbus.matchpolicy.DefaultMatchPolicy;
import org.simple.eventbus.matchpolicy.MatchPolicy;

/* loaded from: classes3.dex */
public final class EventBusConfig {
    EventHandler mUIThreadEventHandler = new UIThreadEventHandler();
    EventHandler mPostThreadHandler = new DefaultEventHandler();
    EventHandler mAsyncEventHandler = new AsyncEventHandler();
    MatchPolicy mMatchPolicy = new DefaultMatchPolicy();

    public EventBusConfig setMatchPolicy(MatchPolicy matchPolicy) {
        this.mMatchPolicy = matchPolicy;
        return this;
    }

    public EventBusConfig setUIThreadEventHandler(EventHandler eventHandler) {
        this.mUIThreadEventHandler = eventHandler;
        return this;
    }

    public EventBusConfig setPostThreadHandler(EventHandler eventHandler) {
        this.mPostThreadHandler = eventHandler;
        return this;
    }

    public EventBusConfig setAsyncEventHandler(EventHandler eventHandler) {
        this.mAsyncEventHandler = eventHandler;
        return this;
    }

    public EventHandler getUIThreadEventHandler() {
        return this.mUIThreadEventHandler;
    }

    public EventHandler getPostThreadHandler() {
        return this.mPostThreadHandler;
    }

    public EventHandler getAsyncEventHandler() {
        return this.mAsyncEventHandler;
    }

    public MatchPolicy getMatchPolicy() {
        return this.mMatchPolicy;
    }
}
