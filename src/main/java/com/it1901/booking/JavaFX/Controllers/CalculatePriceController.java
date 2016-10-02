package com.it1901.booking.JavaFX.Controllers;

import java.sql.SQLException;

import com.it1901.booking.Application.PriceGenerator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CalculatePriceController extends Controller {

	@FXML private ChoiceBox choose = new ChoiceBox();
	@FXML private TextField tField;
	@FXML private ListView<String> veiw;
	String tekst;
	String scene;
	//Compute ticketprice. 
	public void onComputeClicked() throws SQLException{
		PriceGenerator price = new PriceGenerator();
		tekst = tField.getText();
		scene = choose.getValue().toString();
		int fee = price.getArtistFee(tekst, app.getDatabaseHandler());
		int scenePrice = price.getScenePrice(scene, app.getDatabaseHandler());
		int maxAttendance = price.getSceneCapacity(scene, app.getDatabaseHandler());
		float ticketPrice = price.computeTicketPrice(fee, scenePrice, maxAttendance);
		System.out.println("ticketprice:" + ticketPrice);
		ObservableList<String> tPrice = FXCollections.observableArrayList();
		String thePrice = "Calculated ticketprice: " + ticketPrice;
		tPrice.add(thePrice);
		veiw.setItems(tPrice);

	}
//	//Generate suggestion, collect artists from database. 
//	public void generateSuggestion(){
//		System.out.println("ill generate suggestion for you");
//		//tekst = tField.getText();
//		System.out.println(tekst);
//		
//	}
	//Add all scenes to choicebox.
	public void initialize(){
		choose.getItems().addAll("Storsalen", "Klubben", "Knaus", "Selskapssiden", "Strossa");
		
		
	}
	
}
