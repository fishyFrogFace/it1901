package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.JavaFX.BookingApp;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class NavBar {

    public static MenuBar createMenu(BookingApp app) {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Navigation");
        MenuItem goDash = new MenuItem("Dashboard");
        goDash.setOnAction(event ->  app.makeDash());
        menu.getItems().add(goDash);
        menuBar.getMenus().add(menu);
        return menuBar;
    }
}
