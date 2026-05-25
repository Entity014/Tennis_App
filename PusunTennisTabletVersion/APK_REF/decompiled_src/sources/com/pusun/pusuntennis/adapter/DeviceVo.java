package com.pusun.pusuntennis.adapter;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class DeviceVo implements Serializable {
    private String County;
    private int Discount;
    private double Distance;
    private String ID;
    private String Introduce;
    private double Latitude;
    private double Longitude;
    private String Phone;
    private String VenueImg;
    private String VenueName;
    private int venueDiscount;

    public int getVenueDiscount() {
        return this.venueDiscount;
    }

    public void setVenueDiscount(int i) {
        this.venueDiscount = i;
    }

    public int getDiscount() {
        return this.Discount;
    }

    public void setDiscount(int i) {
        this.Discount = i;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String str) {
        this.Phone = str;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String str) {
        this.ID = str;
    }

    public String getIntroduce() {
        return this.Introduce;
    }

    public void setIntroduce(String str) {
        this.Introduce = str;
    }

    public String getVenueName() {
        return this.VenueName;
    }

    public void setVenueName(String str) {
        this.VenueName = str;
    }

    public String getVenueImg() {
        return this.VenueImg;
    }

    public void setVenueImg(String str) {
        this.VenueImg = str;
    }

    public String getCounty() {
        return this.County;
    }

    public void setCounty(String str) {
        this.County = str;
    }

    public double getLatitude() {
        return this.Latitude;
    }

    public void setLatitude(double d) {
        this.Latitude = d;
    }

    public double getLongitude() {
        return this.Longitude;
    }

    public void setLongitude(double d) {
        this.Longitude = d;
    }

    public double getDistance() {
        return this.Distance;
    }

    public void setDistance(double d) {
        this.Distance = d;
    }
}
