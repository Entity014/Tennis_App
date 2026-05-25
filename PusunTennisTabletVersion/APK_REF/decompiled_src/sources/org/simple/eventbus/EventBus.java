package org.simple.eventbus;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import org.simple.eventbus.config.EventBusConfig;
import org.simple.eventbus.handler.EventHandler;
import org.simple.eventbus.matchpolicy.MatchPolicy;

/* loaded from: classes3.dex */
public final class EventBus {
    private static final String DESCRIPTOR = "EventBus";
    private static EventBus sDefaultBus;
    private EventBusConfig mBusConfig;
    private String mDesc;
    EventDispatcher mDispatcher;
    ThreadLocal<Queue<EventType>> mLocalEvents;
    SubsciberMethodHunter mMethodHunter;
    private final Map<EventType, CopyOnWriteArrayList<Subscription>> mSubcriberMap;

    private EventBus() {
        this(DESCRIPTOR);
    }

    public EventBus(String str) {
        this.mDesc = DESCRIPTOR;
        HashMap hashMap = new HashMap();
        this.mSubcriberMap = hashMap;
        this.mLocalEvents = new ThreadLocal<Queue<EventType>>() { // from class: org.simple.eventbus.EventBus.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public Queue<EventType> initialValue() {
                return new ConcurrentLinkedQueue();
            }
        };
        this.mDispatcher = new EventDispatcher(this, null);
        this.mMethodHunter = new SubsciberMethodHunter(hashMap);
        EventBusConfig eventBusConfig = new EventBusConfig();
        this.mBusConfig = eventBusConfig;
        this.mDesc = str;
        initWithConfig(eventBusConfig);
    }

    public static EventBus getDefault() {
        if (sDefaultBus == null) {
            synchronized (EventBus.class) {
                if (sDefaultBus == null) {
                    sDefaultBus = new EventBus();
                }
            }
        }
        return sDefaultBus;
    }

    public void initWithConfig(EventBusConfig eventBusConfig) {
        this.mDispatcher.mMatchPolicy = eventBusConfig.getMatchPolicy();
        this.mDispatcher.mUIThreadEventHandler = eventBusConfig.getUIThreadEventHandler();
        this.mDispatcher.mPostThreadHandler = eventBusConfig.getPostThreadHandler();
        this.mDispatcher.mAsyncEventHandler = eventBusConfig.getAsyncEventHandler();
    }

    public void register(Object obj) {
        if (obj == null) {
            return;
        }
        synchronized (this) {
            this.mMethodHunter.findSubcribeMethods(obj);
        }
    }

    public void unregister(Object obj) {
        if (obj == null) {
            return;
        }
        synchronized (this) {
            this.mMethodHunter.removeMethodsFromMap(obj);
        }
        Log.d(getDescriptor(), "### subscriber map size = " + this.mSubcriberMap.size());
    }

    public void post(Object obj) {
        post(obj, EventType.DEFAULT_TAG);
    }

    public void post(Object obj, String str) {
        this.mLocalEvents.get().offer(new EventType(obj.getClass(), str));
        this.mDispatcher.dispatchEvents(obj);
    }

    public Map<EventType, CopyOnWriteArrayList<Subscription>> getSubscriberMap() {
        return this.mSubcriberMap;
    }

    public Queue<EventType> getEventQueue() {
        return this.mLocalEvents.get();
    }

    public synchronized void clear() {
        this.mLocalEvents.get().clear();
        this.mSubcriberMap.clear();
    }

    public String getDescriptor() {
        return this.mDesc;
    }

    private class EventDispatcher {
        EventHandler mAsyncEventHandler;
        private Map<Class<?>, List<EventType>> mCacheEventTypes;
        MatchPolicy mMatchPolicy;
        EventHandler mPostThreadHandler;
        EventHandler mUIThreadEventHandler;

        private EventDispatcher() {
            this.mCacheEventTypes = new ConcurrentHashMap();
        }

        /* synthetic */ EventDispatcher(EventBus eventBus, EventDispatcher eventDispatcher) {
            this();
        }

        void dispatchEvents(Object obj) {
            Queue<EventType> queue = EventBus.this.mLocalEvents.get();
            while (queue.size() > 0) {
                deliveryEvent(queue.poll(), obj);
            }
        }

        private void deliveryEvent(EventType eventType, Object obj) {
            List<EventType> findMatchEventTypes;
            Class<?> cls = obj.getClass();
            if (this.mCacheEventTypes.containsKey(cls)) {
                findMatchEventTypes = this.mCacheEventTypes.get(cls);
            } else {
                findMatchEventTypes = this.mMatchPolicy.findMatchEventTypes(eventType, obj);
                this.mCacheEventTypes.put(cls, findMatchEventTypes);
            }
            Iterator<EventType> it = findMatchEventTypes.iterator();
            while (it.hasNext()) {
                handleEvent(it.next(), obj);
            }
        }

        private void handleEvent(EventType eventType, Object obj) {
            List<Subscription> list = (List) EventBus.this.mSubcriberMap.get(eventType);
            if (list == null) {
                return;
            }
            for (Subscription subscription : list) {
                getEventHandler(subscription.threadMode).handleEvent(subscription, obj);
            }
        }

        private EventHandler getEventHandler(ThreadMode threadMode) {
            if (threadMode == ThreadMode.ASYNC) {
                return this.mAsyncEventHandler;
            }
            if (threadMode == ThreadMode.POST) {
                return this.mPostThreadHandler;
            }
            return this.mUIThreadEventHandler;
        }
    }
}
