package com.it1901.booking.Application;

import java.sql.*;

public class DatabaseHandler {

        private static Connection con;

        public DatabaseHandler(String dbclass, String host, String user, String pass) {
            try {
                //establish connection
                Class.forName(dbclass);
                this.con = DriverManager.getConnection(host, user, pass);
                //will be moved to tests/interface
                if (con != null) {
                    System.out.println("Connected to database.\n");
                }
                else {
                    System.out.println("Could not establish a connection to the database.\n");
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }

        public static ResultSet getUser(String userName) throws SQLException {
            String query = "SELECT * FROM employee WHERE username = ?";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, userName);
            return prepStatement.executeQuery();
        }

}
