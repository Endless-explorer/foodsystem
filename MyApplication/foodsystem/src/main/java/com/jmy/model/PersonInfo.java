package com.jmy.model;



public class PersonInfo {
	private int _id;
	private String name,password,email;
	private String birthdate;
	int sex;
	String favorite;


	public PersonInfo() {
	}

	public PersonInfo(String name, String password, String email, String birthdate, int sex, String favorite) {
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
		if(email==null)
		{
              email="未填写";
		}

		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBirthdate() {
		if(birthdate==null)
		{
			birthdate="未填写";
		}

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
		if(favorite==null)
		{
			favorite="未填写";
		}

		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	
	

}
