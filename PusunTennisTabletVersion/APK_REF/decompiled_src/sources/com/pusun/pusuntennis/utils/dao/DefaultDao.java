package com.pusun.pusuntennis.utils.dao;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class DefaultDao implements Serializable {
    private static final long serialVersionUID = 38;
    private String daoName;
    private int freq;
    private int grade;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private Long number;
    private int rote;
    private String seles;
    private int velo;

    public DefaultDao(Long l, String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.number = l;
        this.daoName = str;
        this.seles = str2;
        this.grade = i;
        this.item2 = i2;
        this.item3 = i3;
        this.item4 = i4;
        this.item5 = i5;
        this.item6 = i6;
        this.freq = i7;
        this.velo = i8;
        this.rote = i9;
    }

    public DefaultDao() {
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

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int i) {
        this.grade = i;
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

    public int getItem4() {
        return this.item4;
    }

    public void setItem4(int i) {
        this.item4 = i;
    }

    public int getItem5() {
        return this.item5;
    }

    public void setItem5(int i) {
        this.item5 = i;
    }

    public int getItem6() {
        return this.item6;
    }

    public void setItem6(int i) {
        this.item6 = i;
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
