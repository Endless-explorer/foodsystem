package com.jmy.model;

import com.jmy.foodsystem.R;

/**
 * Created by johnseg on 2017/6/5.
 */

public class Comment {
    private int img;
    private String name;
    private String time;
    private int star;
    private String content;
    private int like;


    public Comment() {
    }

    public Comment(int img, String name, String time, int star, String content,int like) {
        this.img = img;
        this.name = name;
        this.time = time;
        this.star = star;
        this.content = content;
        this.like=like;
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

    public int getStar() {
        switch(star) //v为view
        {
            case 1:
                star= R.mipmap.one;
                break;
            case 2:
                star= R.mipmap.two;
                break;
            case 3:
                star= R.mipmap.three;
                break;
            case 4:
                star= R.mipmap.four;
                break;
            case 5:
                star= R.mipmap.five;
                break;

            default:

                break;

        }
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike() {

        switch (like)
        {
            case 0:
                like=R.mipmap.like_black;
                break;
            case 1:
                like=R.mipmap.like;
        }
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
