package com.it1901.booking.Application;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseHandler {

        private Connection con;

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
        public ResultSet getUser(String userName) throws SQLException {
            String query = "SELECT * FROM employee WHERE username = ?";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, userName);
            return prepStatement.executeQuery();
        }

        //inserts a new offer
        public void createOffer(int bookerID) throws SQLException {
            String query = "INSERT INTO offer VALUES " +
                    "(DEFAULT, ?::offerState, ?, ?)";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, "pending");
            prepStatement.setInt(2, bookerID);
            prepStatement.setNull(3, java.sql.Types.INTEGER);
            prepStatement.executeUpdate();
        }

        //inserts a new event
        public void createEvent(
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
        public void createEmail(String subject, String body, int offerID) throws SQLException {
            String query = "INSERT INTO email VALUES " +
                    "(DEFAULT, ?, ?, ?)";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, subject);
            prepStatement.setString(2, body);
            prepStatement.setInt(3, offerID);
            prepStatement.executeUpdate();
        }

        //writes a ResultSet to console
        public int displayResult(ResultSet result) throws SQLException {
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

        //fetches artist name and key information (streaming etc.)
        public ResultSet getArtistKey(String artist) throws SQLException {
            String query = "SELECT artist.artistID, name, genre, spotify, albumsSold, concerts " +
                    "FROM artist, artistinfo " +
                    "WHERE artist.name = ? " +
                    "AND artistinfo.artistID = artist.artistID";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, artist);
            return prepStatement.executeQuery();
        }

        //fetches stage and ticketsSold for concerts in a genre
        public ResultSet eventsByGenre(String genre) throws SQLException {
            String query = "SELECT concert.concertID, genre, ticketsSold, stage.name " +
                    "FROM concert, stage, artist " +
                    "WHERE genre = ?::musicgenre " +
                    "AND concert.artistID = artist.artistID " +
                    "AND concert.stageID = stage.stageID " +
                    "ORDER BY genre, ticketsSold";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, genre);
            System.out.println(prepStatement);
            return prepStatement.executeQuery();
        }
}
