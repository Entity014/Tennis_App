package androidx.activity;

import android.app.NotificationChannel;
import android.app.job.JobWorkItem;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.view.textclassifier.TextClassificationManager;
import android.widget.ThemedSpinnerAdapter;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class ComponentDialog$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ NotificationChannel m(String str, CharSequence charSequence, int i) {
        return new NotificationChannel(str, charSequence, i);
    }

    public static /* synthetic */ JobWorkItem m(Intent intent) {
        return new JobWorkItem(intent);
    }

    public static /* synthetic */ MediaSession m(Context context, String str, Bundle bundle) {
        return new MediaSession(context, str, bundle);
    }

    public static /* bridge */ /* synthetic */ TextClassificationManager m(Object obj) {
        return (TextClassificationManager) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ThemedSpinnerAdapter m7m(Object obj) {
        return (ThemedSpinnerAdapter) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m9m() {
        return TextClassificationManager.class;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m11m() {
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m14m(Object obj) {
        return obj instanceof ThemedSpinnerAdapter;
    }
}
