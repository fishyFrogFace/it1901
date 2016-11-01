package com.it1901.booking.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriceGenerator {
	
	int ticketPrice;
	int artistPrice;
	int scenePrice;
	int capacity;
	int workHours = 4;
	int pay = 150;
	int rentalEquipment = 10000;
		
	static DatabaseHandler dtb;
	//7. Som bookingsjef ønsker jeg å kunne få generert et forslag til billettpris som tar høyde for markedsinformasjon
	//og faktiske kostnader, og få forslag til billettpris på scener med ulik størrelse slik at konserter går i økonomisk balanse.
	//artist pris. 
	
	public float computeTicketPrice(int fee, int scenePrice, int max) throws SQLException{
		// Compute a ticketprice from a set of variables. 
		int totalcost = fee + scenePrice + (workHours * pay) + rentalEquipment;
		int ticketPrice = ((totalcost/max) + 150);
		ticketPrice -= ticketPrice % 50;
		return ticketPrice;
	}
	public int getArtistPrice(){
		return artistPrice;
	}
	public int getScenePrice(){
		return scenePrice;
	}
	public int getTicketPrice(){
		return ticketPrice;
	}
	
	//Get capacity of scene, and totalcost from a scene. 
	public int getSceneCapacity(String scene, DatabaseHandler dbh) throws SQLException{
		ResultSet res = SearchHandler.getSceneCapacity(scene, dbh);
		while (res.next()){
			capacity = res.getInt(1);
			}
		return capacity; 
		}
	//Get Price of arranging concert at scene
	public int getScenePrice(String scene, DatabaseHandler dbh) throws SQLException{
		ResultSet res = SearchHandler.getSceneCapacity(scene, dbh);
		while (res.next()){
			scenePrice = res.getInt(2);
		}
		return scenePrice;
	}
	//Get price of artist. 
	public int getArtistFee(String artist, DatabaseHandler dbh) throws SQLException{
		ResultSet res = SearchHandler.getArtistKey(artist, dbh);
		while (res.next()){
			artistPrice = res.getInt(7);
		}
		return artistPrice;
	}
}
