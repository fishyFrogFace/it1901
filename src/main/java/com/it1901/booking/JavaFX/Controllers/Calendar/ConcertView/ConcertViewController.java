package com.it1901.booking.JavaFX.Controllers.Calendar.ConcertView;

import com.it1901.booking.Application.Concert.Offer.Concert;
import com.it1901.booking.Application.Concert.Offer.ConcertHandler;
import com.it1901.booking.Application.Concert.Offer.Offer;
import com.it1901.booking.Application.Concert.Offer.OfferHandler;
import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.JavaFX.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ConcertViewController extends Controller {
    Concert concert;
    Offer offer;

    @FXML
    private InfoController infoController;

    @FXML
    private StatusController statusController;

    @FXML
    private TechController techController;

    @FXML
    private TechreqController techreqController;

    @FXML
    private ReportController reportController;

    @Override
    public void onLoad(Integer concertID) {
        try {
            this.concert = ConcertHandler.fetchConcert(concertID, app.getDatabaseHandler());
            this.offer = OfferHandler.instanceFromConcert(concertID, app.getDatabaseHandler());
            randomizeResults(this.concert, app.getDatabaseHandler());
            System.out.println(app.getUser().getUserType());
        } catch (SQLException e) {
            //TODO add message to user here
            e.printStackTrace();
        }
        try {
			infoController.load(this);
            statusController.load(this);
            techController.load(this);
            techreqController.load(this);
            reportController.load(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    private void randomizeResults(Concert concert, DatabaseHandler dbh) throws SQLException  {
        if(this.offer.getState() == Offer.offerState.booked && concert.getTicketsSold() == 0 && concert.getStartDate().isBefore(LocalDate.now())){
            int random = (int) Math.floor(Math.random()*concert.getStageSize(dbh));
            String query = "UPDATE concert SET ticketsSold = " + random   +
                    "WHERE concertID = " + concert.getConcertID();
            PreparedStatement prepStatement = dbh.prepareQuery(query);
            prepStatement.executeUpdate();
        }

    }
}
