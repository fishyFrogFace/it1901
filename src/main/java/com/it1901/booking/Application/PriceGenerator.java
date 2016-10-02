package com.it1901.booking.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriceGenerator {
	
	int ticketPrice;
	int artistPrice = 20000;
	int scenePrice;
	int capacity;
		
	static DatabaseHandler dtb;
	//7. Som bookingsjef ønsker jeg å kunne få generert et forslag til billettpris som tar høyde for markedsinformasjon
	//og faktiske kostnader, og få forslag til billettpris på scener med ulik størrelse slik at konserter går i økonomisk balanse.
	//artist pris. 
	PriceGenerator(){
		
	}
	public float computeTicketPrice(String scene) throws SQLException{
		//Compute a ticketprice from a set of variables. 
		getSceneCapacity(scene, dtb);
		System.out.println("Kapasitet " + capacity);
		System.out.println("Pris på scene: " + scenePrice);
		
		return ticketPrice;
	}
	
	
	//Get capacity of scene, and totalcost from a scene. 
	public int getSceneCapacity(String scene, DatabaseHandler dbh) throws SQLException{
		ResultSet res = SearchHandler.getSceneCapacity(scene, dbh);
		System.out.println(res);
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
	public int getArtistFee(String artist, DatabaseHandler dbh){
		return artistPrice;
	}
	
	
	public static void main(String[] args) throws SQLException {
		dtb = new DatabaseHandler("org.postgresql.Driver",
                "jdbc:postgresql://52.40.176.177:5432/booking",
                "team",
                "it1901");
		
		PriceGenerator pr = new PriceGenerator();
		pr.getSceneCapacity("Storsalen", dtb);
		
		
		
	}
}
