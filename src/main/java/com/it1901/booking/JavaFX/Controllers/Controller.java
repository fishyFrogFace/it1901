package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.JavaFX.BookingApp;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public abstract class Controller {
    protected BookingApp app;

    public void setApp(BookingApp app) {
        this.app = app;
    }

    @FXML
    private MenuItem menuDash;

    @FXML
    private MenuItem menuLogout;

    public void goToDash(){
        app.makeDash();
    }

    public void logOut() {
        app.setUser(null);
        app.makeLogin();
    }

    //public void onLoad(){} //for loading autocomplete etc. TODO make usable and clean up in BookingApp
}
