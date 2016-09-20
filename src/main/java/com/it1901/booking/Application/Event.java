package com.it1901.booking.Application;


public class Event {
	
	private int id;
	private String band;
	private String scene;
	private float price;
	Status status;
	
	
	
	Event(int id, String band, String scene, float price, Status status){
		this.id = id;
		this.band = band;
		this.scene = scene;
		this.price = price;
		this.status = status;
		
		
	}
	public void SetStatus(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}
	
	public static void main(String[] args) {
		//Set new event example --> Will delete this soon
		Event eve =  new Event(3, "Adersbandet", "Storsalen", 15000, Status.PROPOSED);
		System.out.println(eve.getStatus());
		eve.SetStatus(Status.DECLINED);
		System.out.println(eve.getStatus());
	}
}

