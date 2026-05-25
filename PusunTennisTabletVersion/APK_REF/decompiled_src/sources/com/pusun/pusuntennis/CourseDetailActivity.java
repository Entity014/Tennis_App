package com.pusun.pusuntennis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.video.VideoSize;
import com.pusun.pusuntennis.adapter.VideoAdapter;
import com.pusun.pusuntennis.utils.vo.TutorialItem;
import com.pusun.pusuntennis.utils.vo.TutorialVideo;
import java.util.List;

/* loaded from: classes3.dex */
public class CourseDetailActivity extends AppCompatActivity {
    private static final float PORTRAIT_THRESHOLD = 0.9f;
    private ImageView back_btn;
    private ImageButton exitFullscreenButton;
    private ImageButton fullscreenButton;
    private boolean isFullscreen = false;
    private boolean isPortraitVideo = false;
    private Button join_button;
    private ExoPlayer player;
    private PlayerView playerView;
    private PlayerView playerView2;
    private Button start_button;
    private TutorialItem tutorialItem;
    private List<TutorialVideo> tutorialVideos;
    private VideoAdapter videoAdapter;
    private RecyclerView videoList;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_course_detail);
        TutorialItem tutorialItem = (TutorialItem) getIntent().getSerializableExtra("data");
        this.tutorialItem = tutorialItem;
        this.tutorialVideos = tutorialItem.getTutorialVideos();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.CourseDetailActivity$$ExternalSyntheticLambda1
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return CourseDetailActivity.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        this.playerView = (PlayerView) findViewById(R.id.video_player_view);
        this.videoList = (RecyclerView) findViewById(R.id.video_list);
        this.playerView2 = (PlayerView) findViewById(R.id.player_view_fullscreen);
        this.exitFullscreenButton = (ImageButton) findViewById(R.id.exit_fullscreen_button);
        ExoPlayer build = new ExoPlayer.Builder(this).build();
        this.player = build;
        this.playerView.setPlayer(build);
        this.player.addListener(new Player.Listener() { // from class: com.pusun.pusuntennis.CourseDetailActivity.1
            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onAudioSessionIdChanged(int i) {
                Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onCues(CueGroup cueGroup) {
                Player.Listener.CC.$default$onCues(this, cueGroup);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onCues(List list) {
                Player.Listener.CC.$default$onCues(this, list);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
                Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onEvents(Player player, Player.Events events) {
                Player.Listener.CC.$default$onEvents(this, player, events);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onIsLoadingChanged(boolean z) {
                Player.Listener.CC.$default$onIsLoadingChanged(this, z);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onIsPlayingChanged(boolean z) {
                Player.Listener.CC.$default$onIsPlayingChanged(this, z);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onLoadingChanged(boolean z) {
                Player.Listener.CC.$default$onLoadingChanged(this, z);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
                Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
                Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onMetadata(Metadata metadata) {
                Player.Listener.CC.$default$onMetadata(this, metadata);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i) {
                Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPlaybackStateChanged(int i) {
                Player.Listener.CC.$default$onPlaybackStateChanged(this, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
                Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPlayerError(PlaybackException playbackException) {
                Player.Listener.CC.$default$onPlayerError(this, playbackException);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
                Player.Listener.CC.$default$onPlayerStateChanged(this, z, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPositionDiscontinuity(int i) {
                Player.Listener.CC.$default$onPositionDiscontinuity(this, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
                Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onRenderedFirstFrame() {
                Player.Listener.CC.$default$onRenderedFirstFrame(this);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onRepeatModeChanged(int i) {
                Player.Listener.CC.$default$onRepeatModeChanged(this, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onSeekBackIncrementChanged(long j) {
                Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
                Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
                Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
                Player.Listener.CC.$default$onSurfaceSizeChanged(this, i, i2);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onTimelineChanged(Timeline timeline, int i) {
                Player.Listener.CC.$default$onTimelineChanged(this, timeline, i);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onTracksChanged(Tracks tracks) {
                Player.Listener.CC.$default$onTracksChanged(this, tracks);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public /* synthetic */ void onVolumeChanged(float f) {
                Player.Listener.CC.$default$onVolumeChanged(this, f);
            }

            @Override // com.google.android.exoplayer2.Player.Listener
            public void onVideoSizeChanged(VideoSize videoSize) {
                int i = videoSize.width;
                int i2 = videoSize.height;
                if (i2 > 0) {
                    CourseDetailActivity.this.isPortraitVideo = i / i2 < CourseDetailActivity.PORTRAIT_THRESHOLD;
                }
            }
        });
        this.join_button = (Button) findViewById(R.id.join_button);
        Button button = (Button) findViewById(R.id.start_button);
        this.start_button = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.CourseDetailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MyApplication.machineNum == 1) {
                    Intent intent = new Intent(CourseDetailActivity.this, (Class<?>) VaryDrillActivityPTP.class);
                    intent.putExtra("ai", 10);
                    intent.putExtra("seles", "" + CourseDetailActivity.this.tutorialItem.getSeles());
                    CourseDetailActivity.this.startActivity(intent);
                } else if (MyApplication.machineNum == 2) {
                    Intent intent2 = new Intent(CourseDetailActivity.this, (Class<?>) VaryDrillActivityPS.class);
                    intent2.putExtra("ai", 10);
                    intent2.putExtra("seles", "" + CourseDetailActivity.this.tutorialItem.getSeles());
                    CourseDetailActivity.this.startActivity(intent2);
                } else if (MyApplication.machineNum == 6) {
                    Intent intent3 = new Intent(CourseDetailActivity.this, (Class<?>) VaryDrillActivity.class);
                    intent3.putExtra("ai", 10);
                    intent3.putExtra("seles", "" + CourseDetailActivity.this.tutorialItem.getSeles());
                    CourseDetailActivity.this.startActivity(intent3);
                } else if (MyApplication.machineNum != 3) {
                    Intent intent4 = new Intent(CourseDetailActivity.this, (Class<?>) VaryDrillActivity.class);
                    intent4.putExtra("ai", 10);
                    intent4.putExtra("seles", "" + CourseDetailActivity.this.tutorialItem.getSeles());
                    CourseDetailActivity.this.startActivity(intent4);
                }
                CourseDetailActivity.this.finish();
            }
        });
        if (MyApplication.machineNum == 3) {
            this.start_button.setVisibility(8);
        }
        ImageView imageView = (ImageView) findViewById(R.id.back_btn);
        this.back_btn = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.CourseDetailActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CourseDetailActivity.this.finish();
            }
        });
        this.videoAdapter = new VideoAdapter(this, this.tutorialVideos, new VideoAdapter.OnItemClickListener() { // from class: com.pusun.pusuntennis.CourseDetailActivity$$ExternalSyntheticLambda2
            @Override // com.pusun.pusuntennis.adapter.VideoAdapter.OnItemClickListener
            public final void onItemClick(TutorialVideo tutorialVideo) {
                CourseDetailActivity.this.playVideo(tutorialVideo);
            }
        }, this.tutorialItem.getImageRoute());
        this.videoList.setNestedScrollingEnabled(false);
        this.videoList.setLayoutManager(new GridLayoutManager(this, 1));
        this.videoList.setAdapter(this.videoAdapter);
        playVideo(this.tutorialVideos.get(0));
        ImageButton imageButton = (ImageButton) findViewById(R.id.fullscreen_button);
        this.fullscreenButton = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.CourseDetailActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CourseDetailActivity.this.m373lambda$onCreate$1$compusunpusuntennisCourseDetailActivity(view);
            }
        });
        this.exitFullscreenButton.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.CourseDetailActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CourseDetailActivity.this.m374lambda$onCreate$2$compusunpusuntennisCourseDetailActivity(view);
            }
        });
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(view.getPaddingLeft(), insets.top, view.getPaddingRight(), insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    /* renamed from: lambda$onCreate$1$com-pusun-pusuntennis-CourseDetailActivity, reason: not valid java name */
    /* synthetic */ void m373lambda$onCreate$1$compusunpusuntennisCourseDetailActivity(View view) {
        toggleFullscreen();
    }

    /* renamed from: lambda$onCreate$2$com-pusun-pusuntennis-CourseDetailActivity, reason: not valid java name */
    /* synthetic */ void m374lambda$onCreate$2$compusunpusuntennisCourseDetailActivity(View view) {
        toggleFullscreen();
    }

    private void toggleFullscreen() {
        if (this.isFullscreen) {
            setRequestedOrientation(1);
            this.playerView2.setPlayer(null);
            PlayerView playerView = (PlayerView) findViewById(R.id.video_player_view);
            this.playerView = playerView;
            playerView.setPlayer(this.player);
            ImageButton imageButton = (ImageButton) findViewById(R.id.fullscreen_button);
            this.fullscreenButton = imageButton;
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.CourseDetailActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CourseDetailActivity.this.m375xcbbef4c1(view);
                }
            });
            this.playerView2.setVisibility(8);
            this.exitFullscreenButton.setVisibility(8);
            showSystemUI();
        } else {
            if (this.isPortraitVideo) {
                setRequestedOrientation(1);
            } else {
                setRequestedOrientation(0);
            }
            this.playerView.setPlayer(null);
            this.playerView2.setVisibility(0);
            this.exitFullscreenButton.setVisibility(0);
            this.playerView2.setPlayer(this.player);
            hideSystemUI();
        }
        this.isFullscreen = !this.isFullscreen;
    }

    /* renamed from: lambda$toggleFullscreen$3$com-pusun-pusuntennis-CourseDetailActivity, reason: not valid java name */
    /* synthetic */ void m375xcbbef4c1(View view) {
        toggleFullscreen();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playVideo(TutorialVideo tutorialVideo) {
        this.player.clearMediaItems();
        this.player.addMediaItem(MediaItem.fromUri(Uri.parse(tutorialVideo.getVideo())));
        this.player.prepare();
        this.player.play();
    }

    private void hideSystemUI() {
        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        if (insetsController != null) {
            insetsController.hide(WindowInsetsCompat.Type.systemBars());
            insetsController.setSystemBarsBehavior(2);
        }
    }

    private void showSystemUI() {
        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        if (insetsController != null) {
            insetsController.show(WindowInsetsCompat.Type.systemBars());
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        setRequestedOrientation(-1);
        super.onDestroy();
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.release();
            this.player = null;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.isFullscreen) {
            toggleFullscreen();
        } else {
            super.onBackPressed();
        }
    }
}
