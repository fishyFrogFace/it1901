package com.it1901.booking.Application.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.User.User;
import org.mindrot.jbcrypt.BCrypt;

public class LoginHandler {

    public static User login(String userName, String password, DatabaseHandler dbh) throws SQLException {
        ResultSet userData = getUser(userName, dbh);
        //check if the password matches the encrypted one
        if (userData.next()) {
            if (BCrypt.checkpw(password, userData.getString(4))) {
                return new User(userData);
            }
        }
        return null;
    }

    public static ResultSet getUser(String userName, DatabaseHandler dbh) throws SQLException {
        String query = "SELECT employeeID, name, accountType, password FROM employee WHERE username = ?";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, userName);
        return prepStatement.executeQuery();
    }

}