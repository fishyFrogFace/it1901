package com.it1901.booking.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class ArtistInfo {

	private DecimalFormat formatter = new DecimalFormat("#,###");

	public String getArtistInfo(String artist, DatabaseHandler dbh) throws SQLException {
		ResultSet rs = SearchHandler.getArtistKey(artist, dbh);
		if (rs.next()) {
			String genre = rs.getString("genre").substring(0, 1).toUpperCase() + rs.getString("genre").substring(1);
			int spotify = rs.getInt("spotify");
			int albumsSold = rs.getInt("albumsSold");
			String concerts = rs.getString("concerts");
			return ("Genre: " + genre + "\nSpotify: " + this.formatter.format((long) spotify)
					+ "\nAlbums sold: " + this.formatter.format((long) albumsSold) + "\n\nConcerts info: " + concerts);
		} else {
			return ("Could not find artist.");
		}
	}
	
	public String getPrevConcerts(String artist, DatabaseHandler dbh) throws SQLException {
		ResultSet rs = SearchHandler.getPreviousConcerts(artist, dbh);
		if (rs.next()) {
			int amount = rs.getRow();
			int duration = rs.getInt("duration");
			int ticketsSold = rs.getInt("ticketsSold");
			int ticketPrice = rs.getInt("ticketPrice");
			return ("Amount of previous concerts: " + amount +
					"\nDuration: " + duration +
					"\nTickets sold: " + ticketsSold +
					"\nTicket price: " + ticketPrice);
		} else {
			return ("Artist/band has not had a concert here before.");
		}
	}
}
