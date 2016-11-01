package com.it1901.booking.Application.Concert.Offer;


import com.it1901.booking.Application.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class Concert {
	
	private final Integer concertID;
	private LocalDate startDate;
	private Integer duration;
	private Integer ticketPrice;
	private Integer ticketsSold;
	private Integer artistID;
	private Integer offerID;
	private Integer stageID;

	public Concert(ConcertBuilder builder){
		this.concertID = builder.concertID;
		this.startDate = builder.startDate;
		this.duration = builder.duration;
		this.ticketPrice = builder.ticketPrice;
		this.ticketsSold = builder.ticketsSold;
		this.artistID = builder.artistID;
		this.offerID = builder.offerID;
		this.stageID = builder.stageID;
	}


	//inserts a new event into the database
	public void newConcert(DatabaseHandler dbh) throws SQLException {
		String query = "INSERT INTO concert VALUES " +
				"(DEFAULT, ?, 5, 0, ?, ?, ?, ?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setObject(1, this.startDate, Types.DATE);
		prepStatement.setInt(2, this.ticketPrice);
		prepStatement.setInt(3, this.artistID);
		prepStatement.setInt(4, this.offerID);
		prepStatement.setInt(5, this.stageID);

		prepStatement.executeUpdate();
	}

    public Integer getConcertID() {
        return this.concertID;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public Integer getStageID() {
        return this.stageID;
    }

    public Integer getTicketsSold()	{	return this.ticketsSold;	}

	public Integer getStageSize(DatabaseHandler dbh) throws SQLException {
		String query = "Select stage.maxAudience " +
				"FROM stage, concert " +
				"WHERE stage.stageID = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setInt(1, this.stageID);
		ResultSet rs = prepStatement.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

    public String getStageName(DatabaseHandler dbh) throws SQLException {
        String query = "SELECT stage.name " +
                "FROM stage, concert " +
                "WHERE stage.stageID = ?";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, this.stageID);
        ResultSet rs = prepStatement.executeQuery();
        rs.next();
        return rs.getString(1);
    }
}

