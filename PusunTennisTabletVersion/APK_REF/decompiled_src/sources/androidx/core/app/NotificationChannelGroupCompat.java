package androidx.core.app;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Build;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class NotificationChannelGroupCompat {
    private boolean mBlocked;
    private List<NotificationChannelCompat> mChannels;
    String mDescription;
    final String mId;
    CharSequence mName;

    public static class Builder {
        final NotificationChannelGroupCompat mGroup;

        public Builder(String str) {
            this.mGroup = new NotificationChannelGroupCompat(str);
        }

        public Builder setName(CharSequence charSequence) {
            this.mGroup.mName = charSequence;
            return this;
        }

        public Builder setDescription(String str) {
            this.mGroup.mDescription = str;
            return this;
        }

        public NotificationChannelGroupCompat build() {
            return this.mGroup;
        }
    }

    NotificationChannelGroupCompat(String str) {
        this.mChannels = Collections.emptyList();
        this.mId = (String) Preconditions.checkNotNull(str);
    }

    NotificationChannelGroupCompat(NotificationChannelGroup notificationChannelGroup) {
        this(notificationChannelGroup, Collections.emptyList());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    NotificationChannelGroupCompat(android.app.NotificationChannelGroup r3, java.util.List<android.app.NotificationChannel> r4) {
        /*
            r2 = this;
            java.lang.String r0 = androidx.core.app.NotificationCompat$$ExternalSyntheticApiModelOutline0.m39m(r3)
            r2.<init>(r0)
            java.lang.CharSequence r0 = androidx.core.app.NotificationCompat$$ExternalSyntheticApiModelOutline0.m(r3)
            r2.mName = r0
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L19
            java.lang.String r0 = androidx.core.app.NotificationCompat$$ExternalSyntheticApiModelOutline0.m$1(r3)
            r2.mDescription = r0
        L19:
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r1) goto L2e
            boolean r4 = androidx.core.app.NotificationCompat$$ExternalSyntheticApiModelOutline0.m47m(r3)
            r2.mBlocked = r4
            java.util.List r3 = androidx.core.app.NotificationCompat$$ExternalSyntheticApiModelOutline0.m40m(r3)
            java.util.List r3 = r2.getChannelsCompat(r3)
            r2.mChannels = r3
            goto L34
        L2e:
            java.util.List r3 = r2.getChannelsCompat(r4)
            r2.mChannels = r3
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationChannelGroupCompat.<init>(android.app.NotificationChannelGroup, java.util.List):void");
    }

    private List<NotificationChannelCompat> getChannelsCompat(List<NotificationChannel> list) {
        String group;
        ArrayList arrayList = new ArrayList();
        Iterator<NotificationChannel> it = list.iterator();
        while (it.hasNext()) {
            NotificationChannel m = NotificationCompat$$ExternalSyntheticApiModelOutline0.m((Object) it.next());
            String str = this.mId;
            group = m.getGroup();
            if (str.equals(group)) {
                arrayList.add(new NotificationChannelCompat(m));
            }
        }
        return arrayList;
    }

    NotificationChannelGroup getNotificationChannelGroup() {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        NotificationCompat$$ExternalSyntheticApiModelOutline0.m42m();
        NotificationChannelGroup m = NotificationCompat$$ExternalSyntheticApiModelOutline0.m(this.mId, this.mName);
        if (Build.VERSION.SDK_INT >= 28) {
            m.setDescription(this.mDescription);
        }
        return m;
    }

    public Builder toBuilder() {
        return new Builder(this.mId).setName(this.mName).setDescription(this.mDescription);
    }

    public String getId() {
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public boolean isBlocked() {
        return this.mBlocked;
    }

    public List<NotificationChannelCompat> getChannels() {
        return this.mChannels;
    }
}
