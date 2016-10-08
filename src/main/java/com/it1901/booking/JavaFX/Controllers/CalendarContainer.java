package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.JavaFX.BookingApp;
import com.it1901.booking.JavaFX.Controllers.Calendar.Calendar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.time.LocalDate;

public class CalendarContainer {
    private final BorderPane mainContainer;
    private final Calendar calendar;
    private final BookingApp app;
    private final Button prev = new Button("<");
    private final Button next = new Button(">");
    private final static Button goToDate = new Button("Go");

    public CalendarContainer(LocalDate basis, BookingApp app) {
        this.calendar = new Calendar(basis);
        this.mainContainer = new BorderPane();
        this.app = app;
    }

    public BorderPane getCalendarContainer(DatabaseHandler dbh) {
        GridPane calGrid = getCalendar(dbh);
        mainContainer.setCenter(calGrid);

        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(event -> app.makeCalendar(datePicker.getValue()));
        BorderPane.setAlignment(datePicker, Pos.CENTER);
        mainContainer.setTop(datePicker);
        //TODO add buttons to show all pending/sent event and change status to declined/sent

        return mainContainer;
    }

    private GridPane getCalendar(DatabaseHandler dbh) {
        GridPane calGrid;
        try {
            calGrid = calendar.createCalendar(dbh);
        } catch (SQLException e) {
            calGrid = new GridPane();
            Text errorMessage = new Text("Could not connect to the database");
            calGrid.getChildren().add(errorMessage);
        }
        return calGrid;
    }

}
