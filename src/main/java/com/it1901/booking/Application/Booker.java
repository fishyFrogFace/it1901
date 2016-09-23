package com.it1901.booking.Application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Booker extends User {

    public Booker(ResultSet userData) throws SQLException {
        super(userData);
    }

    public void newOffer(DatabaseHandler dbh, LocalDate date, int duration, int ticketPrice,
                         int artistID, int offerID, int stageID) {
        //TODO make logic for creating offer and event
    }

    //inserts a new offer
    private void createOffer(DatabaseHandler dbh) throws SQLException {
        String query = "INSERT INTO offer VALUES " +
                "(DEFAULT, ?::offerState, ?, ?)";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, "pending");
        prepStatement.setInt(2, this.getUserID());
        prepStatement.setNull(3, java.sql.Types.INTEGER);
        prepStatement.executeUpdate();
    }

    //inserts a new event
    private void createEvent(
            LocalDate date, int duration, int ticketPrice,
            int artistID, int offerID, int stageID, DatabaseHandler dbh) throws SQLException {
        String query = "INSERT INTO concert VALUES " +
                "(DEFAULT, ?, ?, ?, 0, ?, ?, ?)";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setObject(1, date);
        prepStatement.setInt(2, duration);
        prepStatement.setInt(3, ticketPrice);
        prepStatement.setInt(4, artistID);
        prepStatement.setInt(5, offerID);
        prepStatement.setInt(6, stageID);
        prepStatement.executeUpdate();
    }

    //inserts a new email
    private void createEmail(String subject, String body, int offerID, DatabaseHandler dbh) throws SQLException {
        String query = "INSERT INTO email VALUES " +
                "(DEFAULT, ?, ?, ?)";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, subject);
        prepStatement.setString(2, body);
        prepStatement.setInt(3, offerID);
        prepStatement.executeUpdate();
    }
}
