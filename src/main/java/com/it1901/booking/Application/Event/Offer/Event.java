package com.it1901.booking.Application.Event.Offer;


import com.it1901.booking.Application.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.it1901.booking.Application.Event.Offer.EventBuilder.event;

public class Event {
	
	private final Integer eventID;
	private LocalDate startDate;
	private Integer duration;
	private Integer ticketPrice;
	private Integer ticketsSold;
	private Integer artistID;
	private Integer offerID;
	private Integer stageID;

	public Event(EventBuilder builder){
		this.eventID = builder.eventID;
		this.startDate = builder.startDate;
		this.duration = builder.duration;
		this.ticketPrice = builder.ticketPrice;
		this.artistID = builder.artistID;
		this.offerID = builder.offerID;
		this.stageID = builder.stageID;
	}

	//inserts a new event into the database
	public void newEvent(DatabaseHandler dbh) throws SQLException {
		String query = "INSERT INTO concert VALUES " +
				"(DEFAULT, ?, ?, ?, 0, ?, ?, ?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setObject(1, this.startDate);
		prepStatement.setInt(2, this.duration);
		prepStatement.setInt(3, this.ticketPrice);
		prepStatement.setInt(4, this.artistID);
		prepStatement.setInt(5, this.offerID);
		prepStatement.setInt(6, this.stageID);
		prepStatement.executeUpdate();
	}

	//fetches an event from the database and creates an event instance
	public Event fetchEvent(Integer eventID, DatabaseHandler dbh) throws SQLException {
        String query = "SELECT * FROM concert " +
                "WHERE concertID = ?";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, eventID);
        ResultSet rs = prepStatement.executeQuery();
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
}

