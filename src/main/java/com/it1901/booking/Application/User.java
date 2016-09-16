package com.it1901.booking.Application;

public abstract class User {
	private String name = null; 
	private String password = null;
	
	User(String name, String password){
		this.name = name;
		this.password = password;
	}
}
