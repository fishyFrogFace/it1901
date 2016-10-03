package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Stage;
import com.it1901.booking.JavaFX.Calendar.Calendar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CalendarController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Calendar cal = new Calendar(LocalDate.now());
        GridPane calendar = null;
        try {
            calendar = cal.createCalendar(app.getDatabaseHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        root.getChildren().add(calendar);
    }
}
