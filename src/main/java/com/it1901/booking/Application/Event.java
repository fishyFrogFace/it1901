package com.it1901.booking.Application;

public class Event {
	
	enum Status{
		Proposed, accepted, denied;
	} 
	
	private int id;
	private String band;
	private String scene;
	private float price;
	
	
	
	Event(int id, String band, String scene, float price){
		this.id = id;
		this.band = band;
		this.scene = scene;
		this.price = price;
		
		
	}

}
