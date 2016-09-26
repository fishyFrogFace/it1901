package com.it1901.booking.JavaFX;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GetInformationApp extends Application{
	
	Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		window = arg0;
		Parent root = FXMLLoader.load(getClass().getResource("/InformationByGenre.fxml"));
		window.setTitle("Genre Search");
		window.setScene(new Scene(root, 400, 400));
		window.show();
		
	}


	
}
