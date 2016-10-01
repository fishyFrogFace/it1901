package com.it1901.booking.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriceGenerator {
	
	int ticketPrice;
	int scenePrice;
	int capacity;
	int totalCost;
	ArrayList<Integer> sceneInfo = new ArrayList<>();	
	static DatabaseHandler dtb;
	//7. Som bookingsjef ønsker jeg å kunne få generert et forslag til billettpris som tar høyde for markedsinformasjon
	//og faktiske kostnader, og få forslag til billettpris på scener med ulik størrelse slik at konserter går i økonomisk balanse.
	//artist pris. 
	PriceGenerator(){
		
	}
	public float computeTicketPrice(float totalCost, int seats, String scene){
		//Compute a ticketprice from a set of variables. 
		
		
		return ticketPrice;
	}
	
	
	//Get capacity of scene, and totalcost from a scene. 
	ArrayList<Integer> getSceneCapacity(String scene, DatabaseHandler dbh) throws SQLException{
		ResultSet res = SearchHandler.getSceneCapacity(scene, dbh);
		System.out.println(res);
		while (res.next()){
			sceneInfo.add(res.getInt(1));
			sceneInfo.add(res.getInt(2));
			}
		
		return sceneInfo; 
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
