package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OfferController extends Controller implements Initializable {

    @FXML
    private TextField band;
    //@FXML
    //private TextField stage;
    //@FXML
    //private TextField duration;
    @FXML
    private TextField ticketprice;
    //@FXML
    //private TextField band;

    @FXML
    private ChoiceBox<Stage.stages> stages;

    public String getStage(ChoiceBox<String> stage) {
        return stage.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        stages.getItems().setAll(Stage.stages.values());
    }

    public void onSubmitClick() throws SQLException{
        //ofh.saveOffer(dbh, app.getUser());
        //Event event = new Event(1, band.getText(), getStage(stage), Integer.parseInt(ticketprice.getText()), Offer.offerState.pending);
        //event.saveEvent(LocalDate.now().plusDays(7), Integer.parseInt(ticketprice.getText()), band.getText(), 1, getStage(stage), app.getDatabaseHandler());
        System.out.println("Offer sent!");
        app.makeDash();
        //placeholder values: - need more db queries to get IDs for stage, band, offer

    }
}
