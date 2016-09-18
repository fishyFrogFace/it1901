package com.it1901.booking.Application;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseHandler {

        private static Connection con;

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
            prepStatement.executeUpdate();
        }

        //inserts a new event
        public static void createEvent(
                LocalDate date, int duration, int ticketPrice,
                int artistID, int offerID, int stageID) throws SQLException {
            String query = "INSERT INTO event VALUES " +
                    "(DEFAULT, ?, ?, ?, 0, ?, ?, ?)";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setObject(1, date);
            prepStatement.setInt(2, duration);
            prepStatement.setInt(3, ticketPrice);
            prepStatement.setInt(4, artistID);
            prepStatement.setInt(5, offerID);
            prepStatement.setInt(6, stageID);
            prepStatement.executeUpdate();
        }

        //inserts a new email
        public static void createEmail(String subject, String body, int offerID) throws SQLException {
            String query = "INSERT INTO email VALUES" +
                    "(DEFAULT, ?, ?, ?)";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, subject);
            prepStatement.setString(2, body);
            prepStatement.setInt(3, offerID);
            prepStatement.executeUpdate();
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

        public static ResultSet getArtistKey(String artist) throws SQLException {
            String query = "SELECT artist.artistID, name, genre, spotify, albumsSold, concerts " +
                    "FROM artist, artistinfo " +
                    "WHERE artist.name = ?" +
                    "AND artistinfo.artistID = artist.artistID";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, artist);
            return prepStatement.executeQuery();
        }
}
