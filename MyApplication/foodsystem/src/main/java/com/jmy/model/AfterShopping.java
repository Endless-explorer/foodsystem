package com.jmy.model;

/**
 * Created by johnseg on 2017/6/6.
 */

public class AfterShopping {
    private int img;
    private String name;
    private int number;
    private int sum;

    public AfterShopping() {
    }

    public AfterShopping(int img, String name, int number, int sum) {
        this.img = img;
        this.name = name;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
