package com.jmy.model;

import android.graphics.Bitmap;

/**
 * Created by johnseg on 2017/6/2.
 */

public class MyItem {
    String title;
    Bitmap img;
    int star;
    long price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
