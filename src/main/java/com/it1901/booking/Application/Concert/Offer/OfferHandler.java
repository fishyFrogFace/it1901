package com.it1901.booking.Application.Concert.Offer;

import com.it1901.booking.Application.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferHandler {

    //inserts a new offer into the database
    public static Offer newOffer(Integer userID, DatabaseHandler dbh) throws SQLException {
        String query = "INSERT INTO offer VALUES " +
                "(DEFAULT, ?::offerState, ?, ?)" +
                "RETURNING *";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, Offer.offerState.pending.toString());
        prepStatement.setInt(2, userID);
        prepStatement.setNull(3, java.sql.Types.INTEGER);
        ResultSet rs = prepStatement.executeQuery();
        return newInstance(rs);
    }

    public static Offer instanceFromConcert(Integer concertID, DatabaseHandler dbh) throws SQLException {
        String query =
                "SELECT offer.* " +
                    "FROM offer, concert " +
                    "WHERE concertID = ? " +
                    "AND concert.offerID = offer.offerID";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, concertID);
        return newInstance(prepStatement.executeQuery());
    }

    private static Offer newInstance(ResultSet rs) throws SQLException {
        rs.next();
        return OfferBuilder.offer()
                .withOfferID(rs.getInt(1))
                .withOfferSubject(Offer.offerState.valueOf(rs.getString(2)))
                .withBookerID(rs.getInt(3))
                .build();
    }
}
