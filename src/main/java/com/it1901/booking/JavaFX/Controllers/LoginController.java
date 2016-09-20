package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.LoginHandler;
import com.it1901.booking.Application.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.sql.SQLException;

import static com.it1901.booking.Application.LoginHandler.login;

public class LoginController extends Controller{

    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;
    @FXML
    private Label errorLabel;

    @FXML
    public void onSubmitClick() {

        User user = null;
        try {
            String usnm = username.getText();
            String pswd = pass.getText();
            user = LoginHandler.login(usnm, pswd);
        } catch (SQLException e) {
            e.printStackTrace();
            errorLabel.setTextFill(Paint.valueOf("#ff3636"));
            errorLabel.setText("Error");
        }
        if (user != null) {
            errorLabel.setText("Welcome");
            try {
                app.setUser(user);
                app.makeDash();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
        else {
            errorLabel.setTextFill(Paint.valueOf("#ff3636"));
            errorLabel.setText("Wrong username or password");
        }

    }

}
