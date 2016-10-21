package com.it1901.booking.JavaFX.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuBarController extends Controller {

    @FXML
    private MenuItem btnDash;

    @FXML
    private MenuItem btnLogOut;

    public void goToDash(){
        app.makeDash();
    }

    public void logOut() {
        app.setUser(null);
        app.makeLogin();
    }
}
