package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.SearchHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private TableView<List<String>> artistConcerts;

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
            /*ResultSet concertRS = SearchHandler.getPreviousConcerts(artist, dbh);
            if (concertRS.next()) {
                List row = new ArrayList();
                row.add(concertRS.getString("concertID"));
                row.add(concertRS.getString("stageID"));
                row.add(concertRS.getString("ticketPrice"));
                row.add(concertRS.getString("ticketsSold"));
                row.add(concertRS.getString("duration"));
                artistConcerts.getItems().add(row);
            } else {
                System.out.println("no concerts found");
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Need get___(artist) methods first
    }
}
