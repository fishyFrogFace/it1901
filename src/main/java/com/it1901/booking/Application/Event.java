package com.it1901.booking.Application;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Event {
	
	private int id;
	private String band;
	private String scene;
	private float price;
	Status status;
	
	
	
	public Event(int id, String band, String scene, float price, Status status){
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

	//inserts a new event
    //FIXME is not syncronized with event class
	//private void saveEvent(
	//		LocalDate date, int ticketPrice,
	//		int artistID, int offerID, int stageID, DatabaseHandler dbh) throws SQLException {
	//	String query = "INSERT INTO concert VALUES " +
	//			"(DEFAULT, ?, ?, ?, 0, ?, ?, ?)";
	//	PreparedStatement prepStatement = dbh.prepareQuery(query);
	//	prepStatement.setObject(1, date);
		//prepStatement.setInt(2, duration);
	//	prepStatement.setInt(2, ticketPrice);
	//	prepStatement.setInt(3, artistID);
	//	prepStatement.setInt(4, offerID);
	//	prepStatement.setInt(5, stageID);
	//	prepStatement.executeUpdate();
	//}

	//inserts a new event
	//FIXME is not syncronized with event class
	public void saveEvent(
			LocalDate date, int ticketPrice,
			String artist, int offerID, String stage, DatabaseHandler dbh) throws SQLException {
		String query = "INSERT INTO concert VALUES " +
				"(DEFAULT, ?, ?, ?, 0, ?, ?, ?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setObject(1, date);
		//prepStatement.setInt(2, duration);
		prepStatement.setInt(2, ticketPrice);
		prepStatement.setString(3, artist);
		prepStatement.setInt(4, offerID);
		prepStatement.setString(5, stage);
		prepStatement.executeUpdate();
	}
}

