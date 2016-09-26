package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.ArtistInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ArtistViewController extends Controller {

    @FXML
    private Label artistName;

    @FXML
    private Label artistMail;

    @FXML
    private Label artistGenre;

    @FXML
    private Label artistSpotify;

    @FXML
    private Label artistFee;

    @FXML
    private Label artistCost;

    @FXML
    private Label artistSold;

    @FXML
    private ListView artistConcerts;

    public void displayArtist(String artist) {
        //Need get___(artist) methods first
    }


}
