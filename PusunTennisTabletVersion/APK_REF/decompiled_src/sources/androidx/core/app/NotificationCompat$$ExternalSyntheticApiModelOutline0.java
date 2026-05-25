package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Icon;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class NotificationCompat$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ Notification.Action.Builder m(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
        return new Notification.Action.Builder(icon, charSequence, pendingIntent);
    }

    public static /* synthetic */ Notification.Builder m(Context context, String str) {
        return new Notification.Builder(context, str);
    }

    public static /* synthetic */ Notification.DecoratedCustomViewStyle m() {
        return new Notification.DecoratedCustomViewStyle();
    }

    public static /* synthetic */ Notification.MessagingStyle.Message m(CharSequence charSequence, long j, android.app.Person person) {
        return new Notification.MessagingStyle.Message(charSequence, j, person);
    }

    public static /* synthetic */ Notification.MessagingStyle.Message m(CharSequence charSequence, long j, CharSequence charSequence2) {
        return new Notification.MessagingStyle.Message(charSequence, j, charSequence2);
    }

    public static /* synthetic */ Notification.MessagingStyle m(android.app.Person person) {
        return new Notification.MessagingStyle(person);
    }

    public static /* synthetic */ Notification.MessagingStyle m(CharSequence charSequence) {
        return new Notification.MessagingStyle(charSequence);
    }

    public static /* bridge */ /* synthetic */ NotificationChannel m(Object obj) {
        return (NotificationChannel) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NotificationChannelGroup m26m(Object obj) {
        return (NotificationChannelGroup) obj;
    }

    public static /* synthetic */ NotificationChannelGroup m(String str, CharSequence charSequence) {
        return new NotificationChannelGroup(str, charSequence);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ android.app.Person m27m(Object obj) {
        return (android.app.Person) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ ShortcutInfo.Builder m29m(Context context, String str) {
        return new ShortcutInfo.Builder(context, str);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Icon m32m(Object obj) {
        return (Icon) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m36m() {
        return Notification.MessagingStyle.class;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m42m() {
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m49m(Object obj) {
        return obj instanceof Icon;
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return Notification.DecoratedCustomViewStyle.class;
    }

    /* renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m51m$1() {
    }

    public static /* synthetic */ void m$2() {
    }

    public static /* synthetic */ void m$3() {
    }

    public static /* synthetic */ void m$4() {
    }
}
