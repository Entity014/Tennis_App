package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: classes.dex */
interface PatternCompiler {
    CommonPattern compile(String str);

    boolean isPcreLike();
}
