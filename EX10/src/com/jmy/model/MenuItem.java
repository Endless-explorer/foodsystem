package com.jmy.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Created by johnseg on 2017/5/25.
 */
@Entity
public class MenuItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int business_id;
	private String downurl;
    private String title;
    private int Averagestart;
    private long price;
    @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="business_id")
    private Set<MyOrder> myorders;
    

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



	

    public int getBusiness_id() {
		return business_id;
	}



	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
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



	public Set<MyOrder> getMyorders() {
		return myorders;
	}



	public void setMyorders(Set<MyOrder> myorders) {
		this.myorders = myorders;
	}
    
}
