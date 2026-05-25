package org.simple.eventbus;

import java.lang.reflect.Method;

/* loaded from: classes3.dex */
class TargetMethod {
    public Class<?> eventType;
    public Method method;
    public ThreadMode threadMode;

    public TargetMethod(Method method, Class<?> cls, ThreadMode threadMode) {
        this.method = method;
        method.setAccessible(true);
        this.eventType = cls;
        this.threadMode = threadMode;
    }

    public int hashCode() {
        Class<?> cls = this.eventType;
        int hashCode = ((cls == null ? 0 : cls.hashCode()) + 31) * 31;
        Method method = this.method;
        return hashCode + (method != null ? method.getName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TargetMethod targetMethod = (TargetMethod) obj;
        Class<?> cls = this.eventType;
        if (cls == null) {
            if (targetMethod.eventType != null) {
                return false;
            }
        } else if (!cls.equals(targetMethod.eventType)) {
            return false;
        }
        Method method = this.method;
        if (method == null) {
            if (targetMethod.method != null) {
                return false;
            }
        } else if (!method.getName().equals(targetMethod.method.getName())) {
            return false;
        }
        return true;
    }
}
