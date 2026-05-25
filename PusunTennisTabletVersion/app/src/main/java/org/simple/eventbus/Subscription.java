package org.simple.eventbus;

import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class Subscription {
    public Object subscriber;
    public Method targetMethod;
    public ThreadMode threadMode;

    public Subscription(Object obj, TargetMethod targetMethod) {
        this.subscriber = obj;
        this.targetMethod = targetMethod.method;
        this.threadMode = targetMethod.threadMode;
    }

    public int hashCode() {
        Object obj = this.subscriber;
        int hashCode = ((obj == null ? 0 : obj.hashCode()) + 31) * 31;
        Method method = this.targetMethod;
        return hashCode + (method != null ? method.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        Object obj2 = this.subscriber;
        if (obj2 == null) {
            if (subscription.subscriber != null) {
                return false;
            }
        } else if (!obj2.equals(subscription.subscriber)) {
            return false;
        }
        Method method = this.targetMethod;
        if (method == null) {
            if (subscription.targetMethod != null) {
                return false;
            }
        } else if (!method.equals(subscription.targetMethod)) {
            return false;
        }
        return true;
    }
}
