package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.JavaFX.BookingApp;
import com.it1901.booking.JavaFX.Controllers.Calendar.Calendar;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
        this.mainContainer = new BorderPane();
        this.app = app;
        this.calendar = new Calendar(basis, app);
    }

    public BorderPane getCalendarContainer() {
        GridPane calGrid = getCalendar();
        mainContainer.setCenter(calGrid);

        VBox header = createHeader();

        BorderPane.setAlignment(header, Pos.CENTER);
        mainContainer.setTop(header);
        //TODO add buttons to show all pending/sent event and change status to declined/sent
        //should be added to sides of BorderPane

        return mainContainer;
    }

    private GridPane getCalendar() {
        GridPane calGrid;
        try {
            calGrid = calendar.createCalendar(app);
        } catch (SQLException e) {
            calGrid = new GridPane();
            Text errorMessage = new Text("Could not connect to the database");
            calGrid.getChildren().add(errorMessage);
            e.printStackTrace();
        }
        return calGrid;
    }

    private VBox createHeader() {
        VBox header = new VBox();
        header.setAlignment(Pos.CENTER);

        MenuBar menuBar = NavBar.createMenu(app);

        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(event -> app.makeCalendar(datePicker.getValue()));
        header.getChildren().addAll(menuBar, datePicker);
        return header;
    }
}
