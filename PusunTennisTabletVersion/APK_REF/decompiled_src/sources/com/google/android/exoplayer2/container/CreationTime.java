package com.google.android.exoplayer2.container;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.common.primitives.Longs;

/* loaded from: classes.dex */
public final class CreationTime implements Metadata.Entry {
    public static final Parcelable.Creator<CreationTime> CREATOR = new Parcelable.Creator<CreationTime>() { // from class: com.google.android.exoplayer2.container.CreationTime.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreationTime createFromParcel(Parcel parcel) {
            return new CreationTime(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreationTime[] newArray(int i) {
            return new CreationTime[i];
        }
    };
    public final long timestampMs;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.exoplayer2.metadata.Metadata.Entry
    public /* synthetic */ byte[] getWrappedMetadataBytes() {
        return Metadata.Entry.CC.$default$getWrappedMetadataBytes(this);
    }

    @Override // com.google.android.exoplayer2.metadata.Metadata.Entry
    public /* synthetic */ Format getWrappedMetadataFormat() {
        return Metadata.Entry.CC.$default$getWrappedMetadataFormat(this);
    }

    @Override // com.google.android.exoplayer2.metadata.Metadata.Entry
    public /* synthetic */ void populateMediaMetadata(MediaMetadata.Builder builder) {
        Metadata.Entry.CC.$default$populateMediaMetadata(this, builder);
    }

    public CreationTime(long j) {
        this.timestampMs = j;
    }

    private CreationTime(Parcel parcel) {
        this.timestampMs = parcel.readLong();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CreationTime) && this.timestampMs == ((CreationTime) obj).timestampMs;
    }

    public int hashCode() {
        return Longs.hashCode(this.timestampMs);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Creation time: ");
        long j = this.timestampMs;
        sb.append(j == -2082844800000L ? "unset" : Long.valueOf(j));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.timestampMs);
    }
}
