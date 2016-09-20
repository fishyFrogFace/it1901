package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class DashController extends Controller {

    @FXML
    private Label userType;

    public void addDashElements(User user){
        userType.setText(user.getUserType());
        //TODO Add appropriate elemets for this type of user
    }

}
