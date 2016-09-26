package com.it1901.booking.JavaFX.Controllers;



import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.SearchHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class GenreSearchController extends Controller implements Initializable {
	
	@FXML private TextField searchField;
	@FXML private Label errorLabel;
	@FXML private ListView<String> list;
	
	DatabaseHandler dtb;
	private String tekst;
	
	//When searchbutton clicked. 
	@FXML
	public void onSearchClicked() throws SQLException{
		System.out.println("serr");
		errorLabel.setText("");
		list.getItems().clear();
		
		try{
			tekst = searchField.getText();
			System.out.println("searchword: " + tekst);
			SearchHandler src = new SearchHandler();
			ResultSet res = src.eventsByGenre(tekst, dtb);
			displayToScreen(res);
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
			errorLabel.setTextFill(Paint.valueOf("#ff3636"));
			errorLabel.setText("No genre of this type in database");
			
		}
	}
	
	//Making list veiw for displaying concerts by genre. 
	public void displayToScreen(ResultSet res) throws SQLException{
		ObservableList<String> genres = FXCollections.observableArrayList();
		String genreSearch = "";
		String id = "";
		String genre = "";
		String ticketSales = "";
		String scene = "";
		int check = 0;

		//if more concerts by this genre. 
		while (res.next()){
				check ++;
				System.out.println("search");
				id = res.getString(1);
				genre = res.getString(2);
				ticketSales = res.getString(3);
				scene = res.getString(4);
				genreSearch = "ID: " + id + " 	Genre: " + genre + " 	Ticket-Sales: " + ticketSales + " 	Scene: " + scene;   
				genres.add(genreSearch);
				list.setItems(genres);
				
				
			}
		//If no values in database for this genre
		if (check == 0){
			genres.add("No concert in database of this genre");
			list.setItems(genres);
		}
		}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dtb = new DatabaseHandler("org.postgresql.Driver",
                "jdbc:postgresql://52.40.176.177:5432/booking",
                "team",
                "it1901");
		
	}
}
