package com.pusun.pusuntennis.utils.vo;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes3.dex */
public class TutorialItem implements Serializable {
    private String daoName;
    private String imageRoute;
    private String info;
    private String seles;
    private long serialVersionUID;
    private List<TutorialVideo> tutorialVideos;
    private int useTimes;

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public String getImageRoute() {
        return this.imageRoute;
    }

    public void setImageRoute(String str) {
        this.imageRoute = str;
    }

    public int getUseTimes() {
        return this.useTimes;
    }

    public void setUseTimes(int i) {
        this.useTimes = i;
    }

    public void setTutorialVideos(List<TutorialVideo> list) {
        this.tutorialVideos = list;
    }

    public long getSerialVersionUID() {
        return this.serialVersionUID;
    }

    public void setSerialVersionUID(long j) {
        this.serialVersionUID = j;
    }

    public String getSeles() {
        return this.seles;
    }

    public void setSeles(String str) {
        this.seles = str;
    }

    public String getDaoName() {
        return this.daoName;
    }

    public void setDaoName(String str) {
        this.daoName = str;
    }

    public List<TutorialVideo> getTutorialVideos() {
        return this.tutorialVideos;
    }
}
