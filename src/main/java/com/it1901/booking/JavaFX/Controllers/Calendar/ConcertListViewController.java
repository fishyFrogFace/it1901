package com.it1901.booking.JavaFX.Controllers.Calendar;

import com.it1901.booking.Application.TableViewMaker;
import com.it1901.booking.JavaFX.Controllers.Calendar.Calendar;
import com.it1901.booking.JavaFX.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;


public class ConcertListViewController extends Controller{

    @FXML
    private AnchorPane tableAnchor;

    @Override
    public void onLoad(){
        try {
            //TODO get rid of id and make it possible to click on concerts to get to concert view
            tableAnchor.getChildren().add(
                    TableViewMaker.makeTable(
                            Calendar.getCalendarContent(
                                    LocalDate.now().minusYears(1),
                                    LocalDate.now().plusYears(1),
                                    app.getDatabaseHandler()),
                            Arrays.asList("ID", "Date", "Artist", "Genre", "State", "Stage")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goBack(){
        app.makeCalendar(LocalDate.now());
    }
}
