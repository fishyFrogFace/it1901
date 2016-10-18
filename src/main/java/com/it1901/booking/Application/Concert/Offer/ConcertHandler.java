package com.it1901.booking.Application.Concert.Offer;

import com.it1901.booking.Application.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import static com.it1901.booking.Application.Concert.Offer.ConcertBuilder.event;

public class ConcertHandler {
    //fetches an event from the database and creates an event instance
    public static Concert fetchConcert(Integer concertID, DatabaseHandler dbh) throws SQLException {
        String query = "SELECT * FROM concert " +
                "WHERE concertID = ?";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, concertID);
        ResultSet rs = prepStatement.executeQuery();
        rs.next();
        return event()
                .withEventID(rs.getInt(1))
                .withStartDate(rs.getDate(2).toLocalDate())
                .withDuration(rs.getInt(3))
                .withTicketPrice(rs.getInt(4))
                .withTicketsSold(rs.getInt(5))
                .withArtistID(rs.getInt(6))
                .withOfferID(rs.getInt(7))
                .withStageID(rs.getInt(8))
                .build();
    }

    public static Boolean checkAvailable(
            Integer stageID,
            LocalDate date,
            DatabaseHandler dbh) throws SQLException {
        String query = "SELECT concertID, offer.offerID FROM concert, offer " +
                "WHERE startDate = ? " +
                "AND stageID = ? " +
                "AND concert.offerID = offer.offerID " +
                "AND state = 'booked'";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setObject(1, date, Types.DATE);
        prepStatement.setInt(2, stageID);
        ResultSet rs = prepStatement.executeQuery();
        if (rs.next()) {
            return false;
        } else {
            return true;
        }
    }
}
