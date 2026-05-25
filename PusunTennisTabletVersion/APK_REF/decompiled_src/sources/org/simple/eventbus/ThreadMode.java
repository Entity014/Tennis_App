package org.simple.eventbus;

/* loaded from: classes3.dex */
public enum ThreadMode {
    MAIN,
    POST,
    ASYNC;

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static ThreadMode[] valuesCustom() {
        ThreadMode[] valuesCustom = values();
        int length = valuesCustom.length;
        ThreadMode[] threadModeArr = new ThreadMode[length];
        System.arraycopy(valuesCustom, 0, threadModeArr, 0, length);
        return threadModeArr;
    }
}
