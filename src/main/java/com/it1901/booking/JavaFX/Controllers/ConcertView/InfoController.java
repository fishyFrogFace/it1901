package com.it1901.booking.JavaFX.Controllers.ConcertView;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    //from the old view
/*
    private VBox createLeft() {
        VBox left = new VBox();
        left.setMinWidth(150);
        left.setSpacing(10);

        try {
            ResultSet rs = SearchHandler.getConcertInformation(app.getDatabaseHandler(), eventID);
            rs.next();

            this.offerID = rs.getInt(1);
            this.date = rs.getDate(2).toLocalDate();
            this.stageID = rs.getInt(8);
            this.startingState = Offer.offerState.valueOf(rs.getString(9));
            Text artist = new Text("Artist: "+rs.getString(4));
            Text genre = new Text("Genre: "+rs.getObject(5).toString());
            Text state = new Text("State: "+rs.getObject(6).toString());
            Text date = new Text("Date: "+this.date.toString());
            Text stage = new Text("Stage: "+rs.getString(7));

            left.getChildren().addAll(artist, genre, state, date, stage);
        } catch (SQLException e) {
            errorLabel.setText("Could not connect to the database");
            e.printStackTrace();
        }
        return left;
    }
*/

    public void load(ConcertViewController concertViewController) throws SQLException {
        this.cvc = concertViewController;
        //Get event ID. 
        updateConcertInfo();
        
    }
    public void updateConcertInfo(){
    	try{
        	ResultSet rs = SearchHandler.getConcertInformation(cvc.app.getDatabaseHandler(), cvc.getConcertID());
            rs.next();
            
            artist.setText("Artist: " + rs.getString(4));
            genre.setText("Sjanger: " +  rs.getObject(5).toString());
            state.setText("Status: " + rs.getObject(6).toString());
            date.setText("Dato: " + rs.getObject(2).toString());
            stage.setText("Scene: " + rs.getString(7));
        }
        catch(SQLException e){
        	System.out.println("Error: " + e);
        }
    }
}