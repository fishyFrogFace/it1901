package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Event;
import com.it1901.booking.Application.Offer.OfferHandler;
import com.it1901.booking.Application.Status;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;

public class OfferController extends Controller {

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
    private ObservableList<String> stageOptions =
            FXCollections.observableArrayList(
                    "Scene 1",
                    "Scene 2",
                    "Scene 3",
                    "Scene 4",
                    "Scene 5"
            );
    @FXML
    private ChoiceBox stage = new ChoiceBox(stageOptions);

    public String getStage(ChoiceBox<String> stage) {
        return stage.getSelectionModel().getSelectedItem();
    }



    public void onSubmitClick() throws SQLException{
        OfferHandler ofh = new OfferHandler();
        //ofh.saveOffer(dbh, app.getUser());
        Event event = new Event(1, band.getText(), getStage(stage), Integer.parseInt(ticketprice.getText()), Status.PROPOSED);
        event.saveEvent(LocalDate.now().plusDays(7), Integer.parseInt(ticketprice.getText()), band.getText(), 1, getStage(stage), app.getDatabaseHandler());
        System.out.println("Offer sent!");
        app.makeDash();
        //placeholder values: - need more db queries to get IDs for stage, band, offer

    }
}
