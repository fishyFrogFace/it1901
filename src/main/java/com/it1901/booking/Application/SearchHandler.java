package com.it1901.booking.Application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class SearchHandler {

    //fetches artist name and key information (streaming etc.)
    public static ResultSet getArtistKey(String artist, DatabaseHandler dbh) throws SQLException {
        String query = "SELECT artist.artistID, name, genre, spotify, email, albumsSold, fee, accomodationCost, concerts " +
                "FROM artist, artistinfo " +
                "WHERE UPPER(artist.name) = UPPER(?) " +
                "AND artistinfo.artistID = artist.artistID";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, artist);
        return prepStatement.executeQuery();
    }

    //fetches stage and ticketsSold for concerts in a genre
    public static ResultSet eventsByGenre(String genre, DatabaseHandler dbh) throws SQLException {
        LocalDate today = LocalDate.now();
        String query = "SELECT concert.concertID, genre, ticketsSold, stage.name, artist.name " +
                "FROM concert, stage, artist " +
                "WHERE genre = ?::musicgenre " +
                "AND concert.artistID = artist.artistID " +
                "AND concert.stageID = stage.stageID " +
                "AND startDate < ? " +
                "ORDER BY genre, ticketsSold";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, genre);
        prepStatement.setObject(2, today, Types.DATE);
        return prepStatement.executeQuery();
    }

    //fetches artists who have had concerts on a stage earlier
    public static ResultSet bookedInPast(String stage, DatabaseHandler dbh) throws SQLException {
        LocalDate today = LocalDate.now();
        String query = "SELECT artist.name, startDate, stage.name " +
                "FROM artist, stage, concert " +
                "WHERE startDate < ? " +
                "AND stage.name = ? " +
                "AND concert.artistID = artist.artistID " +
                "AND concert.stageID = stage.stageID " +
                "ORDER BY startDate";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setObject(1, today);
        prepStatement.setString(2, stage);
        return prepStatement.executeQuery();
    }
    
    public static ResultSet previousConcerts(String artist, DatabaseHandler dbh) throws SQLException {
        LocalDate today = LocalDate.now();
        String query = "SELECT startDate " +
                "FROM artist, concert " +
                "WHERE startDate > ? " +
                "AND artist.artistID = concert.artistID" + 
                "AND UPPER(artist.name) = UPPER(?)";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setObject(1, today);
        prepStatement.setString(2, artist);
        return prepStatement.executeQuery();
    }

	public static ResultSet getPreviousConcerts(String artist, DatabaseHandler dbh) throws SQLException {
		String query = "SELECT concertID, duration, ticketPrice, ticketsSold, stageID " +
					   "FROM concert, artist " + 
					   "WHERE concert.artistID = artist.artistID " + 
					   "AND UPPER(artist.name) = UPPER(?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, artist);
		return prepStatement.executeQuery();
	}
	public static ResultSet getSceneCapacity(String scene, DatabaseHandler dbh) throws SQLException {
		String query = "SELECT maxAudience" + 
						"FROM stage" +
						"WHERE name = " + scene;
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, scene);
		return prepStatement.executeQuery();
		
	}
}
