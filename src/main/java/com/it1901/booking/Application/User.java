package com.it1901.booking.Application;

public abstract class User {
	private int userID;
	private String userName;
	private String name;

	User(int userID, String userName, String name){
		this.userID = userID;
		this.userName = userName;
		this.name = name;
	}
}
