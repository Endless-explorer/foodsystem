package com.jmy.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



  @Entity 
public class PersonInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int _id;
	private String name,password,email;
	private String birthdate;
	private int sex;
	private String favorite;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="_id")
	private Set<StarRating> starrating;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="_id")
    private Set<MyOrder> myorders;
	
	
	public PersonInfo() {
		super();
	}
	public PersonInfo(String name, String password, String email,
			String birthdate, int sex, String favorite) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.birthdate = birthdate;
		this.sex = sex;
		this.favorite = favorite;

	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public Set<StarRating> getStarrating() {
		return starrating;
	}
	public void setStarrating(Set<StarRating> starrating) {
		this.starrating = starrating;
	}
	public Set<MyOrder> getMyorders() {
		return myorders;
	}
	public void setMyorders(Set<MyOrder> myorders) {
		this.myorders = myorders;
	}
	
	
	

}
