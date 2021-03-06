package com.it1901.booking.Application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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
        String query;
        PreparedStatement prepStatement;
        if (genre.isEmpty()) {
            query = "SELECT genre, stage.name, artist.name, startDate, duration, ticketsSold, ticketPrice " +
                    "FROM concert, stage, artist " +
                    "WHERE concert.artistID = artist.artistID " +
                    "AND concert.stageID = stage.stageID " +
                    "AND startDate < ? " +
                    "ORDER BY genre, ticketsSold";
            prepStatement = dbh.prepareQuery(query);
            prepStatement.setObject(1, today, Types.DATE);
        } else {
            query = "SELECT genre, stage.name, artist.name, startDate, duration, ticketsSold, ticketPrice " +
                    "FROM concert, stage, artist " +
                    "WHERE genre = ?::musicgenre " +
                    "AND concert.artistID = artist.artistID " +
                    "AND concert.stageID = stage.stageID " +
                    "AND startDate < ? " +
                    "ORDER BY genre, ticketsSold";
            prepStatement = dbh.prepareQuery(query);
            prepStatement.setString(1, genre);
            prepStatement.setObject(2, today, Types.DATE);
        }
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
		String query = "SELECT startDate, duration, ticketPrice, ticketsSold, stage.name " +
					   "FROM concert, artist, stage " +
					   "WHERE concert.artistID = artist.artistID " +
					   "AND concert.stageID = stage.stageID " +
					   "AND UPPER(artist.name) = UPPER(?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, artist);
		return prepStatement.executeQuery();
	}

	public static ResultSet getSceneCapacity(String scene, DatabaseHandler dbh) throws SQLException {
		String query = "SELECT maxAudience ,price " +
						"FROM stage " +
						"WHERE name = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, scene);
		return prepStatement.executeQuery();

	}

	public static ResultSet getSentOffers(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT offer.offerID, concert.offerID, concert.artistID, artist.artistID, artist.name " +
					   "FROM offer, concert, artist " +
					   "WHERE state = 'pending' " +
					   "AND offer.offerID = concert.offerID " +
					   "AND concert.artistID = artist.artistID";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		return prepStatement.executeQuery();
	}

	public static ResultSet getAssignedConcerts(int userID, DatabaseHandler dbh) throws SQLException {
        //add assigned.type,
		String query = "SELECT concert.startDate, assigned.hours, assigned.type, artist.name, stage.name " +
                "FROM assigned, concert, artist, stage " +
                "WHERE assigned.employeeID = ? " +
                "AND concert.concertID = assigned.concertID " +
                "AND artist.artistID = concert.artistID " +
                "AND stage.stageID = concert.stageID";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, userID);
		return prepStatement.executeQuery();
	}

	public static ResultSet getAllArtists(DatabaseHandler dbh) throws SQLException{
		String query = "SELECT name FROM artist ";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        return prepStatement.executeQuery();
	}

    public static Collection<String> getCollection(String parameter, String table, DatabaseHandler dbh) throws SQLException{
        try {
            String query = "SELECT " + parameter + " FROM " + table;
            PreparedStatement prepStatement = dbh.prepareQuery(query);
            ResultSet rs = prepStatement.executeQuery();
            ArrayList<String> output = new ArrayList<>();
            while(rs.next()){
                output.add(rs.getString(1));
            }

            return output;

        } catch (SQLException e) {
            e.printStackTrace();
        }return null;

    }

    public static ResultSet getConcertInformation(DatabaseHandler dbh, Integer concertID) throws SQLException {
        String query = "SELECT offer.offerID, startDate, artist.artistID, artist.name, " +
                "genre, state, stage.name, concert.stageID, offer.state, " +
                "concert.ticketPrice, concert.ticketsSold, artist.fee, artist.accomodationCost, concert.duration, stage.price " +
                "FROM concert, artist, offer, stage " +
                "WHERE concert.artistID = artist.artistID " +
                "AND concert.offerID = offer.offerID " +
                "AND concert.stageID = stage.stageID " +
                "AND concertID = ? ";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, concertID);
        return prepStatement.executeQuery();
    }

    public static ResultSet getAssignedTechs(int concertID, DatabaseHandler dbh) throws SQLException {
        String query = "SELECT employee.name, assigned.type, assigned.hours " +
                "FROM assigned, employee " +
                "WHERE assigned.concertID = ? " +
                "AND assigned.employeeID = employee.employeeID ";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, concertID);
        return prepStatement.executeQuery();
    }

    public static int getEmployeeID(String text, DatabaseHandler dbh) throws SQLException {
        String query = "SELECT employeeID FROM employee WHERE name = ? ";
        return fetchID(dbh, query, text);
    }

    public static Integer fetchID(DatabaseHandler dbh, String query, String name) throws SQLException {
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setString(1, name);
        ResultSet rs = prepStatement.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public static ResultSet getTechReq(int concertID, DatabaseHandler dbh) throws SQLException {
    	String query = "SELECT artist.name, item, description, comment " +
    				   "FROM requirement, needed, concert, artist " +
    				   "WHERE requirement.requirementID = needed.requirementID " +
    				   "AND concert.concertID = needed.concertID " +
    				   "AND concert.artistID = artist.artistID " +
    				   "AND concert.concertID = ?";
    	PreparedStatement prepStatement = dbh.prepareQuery(query);
    	prepStatement.setInt(1, concertID);
    	return prepStatement.executeQuery();
    }
}
