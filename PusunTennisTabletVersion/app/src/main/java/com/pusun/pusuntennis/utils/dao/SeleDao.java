package com.pusun.pusuntennis.utils.dao;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class SeleDao implements Serializable {
    private static final long serialVersionUID = 36;
    private String daoName;
    private int freq;
    private int item1;
    private int item2;
    private int item3;
    private Long number;
    private int rote;
    private String seles;
    private int velo;

    public SeleDao(Long l, String str, String str2, int i, int i2, int i3, int i4, int i5, int i6) {
        this.number = l;
        this.daoName = str;
        this.seles = str2;
        this.item1 = i;
        this.item2 = i2;
        this.item3 = i3;
        this.freq = i4;
        this.velo = i5;
        this.rote = i6;
    }

    public SeleDao() {
    }

    public Long getNumber() {
        return this.number;
    }

    public void setNumber(Long l) {
        this.number = l;
    }

    public String getDaoName() {
        return this.daoName;
    }

    public void setDaoName(String str) {
        this.daoName = str;
    }

    public String getSeles() {
        return this.seles;
    }

    public void setSeles(String str) {
        this.seles = str;
    }

    public int getItem1() {
        return this.item1;
    }

    public void setItem1(int i) {
        this.item1 = i;
    }

    public int getItem2() {
        return this.item2;
    }

    public void setItem2(int i) {
        this.item2 = i;
    }

    public int getItem3() {
        return this.item3;
    }

    public void setItem3(int i) {
        this.item3 = i;
    }

    public int getFreq() {
        return this.freq;
    }

    public void setFreq(int i) {
        this.freq = i;
    }

    public int getVelo() {
        return this.velo;
    }

    public void setVelo(int i) {
        this.velo = i;
    }

    public int getRote() {
        return this.rote;
    }

    public void setRote(int i) {
        this.rote = i;
    }
}
