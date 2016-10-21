package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.Application.TableViewMaker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ArtistViewController extends Controller{

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
    private AnchorPane tableAnchor;

    @FXML
    private TextField searchArtistField;

    @FXML
    private Button searchArtistButton;


    public void displayArtist(String artist) {
        if (artist.equals("init")) {
            clearLabels();
            artistName.setText("Search for an artist.");
            return;
        }
        try {
            ResultSet artistRS = SearchHandler.getArtistKey(artist, app.getDatabaseHandler());
            if (artistRS.next()) {
                artistName.setText(artistRS.getString("name"));
                artistMail.setText("Email: " + artistRS.getString("email"));
                artistFee.setText("Fee: " + artistRS.getString("fee"));
                artistGenre.setText("Genre: " + artistRS.getString("genre"));
                artistCost.setText("Cost: " + artistRS.getString("accomodationCost"));
                artistSold.setText("AlbumsSold: " + artistRS.getString("albumsSold"));
                artistSpotify.setText("Spotify ID: " + artistRS.getString("spotify"));
            } else {
                clearLabels();
                artistName.setText("artist not found");
            }
            ResultSet concertRS = SearchHandler.getPreviousConcerts(artist, app.getDatabaseHandler());
            tableAnchor.getChildren().add(TableViewMaker.makeTable(concertRS, Arrays.asList("Date", "Duration (hours)", "Price", "Sold", "Stage")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchArtist() {
        displayArtist(searchArtistField.getText());
    }

    public void clearLabels() {
        artistName.setText("");
        artistMail.setText("Email: ");
        artistFee.setText("Fee: ");
        artistGenre.setText("Genre: ");
        artistCost.setText("Cost: ");
        artistSold.setText("AlbumsSold: ");
        artistSpotify.setText("Spotify ID: ");
    }

    @Override
    public void onLoad(){
        try {
            TextFields.bindAutoCompletion(searchArtistField, SearchHandler.getCollection("name", "artist", app.getDatabaseHandler()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
