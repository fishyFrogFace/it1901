package com.it1901.booking.Application.Event.Offer;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Offer {

    public enum offerState {
        unfinished, pending, accepted, declined,
        sent, returned, booked
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
    
    private boolean setManager(Integer managerID) {
        if (this.managerID == null) {
            this.managerID = managerID;
            return true;
        }
        else {
            return false;
        }
    }

    public void setState(offerState state) {
        this.state = state;
    }

    //inserts a new offer into the database
    public static Integer newOffer(Integer userID, DatabaseHandler dbh) throws SQLException {
        String query = "INSERT INTO offer VALUES " +
                "(DEFAULT, ?::offerState, ?, ?)" +
                "RETURNING offerID";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, offerState.pending.toString());
        prepStatement.setInt(2, userID);
        prepStatement.setNull(3, java.sql.Types.INTEGER);
        ResultSet rs = prepStatement.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

/*    //fetch offer from database and create offer
    public Offer fetchOffer() {
        return null;
    }*/
}
