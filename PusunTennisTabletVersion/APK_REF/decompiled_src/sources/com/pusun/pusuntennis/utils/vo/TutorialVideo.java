package com.pusun.pusuntennis.utils.vo;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class TutorialVideo implements Serializable {
    private int tutorialBallWayId;
    private String video;
    private String videoName;

    public int getTutorialBallWayId() {
        return this.tutorialBallWayId;
    }

    public void setTutorialBallWayId(int i) {
        this.tutorialBallWayId = i;
    }

    public void setVideo(String str) {
        this.video = str;
    }

    public void setVideoName(String str) {
        this.videoName = str;
    }

    public String getVideo() {
        return this.video;
    }

    public String getVideoName() {
        return this.videoName;
    }
}
