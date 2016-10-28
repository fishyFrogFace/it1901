package com.it1901.booking.JavaFX.Controllers.ConcertView;

import com.it1901.booking.Application.Employee.User;
import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.Application.TableViewMaker;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class TechController{

    ConcertViewController cvc;

    @FXML
    private AnchorPane tableAnchor;

    @FXML
    private TextField txtName;

    @FXML
    private ChoiceBox<Integer> cbxHours;

    @FXML
    private ChoiceBox<String> cbxRole;

    @FXML
    private Label lblError;


    public void load(ConcertViewController concertViewController) {
        this.cvc = concertViewController;

        cbxHours.getItems().addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
        cbxHours.setValue(1);
        cbxRole.getItems().addAll(Arrays.asList("lyd", "lys", "rigging"));
        cbxRole.setValue("rigging");
        try {
            TextFields.bindAutoCompletion(txtName, SearchHandler.getCollection("name", "employee", cvc.app.getDatabaseHandler()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getTechTable();
    }

    public void getTechTable() {
        try {
            tableAnchor.getChildren().clear();
            tableAnchor.getChildren().addAll(
                    TableViewMaker.makeTable(
                            SearchHandler.getAssignedTechs(cvc.concert.getConcertID(), cvc.app.getDatabaseHandler()),
                            Arrays.asList("Name", "Role", "Hours")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void submit() throws SQLException {
        if (cbxHours.getValue() == null || txtName.getText().equals("") || cbxRole.getValue() == null) {
            lblError.setText("Please fill in all the fields.");
            return;
        }
        try {
            String query = "INSERT INTO assigned VALUES (DEFAULT, ?, ?::techType, ?, ?)";
            PreparedStatement preparedStatement = cvc.app.getDatabaseHandler().prepareQuery(query);
            preparedStatement.setInt(1, cbxHours.getValue());
            preparedStatement.setString(2, cbxRole.getValue());
            preparedStatement.setInt(3, SearchHandler.getEmloyeeID(txtName.getText(), cvc.app.getDatabaseHandler()));
            preparedStatement.setInt(4, cvc.concert.getConcertID());
            preparedStatement.execute();

            txtName.setText("");
            cbxRole.setValue("rigging");
            cbxHours.setValue(1);
            lblError.setText("");

            getTechTable();
        } catch (SQLException e) {
            lblError.setText("Tech name not valid");
        }
    }
}