package com.it1901.booking.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OfferHandler {

    //inserts a new offer
    private void saveOffer(DatabaseHandler dbh, User user) throws SQLException {
        String query = "INSERT INTO offer VALUES " +
                "(DEFAULT, ?::offerState, ?, ?)";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, "pending");
        prepStatement.setInt(2, user.getUserID());
        prepStatement.setNull(3, java.sql.Types.INTEGER);
        prepStatement.executeUpdate();
    }
}
