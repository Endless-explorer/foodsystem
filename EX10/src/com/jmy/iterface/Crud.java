package com.jmy.iterface;

import java.util.List;

import com.jmy.model.MenuItem;
import com.jmy.model.PersonInfo;

public interface Crud {

	public List<PersonInfo> doQueryforLogin(String name,String password);
	public Boolean doInsertforReg(String name,String password,int sex,String birthdate,String email,String favorite);
	public List<MenuItem> doQueryforMenuItem();
	public String getImage(int position);
}
