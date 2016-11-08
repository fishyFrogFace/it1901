package com.it1901.booking.JavaFX.Controllers.Calendar;

import com.it1901.booking.JavaFX.BookingApp;
import com.it1901.booking.JavaFX.Controllers.Calendar.Calendar;
import com.it1901.booking.JavaFX.Controllers.Elements.NavBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
        mainContainer.setPrefWidth(1200);
        mainContainer.setMinHeight(600);
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
        HBox menuBar = NavBar.createMenu(app);

        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(event -> app.makeCalendar(datePicker.getValue()));
        HBox datePickerContainer = new HBox(datePicker);
        datePickerContainer.setPadding(new Insets(20));
        datePickerContainer.setAlignment(Pos.CENTER);
        return new VBox(menuBar, datePickerContainer);
    }

    private VBox createBackBtn() {
        VBox left = new VBox();
        left.setPadding(new Insets(20));
        Button back = new Button("<");
        AnchorPane.setTopAnchor(back, 0.0);
        AnchorPane.setBottomAnchor(back, 0.0);
        AnchorPane.setLeftAnchor(back, 200.0);
        back.setPrefWidth(60);
        back.setPrefHeight(500);
        back.setOnAction(event -> app.makeCalendar(calendar.getStartOfWeek().minusDays(7)));
        left.getChildren().add(back);
        return left;
    }

    private VBox createFwdBtn() {
        VBox right = new VBox();
        right.setPadding(new Insets(20));
        Button fwd = new Button(">");
        AnchorPane.setTopAnchor(fwd, 0.0);
        AnchorPane.setBottomAnchor(fwd, 0.0);
        AnchorPane.setRightAnchor(fwd, 200.0);
        fwd.setPrefWidth(60);
        fwd.setPrefHeight(500);
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
