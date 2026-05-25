package okio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes3.dex */
public final class HashingSource extends ForwardingSource {
    private final MessageDigest messageDigest;

    public static HashingSource md5(Source source) {
        return new HashingSource(source, "MD5");
    }

    public static HashingSource sha1(Source source) {
        return new HashingSource(source, "SHA-1");
    }

    public static HashingSource sha256(Source source) {
        return new HashingSource(source, "SHA-256");
    }

    private HashingSource(Source source, String str) {
        super(source);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    @Override // okio.ForwardingSource, okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        long read = super.read(buffer, j);
        if (read != -1) {
            long j2 = buffer.size - read;
            long j3 = buffer.size;
            Segment segment = buffer.head;
            while (j3 > j2) {
                segment = segment.prev;
                j3 -= segment.limit - segment.pos;
            }
            while (j3 < buffer.size) {
                int i = (int) ((segment.pos + j2) - j3);
                this.messageDigest.update(segment.data, i, segment.limit - i);
                j3 += segment.limit - segment.pos;
                segment = segment.next;
                j2 = j3;
            }
        }
        return read;
    }

    public ByteString hash() {
        return ByteString.of(this.messageDigest.digest());
    }
}
