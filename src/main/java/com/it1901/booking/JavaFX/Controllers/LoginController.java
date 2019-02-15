package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.Employee.LoginHandler;
import com.it1901.booking.Application.Employee.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.sql.SQLException;

public class LoginController extends Controller{

    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;
    @FXML
    private Label errorLabel;

    @FXML
    public void onSubmitClick() {
        DatabaseHandler dbh = null;
        errorLabel.setTextFill(Paint.valueOf("#ff3636"));
        try {
            dbh = new DatabaseHandler(
                    "org.postgresql.Driver",
                    "jdbc:postgresql://localhost:5432/booking",
                    "team",
                    "it1901");
        } catch (SQLException e) {
            errorLabel.setText("No contact with database");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            errorLabel.setText("Driver error");
            e.printStackTrace();
        }
        app.setDatabaseHandler(dbh);

        User user = null;
        try {
            user = LoginHandler.login(username.getText(), pass.getText(), dbh);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            errorLabel.setText("Error");
        }
        if (user != null) {
            errorLabel.setTextFill(Paint.valueOf("#000000"));
            errorLabel.setText("Welcome");
            try {
                app.setUser(user);
                app.makeDash();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            errorLabel.setText("Wrong username or password");
        }

    }

}
