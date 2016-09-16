package com.it1901.booking.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class LoginHandler {

    public static User login(String userName, String password) throws SQLException {
        User user = null;
        ResultSet userData = DatabaseHandler987.getUser(userName);

        //check if the password matches the encrypted one
        if (userData.next()) {
            int userID = userData.getInt(1);
            if (BCrypt.checkpw(password, userData.getString(4))) {
                if (userData.getString(5).equals("booker")) {
                    user = new Booker(
                            userData.getInt(1),
                            userData.getString(2),
                            userData.getString(3));
                }
            }
        }
        return user;
    }

    public static void main(String[] args) {
        //TODO load login screen

        DatabaseHandler987 dbh = new DatabaseHandler987(
                "org.postgresql.Driver",
                "jdbc:postgresql://52.40.176.177:5432/booking",
                "team",
                "it1901");

        //TODO call LoginController here
        User booker = null;
        try {
            booker = login("meepMeep", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (booker != null) {
            System.out.println("Yay! You're logged in as a booker!");
        }
        else {
            System.out.println("You failed!");
        }
    }
}