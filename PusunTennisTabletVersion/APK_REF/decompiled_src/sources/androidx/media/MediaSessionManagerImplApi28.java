package androidx.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import androidx.media.MediaSessionManager;
import androidx.media.MediaSessionManagerImplBase;
import androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0;

/* loaded from: classes.dex */
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
    android.media.session.MediaSessionManager mObject;

    MediaSessionManagerImplApi28(Context context) {
        super(context);
        this.mObject = (android.media.session.MediaSessionManager) context.getSystemService("media_session");
    }

    @Override // androidx.media.MediaSessionManagerImplApi21, androidx.media.MediaSessionManagerImplBase, androidx.media.MediaSessionManager.MediaSessionManagerImpl
    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        return super.isTrustedForMediaControl(remoteUserInfoImpl);
    }

    static final class RemoteUserInfoImplApi28 extends MediaSessionManagerImplBase.RemoteUserInfoImplBase {
        final MediaSessionManager.RemoteUserInfo mObject;

        RemoteUserInfoImplApi28(String str, int i, int i2) {
            super(str, i, i2);
            this.mObject = Trace$$ExternalSyntheticApiModelOutline0.m(str, i, i2);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        RemoteUserInfoImplApi28(android.media.session.MediaSessionManager.RemoteUserInfo r4) {
            /*
                r3 = this;
                java.lang.String r0 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m164m(r4)
                int r1 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m(r4)
                int r2 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m$1(r4)
                r3.<init>(r0, r1, r2)
                r3.mObject = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media.MediaSessionManagerImplApi28.RemoteUserInfoImplApi28.<init>(android.media.session.MediaSessionManager$RemoteUserInfo):void");
        }

        static String getPackageName(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            String packageName;
            packageName = remoteUserInfo.getPackageName();
            return packageName;
        }
    }
}
