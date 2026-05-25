package org.simple.eventbus;

/* loaded from: classes3.dex */
public final class EventType {
    public static final String DEFAULT_TAG = "default_tag";
    Class<?> paramClass;
    public String tag;

    public EventType(Class<?> cls) {
        this(cls, DEFAULT_TAG);
    }

    public EventType(Class<?> cls, String str) {
        this.paramClass = cls;
        this.tag = str;
    }

    public int hashCode() {
        Class<?> cls = this.paramClass;
        int hashCode = ((cls == null ? 0 : cls.hashCode()) + 31) * 31;
        String str = this.tag;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventType eventType = (EventType) obj;
        Class<?> cls = this.paramClass;
        if (cls == null) {
            if (eventType.paramClass != null) {
                return false;
            }
        } else if (!cls.equals(eventType.paramClass)) {
            return false;
        }
        String str = this.tag;
        if (str == null) {
            if (eventType.tag != null) {
                return false;
            }
        } else if (!str.equals(eventType.tag)) {
            return false;
        }
        return true;
    }
}
