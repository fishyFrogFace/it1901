package com.it1901.booking.JavaFX.Controllers.Dashboard.Buttons;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.it1901.booking.Application.PriceGenerator;
import com.it1901.booking.Application.SearchHandler;

import com.it1901.booking.JavaFX.Controllers.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Paint;

public class CalculatePriceController extends Controller {

	@FXML private ChoiceBox choose = new ChoiceBox();
	@FXML private ChoiceBox artist = new ChoiceBox();
	@FXML private ListView<String> view;
	@FXML private Label error;
	int i = 0;
	
	// Compute ticketprice. 
	public void onComputeClicked(){
		try{
			error.setText("");
			String scene = choose.getValue().toString();
			int fee = PriceGenerator.getArtistFee(artist.getValue().toString(), app.getDatabaseHandler());
			int scenePrice = PriceGenerator.getScenePrice(scene, app.getDatabaseHandler());
			int maxAttendance = PriceGenerator.getSceneCapacity(scene, app.getDatabaseHandler());
			float ticketPrice = PriceGenerator.computeTicketPrice(fee, scenePrice, maxAttendance);
			
			ObservableList<String> tPrice = FXCollections.observableArrayList();
			String costsArtist = "Price of artist: " + fee + " kr";
			String costScene = "Price of stage: " + scenePrice + " kr";
			String thePrice = "Ticket price: " + ticketPrice + "Kr" ;
			tPrice.add(costsArtist);
			tPrice.add(costScene);
			tPrice.add(thePrice);
			view.setItems(tPrice);
		}
		catch(Exception e){
			error.setTextFill(Paint.valueOf("#ff3636"));
			error.setText("Please fill in all the fields");
		}

	}
	//Generate suggestion, collect artists from database. 
	@FXML
	public void generateSuggestion() throws SQLException {
		//get all artists from database
		
		if(i == 0){
			i++;
			ResultSet res = SearchHandler.getAllArtists(app.getDatabaseHandler());
			
			while (res.next()){
				artist.getItems().addAll(res.getString(1));
				
			}
		}
	}
	//Add all scenes to choicebox.
	@FXML
	public void initialize(){
		choose.getItems().addAll("Storsalen", "Klubben", "Knaus", "Selskapssiden", "Strossa");
	}
	
}
