package com.it1901.booking.JavaFX.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController extends Controller implements Initializable{
	//Create table
	@FXML
	TableView<Table> tableID;
	//Create columns based on what's in table.java
	@FXML
	TableColumn<Table, String> iName;
	@FXML
	TableColumn<Table, String> iGenre;
	@FXML
	TableColumn<Table, String> iSpotify;
	@FXML
	TableColumn<Table, String> iAlbumsSold;
	@FXML
	TableColumn<Table, String> iConcerts;
	
	
	final ObservableList<Table> data = FXCollections.observableArrayList(
			//Creating table entries
			new Table("The Beatles", "Pop", "1 Billion", "1000", "Samfundet")
	);

	public void initialize() {
		iName.setCellValueFactory(new PropertyValueFactory<Table, String>("rName"));
		iGenre.setCellValueFactory(new PropertyValueFactory<Table, String>("rGenre"));
		iSpotify.setCellValueFactory(new PropertyValueFactory<Table, String>("rSpotify"));
		iAlbumsSold.setCellValueFactory(new PropertyValueFactory<Table, String>("rAlbumsSold"));
		iConcerts.setCellValueFactory(new PropertyValueFactory<Table, String>("rConcerts"));
		
		tableID.setItems(data);
	}
}
