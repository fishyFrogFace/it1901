package com.it1901.booking.Application.Concert.Offer;

import com.it1901.booking.Application.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Offer {

    public enum offerState {
        unfinished, pending, accepted, declined,
        sent, cancelled, booked
    }
    private final Integer offerID;
    private offerState state;
    private final Integer bookerID;
    private Integer managerID;

    public Offer(OfferBuilder builder) {
        this.offerID = builder.offerID;
        this.state = builder.state;
        this.bookerID = builder.bookerID;
        this.managerID = builder.managerID;
    }
    
    public void setManager(Integer managerID, DatabaseHandler dbh) throws SQLException {
        if (this.managerID == null) {
            String query =
                    "UPDATE offer " +
                            "SET state = ?::offerState," +
                            "WHERE offerID = ?";
            PreparedStatement prepStatement = dbh.prepareQuery(query);
            prepStatement.setString(1, state.toString());
            prepStatement.setInt(2, this.offerID);
            prepStatement.executeUpdate();
            this.managerID = managerID;
        }
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
