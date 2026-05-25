package com.pusun.pusuntennis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pusun.pusuntennis.MyApplication;
import com.pusun.pusuntennis.R;
import com.pusun.pusuntennis.utils.GlideUtil;
import com.pusun.pusuntennis.utils.vo.TutorialVideo;
import java.util.List;

/* loaded from: classes3.dex */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private Context context;
    private OnItemClickListener listener;
    private String route;
    private List<TutorialVideo> videoItems;

    public interface OnItemClickListener {
        void onItemClick(TutorialVideo tutorialVideo);
    }

    public VideoAdapter(Context context, List<TutorialVideo> list, OnItemClickListener onItemClickListener, String str) {
        this.videoItems = list;
        this.listener = onItemClickListener;
        this.context = context;
        this.route = str;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VideoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VideoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VideoViewHolder videoViewHolder, int i) {
        final TutorialVideo tutorialVideo = this.videoItems.get(i);
        videoViewHolder.videoName.setText(tutorialVideo.getVideoName());
        if (this.route != null) {
            if (!tutorialVideo.getVideo().contains("http")) {
                GlideUtil.loadImage(this.context, MyApplication.BASE_URL + "/" + this.route, videoViewHolder.videoThumbnail);
            } else {
                GlideUtil.loadImage(this.context, this.route, videoViewHolder.videoThumbnail);
            }
        }
        videoViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VideoAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoAdapter.this.m378x6238f9d(tutorialVideo, view);
            }
        });
    }

    /* renamed from: lambda$onBindViewHolder$0$com-pusun-pusuntennis-adapter-VideoAdapter, reason: not valid java name */
    /* synthetic */ void m378x6238f9d(TutorialVideo tutorialVideo, View view) {
        OnItemClickListener onItemClickListener = this.listener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(tutorialVideo);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.videoItems.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView videoName;
        ImageView videoThumbnail;

        public VideoViewHolder(View view) {
            super(view);
            this.videoThumbnail = (ImageView) view.findViewById(R.id.video_thumbnail);
            this.videoName = (TextView) view.findViewById(R.id.video_name);
        }
    }
}
