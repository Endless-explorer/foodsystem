package com.jmy.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class MyOrder {
	
	
	@Id
	@GeneratedValue
	private int myorder_id;
	private String Comment;
	private Date ordertime;
	
	
	
	public MyOrder() {
		super();
	}


	public MyOrder(String comment, Date ordertime) {
		super();
		Comment = comment;
		this.ordertime = ordertime;
	}


	public String getComment() {
		return Comment;
	}


	public void setComment(String comment) {
		Comment = comment;
	}


	public Date getOrdertime() {
		return ordertime;
	}


	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	
	
	
	
	
	

}
