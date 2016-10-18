package com.it1901.booking.Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private final Integer userID;
	private final String userName;
	private final String name;
    private final String type;

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

    public String getUserName() { return name; }
}
