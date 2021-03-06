package com.it1901.booking.JavaFX.Controllers.Calendar.ConcertView;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.PriceGenerator;
import com.it1901.booking.Application.SearchHandler;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InfoController {

    ConcertViewController cvc;
    @FXML
    private Text artist;

    @FXML
    private Text genre;

    @FXML
    private Text state;

    @FXML
    private Text date;

    @FXML
    private Text stage;
    
    @FXML
    private Text price;

    public void load(ConcertViewController concertViewController) throws SQLException {
        this.cvc = concertViewController;
        updateConcertInfo(cvc.concert.getConcertID());
        
    }
    public void updateConcertInfo(Integer concertID){
    	try{
    		
    		DatabaseHandler dbh = cvc.app.getDatabaseHandler();
        	ResultSet rs = SearchHandler.getConcertInformation(dbh, concertID);rs.next();
            String ar = rs.getString(4);
            String sc = rs.getString(7);
            Integer tp = rs.getInt(10);
            artist.setText("Artist: " + ar);
            genre.setText("Genre: " +  rs.getObject(5).toString());
            state.setText("Status: " + rs.getObject(6).toString());
            date.setText("Date: " + rs.getObject(2).toString());
            stage.setText("Scene: " + sc);
            price.setText("Ticket-Price: " + tp.toString() + "kr");
        }
        catch(SQLException e){
        	System.out.println("Error: " + e);
        }
    }
   
}