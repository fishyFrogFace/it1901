package com.it1901.booking.JavaFX.Controllers.Elements;

import com.it1901.booking.JavaFX.BookingApp;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

public class NavBar {

    public static HBox createMenu(BookingApp app) {
        Button logout = new Button("Logout");
        Button dash = new Button("Dashboard");
        dash.setOnAction(event ->  app.makeDash());
        logout.setOnAction(event -> {
            app.setUser(null);
            app.makeLogin();
        });
        HBox menuBar = new HBox(logout, dash);
        menuBar.setPrefHeight(40);
        menuBar.setMinHeight(40);
        menuBar.setMaxHeight(40);
        AnchorPane.setRightAnchor(menuBar, 0.0);
        AnchorPane.setLeftAnchor(menuBar, 0.0);
        AnchorPane.setTopAnchor(menuBar, 0.0);
        menuBar.setSpacing(10);
        menuBar.setPadding(new Insets(8, 8, 8, 8));
        //menuBar.setStyle("-fx-background-color: #000000"); FIXME TO BE THE SAME AS STYLE COLOR
        return menuBar;
    }
}
