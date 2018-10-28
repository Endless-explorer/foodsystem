package com.jmy.model;

/**
 * Created by johnseg on 2017/6/6.
 */

public class NoUser {
    private int img;
    private String name;
    private String dateline;
    private int number;
    private int price;

    public NoUser() {
    }

    public NoUser(int img, String name, String dateline, int number, int price) {
        this.img = img;
        this.name = name;
        this.dateline = dateline;
        this.number = number;
        this.price = price;
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

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
