package com.google.android.exoplayer2.upstream.experimental;

@Deprecated
/* loaded from: classes.dex */
public interface BandwidthStatistic {
    void addSample(long j, long j2);

    long getBandwidthEstimate();

    void reset();
}
