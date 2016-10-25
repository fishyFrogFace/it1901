package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Artist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RequirementsController extends Controller {

    @FXML
    private ChoiceBox<String> artists;

    @FXML
    private TextField requirement;

    @FXML
    private TextArea description;

    @FXML
    private TextArea comments;

    public void onLoad() {
        try {
            artists.getItems().setAll(Artist.getArtistNames(app.getDatabaseHandler()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeRequirement(ActionEvent actionEvent) {
        String query = "INSERT INTO requirements VALUES" +
                "(DEFAULT, ?, ?, ?, ?)";
    }
}
