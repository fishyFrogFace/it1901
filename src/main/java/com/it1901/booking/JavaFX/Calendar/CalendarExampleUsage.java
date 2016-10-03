package com.it1901.booking.JavaFX.Calendar;

import com.it1901.booking.Application.DatabaseHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class CalendarExampleUsage extends Application {

    public void start(Stage primaryStage) {
        DatabaseHandler dbh = new DatabaseHandler(
                "org.postgresql.Driver",
                "jdbc:postgresql://52.40.176.177:5432/booking",
                "team",
                "it1901");

        Calendar calendar = new Calendar(LocalDate.now());
        GridPane calGrid = null;
        try {
            calGrid = calendar.createCalendar(dbh);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ScrollPane scroller = new ScrollPane(calGrid);

        Scene scene = new Scene(scroller);
        scene.getStylesheets().add(getClass().getResource("/calendar.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
