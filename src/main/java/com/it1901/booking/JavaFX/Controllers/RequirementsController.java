package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Artist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
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

    //TODO add messageText

    public void onLoad() {
        try {
            artists.getItems().setAll(Artist.getArtistNames(app.getDatabaseHandler()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeRequirement(ActionEvent actionEvent) {
        String query = "INSERT INTO requirement VALUES" +
                "(DEFAULT, ?, ?, ?)";
        try {
            PreparedStatement prepStatement = app.getDatabaseHandler().prepareQuery(query);
            prepStatement.setString(1, artists.getValue());
            prepStatement.setString(2, description.getText());
            prepStatement.setString(3, comments.getText());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            //TODO catch all exceptions and do something with them
            e.printStackTrace();
        }
    }
}
