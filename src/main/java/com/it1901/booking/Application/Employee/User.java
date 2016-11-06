package com.it1901.booking.Application.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private final Integer userID;
	private final String name;
    private final Role role;

    public enum Role {
        booker, administrator, organizer, tech, manager
    }

	public User(ResultSet userData) throws SQLException {
		this.userID = userData.getInt(1);
		this.name = userData.getString(2);
        this.role = Role.valueOf(userData.getString(3));
        System.out.println("Role: "+role);
	}

	public int getUserID() {
		return this.userID;
	}

    public Role getUserRole(){
        return role;
    }

    public String getName() {
        return this.name;
    }
}
