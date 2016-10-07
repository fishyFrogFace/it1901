package com.it1901.booking.JavaFX.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.it1901.booking.Application.SearchHandler;

import com.it1901.booking.Application.TableViewMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

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
            String searchText = searchField.getText();
            ResultSet res;
            if (searchText.equals("")) {
                //TODO Does not currently work, need a enum wildcard (fint om Camilla ser på denne :))
                res = SearchHandler.eventsByGenre("*", app.getDatabaseHandler()); // Show all if no keyword is specified
            } else {
                res = SearchHandler.eventsByGenre(searchField.getText(), app.getDatabaseHandler());
            }
            tableAnchor.getChildren().add(TableViewMaker.makeTable(res, Arrays.asList("ID", "Genre", "Sales", "Stage", "Artist"))); //Makes result set and displays to screen

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            errorLabel.setTextFill(Paint.valueOf("#ff3636"));
            errorLabel.setText("No genre of this type in database");

        }
    }
}
