package com.it1901.booking.Application;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class LoginHandler {

    public static User login(String userName, String password) throws SQLException {
        ResultSet userData = DatabaseHandler.getUser(userName);
        //check if the password matches the encrypted one
        if (userData.next()) {
            if (BCrypt.checkpw(password, userData.getString(4))) {
                return new User(userData.getInt(1), userData.getString(2), userData.getString(3), userData.getString(5));
            }
        }
        return null;
    }

}