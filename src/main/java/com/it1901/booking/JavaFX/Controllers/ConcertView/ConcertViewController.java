package com.it1901.booking.JavaFX.Controllers.ConcertView;

import com.it1901.booking.Application.Concert.Offer.Concert;
import com.it1901.booking.Application.Concert.Offer.ConcertHandler;
import com.it1901.booking.Application.Concert.Offer.Offer;
import com.it1901.booking.Application.Concert.Offer.OfferHandler;
import com.it1901.booking.JavaFX.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class ConcertViewController extends Controller {
    Concert concert;
    Offer offer;

    @FXML
    private InfoController infoController;

    @FXML
    private StatusController statusController;

    @FXML
    private TechController techController;

    @Override
    public void onLoad(Integer concertID) {
        try {
            this.concert = ConcertHandler.fetchConcert(concertID, app.getDatabaseHandler());
            this.offer = OfferHandler.instanceFromConcert(concertID, app.getDatabaseHandler());
            System.out.println(app.getUser().getUserType());
        } catch (SQLException e) {
            //TODO add message to user here
            e.printStackTrace();
        }
        try {
			infoController.load(this);
            statusController.load(this);
            techController.load(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //add rest of nested controllers
    }

    public void goBack(ActionEvent actionEvent) {
        app.makeCalendar(concert.getStartDate());
    }
    //Update information when clicking on infotab.
    public void updateInfoTab(){
    	infoController.updateConcertInfo(concert.getConcertID());
    }
    public void updateTechTab(){
        techController.getTechTable();
    }
}
