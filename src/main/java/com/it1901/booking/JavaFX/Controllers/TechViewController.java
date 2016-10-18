package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.Application.TableViewMaker;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.Arrays;


public class TechViewController extends Controller{

    @FXML
    private AnchorPane tableAnchor;

    @Override
    public void onLoad(){
        try {
            tableAnchor.getChildren().add(
                    TableViewMaker.makeTable(
                            SearchHandler.getAssignedConcerts(
                                    app.getUser().getUserID(),
                                    app.getDatabaseHandler()),
                            Arrays.asList("Hours", "Artist", "Stage", "Date" ))); //add"Role",
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
