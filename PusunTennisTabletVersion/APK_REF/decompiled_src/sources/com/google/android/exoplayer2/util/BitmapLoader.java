package com.google.android.exoplayer2.util;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.common.util.concurrent.ListenableFuture;

@Deprecated
/* loaded from: classes.dex */
public interface BitmapLoader {
    ListenableFuture<Bitmap> decodeBitmap(byte[] bArr);

    ListenableFuture<Bitmap> loadBitmap(Uri uri);

    ListenableFuture<Bitmap> loadBitmapFromMetadata(MediaMetadata mediaMetadata);

    /* renamed from: com.google.android.exoplayer2.util.BitmapLoader$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static ListenableFuture $default$loadBitmapFromMetadata(BitmapLoader _this, MediaMetadata mediaMetadata) {
            if (mediaMetadata.artworkData != null) {
                return _this.decodeBitmap(mediaMetadata.artworkData);
            }
            if (mediaMetadata.artworkUri != null) {
                return _this.loadBitmap(mediaMetadata.artworkUri);
            }
            return null;
        }
    }
}
