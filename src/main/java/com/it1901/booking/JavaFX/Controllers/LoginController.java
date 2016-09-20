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
        DatabaseHandler dbh = new DatabaseHandler(
                "org.postgresql.Driver",
                "jdbc:postgresql://52.40.176.177:5432/booking",
                "team",
                "it1901");

        User user = null;
        try {
            user = LoginHandler.login(username.getText(), pass.getText(), dbh);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            errorLabel.setTextFill(Paint.valueOf("#ff3636"));
            errorLabel.setText("Error");
        }
        if (user != null) {
            errorLabel.setText("Welcome");
            try {
                app.setUser(user);
                app.makeDash();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            errorLabel.setTextFill(Paint.valueOf("#ff3636"));
            errorLabel.setText("Wrong username or password");
        }

    }

}
