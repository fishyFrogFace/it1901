package com.it1901.booking.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class LoginHandler {

    public static Booker login(String userName, String password, Connection con) throws SQLException {
        Booker booker = null;

        //get user data for given username from db
        String query = "SELECT * FROM employee WHERE username = ?";
        PreparedStatement prepStatement = con.prepareStatement(query);
        prepStatement.setString(1, userName);
        ResultSet userData = prepStatement.executeQuery();
        int userID = userData.getInt(1);

        //check if the password matches the encrypted one
        if (userData.next()) {
            if (BCrypt.checkpw(password, userData.getString(4))) {
                if (userData.getString(5).equals("booker")) {
                    booker = new Booker(userID);
                }
            }
        }
        return booker;
    }

    public static void main(String[] args) {
        try {
            //establish connection
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://52.40.176.177:5432/booking", "postgres", "it1901");
            if (con != null) {
                System.out.println("Connected to database.\n");
            }
            else {
                System.out.printl("Could not establish a connection to the database.\n");
            }

            //log in as a booker
            Booker booker = login("meepMeep", "1234", con);
            if (booker != null) {
                System.out.println("Yay! You're logged in as a booker!");
            }
            else {
                System.out.println("You failed!");
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}