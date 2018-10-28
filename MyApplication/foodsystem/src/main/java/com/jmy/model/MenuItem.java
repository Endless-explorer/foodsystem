package com.jmy.model;

import com.jmy.foodsystem.R;

import java.io.Serializable;

/**
 * Created by johnseg on 2017/5/25.
 */

public class MenuItem implements Serializable {
	private int id;
	private String downurl;
    private String title;
    private int Averagestart;
    private long price;

    
    
    
    public MenuItem() {
		super();
	}

	

	public MenuItem(String downurl, String title, int averagestart, long price) {
		super();
		this.downurl = downurl;
		this.title = title;
		Averagestart = averagestart;
		this.price = price;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getDownurl() {
		return downurl;
	}

	public void setDownurl(String downurl) {
		this.downurl = downurl;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   

    public int getAveragestart() {
		switch(Averagestart) //v为view
    {
           case 1:
			   Averagestart= R.mipmap.one;
			   break;
		   case 2:
			   Averagestart= R.mipmap.two;
			break;
		   case 3:
			   Averagestart= R.mipmap.three;
			break;
		   case 4:
			   Averagestart= R.mipmap.four;
			break;
		   case 5:
			   Averagestart= R.mipmap.five;
			break;

      default:

      break;

    }
		return Averagestart;
	}

	public void setAveragestart(int averagestart) {
		Averagestart = averagestart;
	}

	public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
