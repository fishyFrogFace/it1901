package com.it1901.booking.Application.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private final Integer userID;
	private final String name;
    private final Type type;

    public enum Type {
        booker, administrator, organizer, tech, manager
    }

	public User(ResultSet userData) throws SQLException {
		this.userID = userData.getInt(1);
		this.name = userData.getString(2);
        this.type = Type.valueOf(userData.getString(3));
        System.out.println("Type: "+type);
	}

	public int getUserID() {
		return this.userID;
	}

    public String getUserType(){
        return type.toString();
    }

    public String getName() {
        return this.name;
    }
}
