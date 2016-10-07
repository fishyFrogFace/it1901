package com.it1901.booking.JavaFX.Controllers;

import java.awt.ScrollPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class BookingsjefOfferResponseController extends Controller implements Initializable{
	@FXML
    private Label title;
	@FXML 
	private FlowPane btnContainer;
	@FXML
	private ScrollPane scroll;
	
	
		
	
	public void createButton(String label) throws IOException{
		Button btn = new Button(label);
		FlowPane testPane = new FlowPane();
		btn.setOnAction(null);
		btn.setPrefSize(75, 25);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		title.setText("Test");
		try {
			String[] test2 = {"Accept", "Decline"};

			for (String value : test2){
				createButton(value);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
