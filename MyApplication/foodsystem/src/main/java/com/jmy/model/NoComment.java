package com.jmy.model;

/**
 * Created by johnseg on 2017/6/6.
 */

public class NoComment {
       private int img;
       private String name;
       private String time;

    public NoComment() {
    }

    public NoComment(int img, String name, String time) {
        this.img = img;
        this.name = name;
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
