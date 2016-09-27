package com.it1901.booking.JavaFX.Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Table {
	private final SimpleStringProperty rName;
	private final SimpleStringProperty rGenre;
	private final SimpleStringProperty rSpotify;
	private final SimpleIntegerProperty rAlbumsSold;
	private final SimpleStringProperty rConcerts;
	
	public Table(String sName, String sGenre, String sSpotify, Integer sAlbumsSold, String sConcerts){
		//Defining the columns
		this.rName = new SimpleStringProperty(sName); 
		this.rGenre = new SimpleStringProperty(sGenre);
		this.rSpotify = new SimpleStringProperty(sSpotify); 
		this.rAlbumsSold = new SimpleIntegerProperty(sAlbumsSold);
		this.rConcerts = new SimpleStringProperty(sConcerts);
	}
	
	//Getters and setters for the columns
	
	public String getRName(){
		return rName.get();
	}
	public void setRName(String v){
		rName.set(v);
	}
	
	public String getRGenre(){
		return rGenre.get();
	}
	public void setRGenre(String v){
		rGenre.set(v);
	}
	
	public String getRSpotify(){
		return rSpotify.get();
	}
	public void setRSpotify(String v){
		rSpotify.set(v);
	}
	
	public Integer getRAlbumsSold(){
		return rAlbumsSold.get();
	}
	public void setRAlbumsSold(Integer v){
		rAlbumsSold.set(v);
	}
	public String getRConcerts(){
		return rConcerts.get();
	}
	public void setRConcerts(String v){
		rConcerts.set(v);;
	}
}

