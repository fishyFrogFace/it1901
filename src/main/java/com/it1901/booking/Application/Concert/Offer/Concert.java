package com.it1901.booking.Application.Concert.Offer;


import com.it1901.booking.Application.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import static com.it1901.booking.Application.Concert.Offer.ConcertBuilder.event;

public class Concert {
	
	private final Integer eventID;
	private LocalDate startDate;
	private Integer duration;
	private Integer ticketPrice;
	private Integer ticketsSold;
	private Integer artistID;
	private Integer offerID;
	private Integer stageID;

	public Concert(ConcertBuilder builder){
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
				"(DEFAULT, ?, 5, ?, 0, ?, ?, ?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setObject(1, this.startDate);
		prepStatement.setInt(2, this.ticketPrice);
		prepStatement.setInt(3, this.artistID);
		prepStatement.setInt(4, this.offerID);
		prepStatement.setInt(5, this.stageID);
		prepStatement.executeUpdate();
	}

    public Integer getEventID() {
        return this.eventID;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }
}

