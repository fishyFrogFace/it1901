package com.it1901.booking.JavaFX.Controllers.Calendar.ConcertView;

import com.it1901.booking.Application.Artist;
import com.it1901.booking.JavaFX.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequirementsController extends Controller {

    @FXML
    private TextField requirement;

    @FXML
    private TextArea description;

    @FXML
    private TextArea comments;

    @FXML
    private Text messageLabel;

    private ConcertViewController cvc;

    public void load(ConcertViewController cvc) {
        this.cvc = cvc;
    }

    public void storeRequirement(ActionEvent actionEvent) {
        String query = "INSERT INTO requirement VALUES" +
                "(DEFAULT, ?, ?, ?)" +
                "RETURNING *";
        try {
            PreparedStatement prepStatement = cvc.app.getDatabaseHandler().prepareQuery(query);
            prepStatement.setInt(1, cvc.concert.getConcertID());
            prepStatement.setString(2, description.getText());
            prepStatement.setString(3, comments.getText());
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                messageLabel.setText("Requirement added");
            } else {
                messageLabel.setText("Could not add this requirement");
            }

        } catch (SQLException e) {
            messageLabel.setText("Could not establish connection with the database");
            e.printStackTrace();
        }
    }
}
