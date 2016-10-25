package com.it1901.booking.JavaFX.Controllers.Calendar;

import com.it1901.booking.JavaFX.BookingApp;
import com.it1901.booking.JavaFX.Controllers.Calendar.Calendar;
import com.it1901.booking.JavaFX.Controllers.Elements.NavBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.time.LocalDate;

public class CalendarContainer {
    private final BorderPane mainContainer;
    private final Calendar calendar;
    private final BookingApp app;

    public CalendarContainer(LocalDate basis, BookingApp app) {
        this.mainContainer = new BorderPane();
        this.app = app;
        this.calendar = new Calendar(basis, app);
    }

    public BorderPane getCalendarContainer() {
        GridPane calGrid = getCalendar();

        mainContainer.setCenter(new ScrollPane(calGrid));
        mainContainer.setTop(createHeader());
        mainContainer.setLeft(createBackBtn());
        mainContainer.setRight(createFwdBtn());
        mainContainer.setBottom(createBottom());

        return mainContainer;
    }

    private GridPane getCalendar() {
        GridPane calGrid;
        try {
            calGrid = calendar.createCalendar();
        } catch (SQLException e) {
            calGrid = new GridPane();
            Text errorMessage = new Text("Could not connect to the database");
            calGrid.getChildren().add(errorMessage);
            e.printStackTrace();
        }
        return calGrid;
    }

    private VBox createHeader() {
        MenuBar menuBar = NavBar.createMenu(app);

        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(event -> app.makeCalendar(datePicker.getValue()));
        HBox datePickerContainer = new HBox(datePicker);
        datePickerContainer.setPadding(new Insets(20));
        datePickerContainer.setAlignment(Pos.CENTER);
        return new VBox(menuBar, datePickerContainer);
    }

    private VBox createBackBtn() {
        VBox left = new VBox();
        left.setAlignment(Pos.CENTER);
        left.setPadding(new Insets(20));
        Button back = new Button("<");
        back.setPrefWidth(40);
        back.setPrefHeight(50);
        back.setOnAction(event -> app.makeCalendar(calendar.getStartOfWeek().minusDays(7)));
        left.getChildren().add(back);
        return left;
    }

    private VBox createFwdBtn() {
        VBox right = new VBox();
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(20));
        Button fwd = new Button(">");
        fwd.setPrefWidth(40);
        fwd.setPrefHeight(50);
        fwd.setOnAction(event -> app.makeCalendar(calendar.getStartOfWeek().plusDays(7)));
        right.getChildren().add(fwd);
        return right;
    }

    private HBox createBottom() {
        Button viewList = new Button("View list");
        viewList.setPrefWidth(100);
        viewList.setPrefHeight(40);
        viewList.setOnAction(event -> app.makeConcertList());
        HBox bottom = new HBox(viewList);
        bottom.setPadding(new Insets(20));
        bottom.setAlignment(Pos.CENTER);
        return bottom;
    }
}
