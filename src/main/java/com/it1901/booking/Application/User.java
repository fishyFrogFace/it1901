package com.it1901.booking.Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private int userID;
	private String userName;
	private String name;
    private String type;

    public enum Type { //Not currently used, see if possible to connect sql enums and java enums. If not drop them
        BOOKER, ADMINISTRATOR, ORGANIZER, TECH
    }

	public User(ResultSet userData) throws SQLException {
		this.userID = userData.getInt(1);
		this.userName = userData.getString(2);
		this.name = userData.getString(3);
        this.type = userData.getString(5);
	}

	public int getUserID() {
		return this.userID;
	}

    public String getUserType(){
        return type;
    }
}
