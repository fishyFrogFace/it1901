package com.it1901.booking.JavaFX;

import com.it1901.booking.Application.Booker;
import com.it1901.booking.Application.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class DashController extends Controller {

    @FXML
    private Label userType;

    public void addDashElements(User user){
        if (user instanceof Booker) {
            userType.setText("Booker");
        }
    }

}
