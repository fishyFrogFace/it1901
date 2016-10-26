package com.it1901.booking.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.Application.TableViewMaker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import org.controlsfx.control.textfield.TextFields;

public class GenreSearchController extends Controller {

    @FXML
    private TextField searchField;
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane tableAnchor;

    //When search button clicked.
    @FXML
    public void onSearchClicked() throws SQLException {
        errorLabel.setText("");
        tableAnchor.getChildren().clear();

        try {
            ResultSet res = SearchHandler.eventsByGenre(searchField.getText(), app.getDatabaseHandler());
            tableAnchor.getChildren().add(TableViewMaker.makeTable(res, Arrays.asList("Genre", "Stage", "Artist", "Date", "Duration", "Sold", "Price")));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            errorLabel.setTextFill(Paint.valueOf("#ff3636"));
            errorLabel.setText("No genre of this type in database");
        }
    }

    @Override
    public void onLoad() {
        TextFields.bindAutoCompletion(searchField, Arrays.asList("rap", "pop", "rock", "blues", "alternative", "country", "electronic", "dance", "classical", "r&b/soul", "reggae", "jazz", "metal"));
    }
}
