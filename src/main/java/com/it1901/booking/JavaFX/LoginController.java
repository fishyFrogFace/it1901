package com.it1901.booking.JavaFX;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class LoginController extends Controller{

    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;
    @FXML
    private Label errorLabel;

    @FXML
    public void onSubmitClick() {
        if (username.getText().equals("user") && pass.getText().equals("pass")) { //TODO add actual logic
            errorLabel.setTextFill(Paint.valueOf("#36ff36"));
            //TODO app.setUser(new User(query for user type))
            errorLabel.setText("Welcome");
            try {
                app.makeDash();
            } catch (IOException io) {
                errorLabel.setTextFill(Paint.valueOf("#ff3636"));
                errorLabel.setText("Error");
                System.out.println(io.getLocalizedMessage());
            }
        } else {
            errorLabel.setTextFill(Paint.valueOf("#ff3636"));
            errorLabel.setText("Wrong username or password");
        }
    }

}
