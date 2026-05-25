package com.google.android.exoplayer2;

import android.media.AudioDeviceInfo;
import android.media.AudioFocusRequest;
import android.media.AudioTrack;
import android.media.MediaDrmResetException;
import android.media.PlaybackParams;
import android.media.metrics.MediaMetricsManager;
import android.media.metrics.NetworkEvent;
import android.media.metrics.PlaybackErrorEvent;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackStateEvent;
import android.media.metrics.TrackChangeEvent;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class AudioFocusManager$$ExternalSyntheticApiModelOutline3 {
    public static /* bridge */ /* synthetic */ AudioDeviceInfo m(Object obj) {
        return (AudioDeviceInfo) obj;
    }

    public static /* synthetic */ AudioFocusRequest.Builder m(int i) {
        return new AudioFocusRequest.Builder(i);
    }

    public static /* synthetic */ AudioFocusRequest.Builder m(AudioFocusRequest audioFocusRequest) {
        return new AudioFocusRequest.Builder(audioFocusRequest);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ AudioTrack.Builder m175m() {
        return new AudioTrack.Builder();
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ PlaybackParams m176m() {
        return new PlaybackParams();
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ MediaMetricsManager m179m(Object obj) {
        return (MediaMetricsManager) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ NetworkEvent.Builder m180m() {
        return new NetworkEvent.Builder();
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ PlaybackErrorEvent.Builder m181m() {
        return new PlaybackErrorEvent.Builder();
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ PlaybackMetrics.Builder m182m() {
        return new PlaybackMetrics.Builder();
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ PlaybackMetrics.Builder m183m(Object obj) {
        return (PlaybackMetrics.Builder) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ PlaybackStateEvent.Builder m184m() {
        return new PlaybackStateEvent.Builder();
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ TrackChangeEvent.Builder m185m(int i) {
        return new TrackChangeEvent.Builder(i);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m186m() {
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m191m(Object obj) {
        return obj instanceof MediaDrmResetException;
    }
}
