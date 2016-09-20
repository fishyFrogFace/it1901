package com.it1901.booking.Application;

public class User {
	private int userID;
	private String userName;
	private String name;
    private String type;

    public enum Type {
        BOOKER, ADMINISTRATOR, ORGANIZER, TECH
    }

	public User(int userID, String userName, String name, String type){
		this.userID = userID;
		this.userName = userName;
		this.name = name;
        this.type = type;
	}

    public String getUserType(){
        return type;
    }
}
