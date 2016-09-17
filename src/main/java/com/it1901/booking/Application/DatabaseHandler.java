package com.it1901.booking.Application;

import java.sql.*;

public class DatabaseHandler {

        private static Connection con;
        private enum offerState {
            pending, accepted, declined;
        }

        public DatabaseHandler(String dbclass, String host, String user, String pass) {
            try {
                Class.forName(dbclass);
                this.con = DriverManager.getConnection(host, user, pass);
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

        //fetches a user from the database
        public static ResultSet getUser(String userName) throws SQLException {
            String query = "SELECT * FROM employee WHERE username = ?";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, userName);
            return prepStatement.executeQuery();
        }

        //inserts a new offer
        public static void createOffer(int bookerID) throws SQLException {
            String query = "INSERT INTO offer VALUES " +
                    "(DEFAULT, ?::offerState, ?, ?)";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, "pending");
            prepStatement.setInt(2, bookerID);
            prepStatement.setNull(3, java.sql.Types.INTEGER);
        }

        //writes a ResultSet to console
        public static int displayResult(ResultSet result) throws SQLException {
            ResultSetMetaData resultMeta = result.getMetaData();
            int columnsNumber = resultMeta.getColumnCount();

            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(result.getString(i) + " ");
                }
                System.out.println();
            }
            return 0;
        }

}
