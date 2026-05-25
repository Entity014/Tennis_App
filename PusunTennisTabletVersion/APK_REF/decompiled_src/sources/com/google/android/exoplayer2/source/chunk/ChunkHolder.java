package com.google.android.exoplayer2.source.chunk;

@Deprecated
/* loaded from: classes.dex */
public final class ChunkHolder {
    public Chunk chunk;
    public boolean endOfStream;

    public void clear() {
        this.chunk = null;
        this.endOfStream = false;
    }
}
