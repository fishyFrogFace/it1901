package com.it1901.booking.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.it1901.booking.Application.PriceGenerator;
import com.it1901.booking.Application.SearchHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class CalculatePriceController extends Controller {

	@FXML private ChoiceBox choose = new ChoiceBox();
	@FXML private ChoiceBox artist = new ChoiceBox();
	@FXML private TextField tField;
	@FXML private ListView<String> veiw;
	@FXML private Label error;
	String tekst;
	String scene;
	int i = 0;
	
	//Compute ticketprice. 
	public void onComputeClicked() throws SQLException{
		try{
			error.setText("");
			PriceGenerator price = new PriceGenerator();
			tekst = artist.getValue().toString();
			tekst.toLowerCase();
			scene = choose.getValue().toString();
			int fee = price.getArtistFee(tekst, app.getDatabaseHandler());
			int scenePrice = price.getScenePrice(scene, app.getDatabaseHandler());
			int maxAttendance = price.getSceneCapacity(scene, app.getDatabaseHandler());
			float ticketPrice = price.computeTicketPrice(fee, scenePrice, maxAttendance);
			
			ObservableList<String> tPrice = FXCollections.observableArrayList();
			String costsArtist = "Pris på artist: " + fee + " kr";
			String costScene = "Pris for konsert på gitt scene: " + scenePrice + " kr";
			String thePrice = "Billettpris for økonomisk balanse: " + ticketPrice + "Kr" ;
			tPrice.add(costsArtist);
			tPrice.add(costScene);
			tPrice.add(thePrice);
			veiw.setItems(tPrice);
		}
		catch(Exception e){
			System.out.println("Not all values given");
			error.setTextFill(Paint.valueOf("#ff3636"));
			error.setText("Må ha noe i begge felter for å kunne gjennomføre søk");
		}

	}
	//Generate suggestion, collect artists from database. 
	@FXML
	public void generateSuggestion() throws SQLException{
		//get all artists friom database
		
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
