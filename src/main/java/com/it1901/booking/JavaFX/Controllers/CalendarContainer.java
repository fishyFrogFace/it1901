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
    private final Button prev = new Button("<"); //unsure of these buttons
    private final Button next = new Button(">");
    //maybe just add buttons to all events
    //that need a status change

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
        //should be added to sides of BorderPane

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
            e.printStackTrace();
        }
        return calGrid;
    }

}
