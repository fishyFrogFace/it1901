package com.it1901.booking.Application.Concert.Offer;

import com.it1901.booking.Application.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;

public class Offer {

    public enum offerState {
        unfinished, pending, declined, accepted,
        sent, cancelled, booked
    }
    private final Integer offerID;
    private offerState state;
    private final Integer bookerID;

    public Offer(OfferBuilder builder) {
        this.offerID = builder.offerID;
        this.state = builder.state;
        this.bookerID = builder.bookerID;
    }

    public void setStatusChange(Integer employeeID, DatabaseHandler dbh) throws SQLException {
            String query =
                    "INSERT INTO changedBy VALUES " +
                            "(?, ?, ?)";
            PreparedStatement prepStatement = dbh.prepareQuery(query);
            prepStatement.setObject(1, LocalDateTime.now(), Types.TIMESTAMP);
            prepStatement.setInt(2, this.offerID);
            prepStatement.setInt(3, employeeID);
            prepStatement.executeUpdate();
    }

    public void saveState(offerState state, DatabaseHandler dbh) throws SQLException {
        String query =
                "UPDATE offer " +
                        "SET state = ?::offerState " +
                        "WHERE offerID = ?";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, state.toString());
        prepStatement.setInt(2, this.offerID);
        prepStatement.executeUpdate();
        this.state = state;
    }

    public Integer getOfferID() {
        return this.offerID;
    }

    public offerState getState() {
        return this.state;
    }

    public Integer getBookerID() {
        return this.bookerID;
    }
}
