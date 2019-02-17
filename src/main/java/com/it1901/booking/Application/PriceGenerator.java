package com.it1901.booking.Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceGenerator {

	private static int artistPrice;
	private static int scenePrice;
	private static int capacity;
	private static final int workHours = 4;
	private static final int pay = 150;
	private static final int rentalEquipment = 10000;
		
	//7. Som bookingsjef ønsker jeg å kunne få generert et forslag til billettpris som tar høyde for markedsinformasjon
	//og faktiske kostnader, og få forslag til billettpris på scener med ulik størrelse slik at konserter går i økonomisk balanse.
	//artist pris. 
	
	public static float computeTicketPrice(int fee, int scenePrice, int max){
		// Compute a ticketprice from a set of variables. 
		int totalcost = fee + scenePrice + (workHours * pay) + rentalEquipment;
		return ((totalcost/max) + 150);
	}
	
	//Get capacity of scene, and total cost from a scene.
	public static int getSceneCapacity(String scene, DatabaseHandler dbh) throws SQLException{
		ResultSet res = SearchHandler.getSceneCapacity(scene, dbh);
		while (res.next()){
			capacity = res.getInt(1);
			}
		return capacity; 
		}

	//Get Price of arranging concert at scene
	public static int getScenePrice(String scene, DatabaseHandler dbh) throws SQLException {
		ResultSet res = SearchHandler.getSceneCapacity(scene, dbh);
		while (res.next()){
			scenePrice = res.getInt(2);
		}
		return scenePrice;
	}

	//Get price of artist. 
	public static int getArtistFee(String artist, DatabaseHandler dbh) throws SQLException {
		ResultSet res = SearchHandler.getArtistKey(artist, dbh);
		while (res.next()){
			artistPrice = res.getInt(7);
		}
		return artistPrice;
	}
}