package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.MediaPeriodId;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;

@Deprecated
/* loaded from: classes.dex */
public interface LoadControl {

    @Deprecated
    public static final MediaPeriodId EMPTY_MEDIA_PERIOD_ID = new MediaPeriodId(new Object());

    Allocator getAllocator();

    long getBackBufferDurationUs();

    void onPrepared();

    void onReleased();

    void onStopped();

    void onTracksSelected(Timeline timeline, MediaPeriodId mediaPeriodId, Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr);

    @Deprecated
    void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr);

    boolean retainBackBufferFromKeyframe();

    boolean shouldContinueLoading(long j, long j2, float f);

    @Deprecated
    boolean shouldStartPlayback(long j, float f, boolean z, long j2);

    boolean shouldStartPlayback(Timeline timeline, MediaPeriodId mediaPeriodId, long j, float f, boolean z, long j2);

    /* renamed from: com.google.android.exoplayer2.LoadControl$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}
