package com.jmy.model;

import android.graphics.Bitmap;

/**
 * Created by johnseg on 2017/6/7.
 */

public class Collection {
    private Bitmap img;
    private String name;
    private int star;
    private String location;
    private int distance;

    public Collection() {
    }

    public Collection(Bitmap img, String name, int star, String location, int distance) {
        this.img = img;
        this.name = name;
        this.star = star;
        this.location = location;
        this.distance = distance;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
