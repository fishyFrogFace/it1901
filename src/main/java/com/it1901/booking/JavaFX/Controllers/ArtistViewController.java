package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.Application.TableViewMaker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private AnchorPane tableAnchor;

    public void displayArtist(String artist) {

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
                System.out.println("artist not found");
            }
            ResultSet concertRS = SearchHandler.getPreviousConcerts(artist, app.getDatabaseHandler());
            tableAnchor.getChildren().add(TableViewMaker.makeTable(concertRS, Arrays.asList("ID", "Duration", "Price", "Sold", "Stage")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Need get___(artist) methods first
    }
}
