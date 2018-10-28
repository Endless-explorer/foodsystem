package com.jmy.model;

/**
 * Created by johnseg on 2017/6/5.
 */

public class NoPay {
    private int img;
    private String name;
    private String time;
    private String sum;

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public NoPay(int img, String name, String time, String sum) {
        this.img = img;
        this.name = name;
        this.time = time;
        this.sum = sum;
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
