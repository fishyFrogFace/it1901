package com.it1901.booking.Application;

public class PriceGenerator {
	
	int ticketPrice;
	int scenePrice;
	//7. Som bookingsjef ønsker jeg å kunne få generert et forslag til billettpris som tar høyde for markedsinformasjon
	//og faktiske kostnader, og få forslag til billettpris på scener med ulik størrelse slik at konserter går i økonomisk balanse.
	//artist pris, 
	PriceGenerator(){
		
	}
	public float computeTicketPrice(float cost, int seats, String scene){
		//Compute a ticketprice from a set of variables. 
		scenePrice = getScenePrice(scene);
		
		return ticketPrice;
	}
	
	int getScenePrice(String Scene){
		//method for get concert cost from scene with string. 
		return scenePrice;
	}
}
