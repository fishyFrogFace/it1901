package com.it1901.booking.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.it1901.booking.Application.PriceGenerator;
import com.it1901.booking.Application.SearchHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CalculatePriceController extends Controller {

	@FXML private ChoiceBox choose = new ChoiceBox();
	@FXML private ChoiceBox artist = new ChoiceBox();
	@FXML private TextField tField;
	@FXML private ListView<String> veiw;
	String tekst;
	String scene;
	
	//Compute ticketprice. 
	public void onComputeClicked() throws SQLException{
		PriceGenerator price = new PriceGenerator();
		tekst = artist.getValue().toString();
		tekst.toLowerCase();
		scene = choose.getValue().toString();
		int fee = price.getArtistFee(tekst, app.getDatabaseHandler());
		int scenePrice = price.getScenePrice(scene, app.getDatabaseHandler());
		int maxAttendance = price.getSceneCapacity(scene, app.getDatabaseHandler());
		float ticketPrice = price.computeTicketPrice(fee, scenePrice, maxAttendance);
		System.out.println("ticketprice:" + ticketPrice);
		ObservableList<String> tPrice = FXCollections.observableArrayList();
		String costsArtist = "Pris på artist: " + fee + " kr";
		String costScene = "Andre utgifter: " + scenePrice + " kr";
		String thePrice = "Billettpris for økonomisk balanse: " + ticketPrice + "Kr" ;
		tPrice.add(costsArtist);
		tPrice.add(costScene);
		tPrice.add(thePrice);
		veiw.setItems(tPrice);

	}
	//Generate suggestion, collect artists from database. 
	public void generateSuggestion() throws SQLException{
		//get all artists friom database
		System.out.println("ill generate suggestion for you");
		ResultSet res = SearchHandler.getAllArtists(app.getDatabaseHandler());
		
		while (res.next()){
			
			artist.getItems().addAll(res.getString(1));
			System.out.println("e");
		}
	}
	//Add all scenes to choicebox.
	public void initialize(){
		choose.getItems().addAll("Storsalen", "Klubben", "Knaus", "Selskapssiden", "Strossa");
		System.out.println("init");
		
		
	}
	
}
