package com.it1901.booking.JavaFX.Controllers.ConcertView;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.it1901.booking.Application.PriceGenerator;
import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.Application.ConcertView.Info;

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
        	ResultSet rs = SearchHandler.getConcertInformation(cvc.app.getDatabaseHandler(), concertID);
            PriceGenerator p = new PriceGenerator();
        	rs.next();
            
            artist.setText("Artist: " + rs.getString(4));
            genre.setText("Sjanger: " +  rs.getObject(5).toString());
            state.setText("Status: " + rs.getObject(6).toString());
            date.setText("Dato: " + rs.getObject(2).toString());
            stage.setText("Scene: " + rs.getString(7));
            price.setText("Bilettpris: " + p.computeTicketPrice(
            		+ p.getArtistFee(rs.getString(4), cvc.app.getDatabaseHandler()),
            		+ p.getScenePrice(rs.getString(7), cvc.app.getDatabaseHandler()),
            		+ p.getSceneCapacity(rs.getString(7), cvc.app.getDatabaseHandler())) + "kr");
        }
        catch(SQLException e){
        	System.out.println("Error: " + e);
        }
    }
}