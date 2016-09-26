package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import java.io.IOException;


public class DashController extends Controller {

    @FXML
    private Label userType;
    @FXML
    private FlowPane btnContainer;

    public void addDashElements(User user) throws IOException {
        userType.setText(user.getUserType());
        //TODO Add appropriate elemets for this type of user
        switch (user.getUserType()) {
            case "administrator":
                createButton("Prisforslag", event -> app.placeHolder(""));
                createButton("Konsert rapporter", event -> app.placeHolder(""));
                createButton("Hendelses oversikt", event -> app.placeHolder(""));
                createButton("Godkjenn tilbud", event -> app.placeHolder(""));
            case "booker":
                createButton("Søk blant konserter", event -> app.placeHolder(""));
                createButton("Søk blant artister", event -> app.placeHolder(""));
                createButton("Opprett tilbud", event -> app.placeHolder(""));
                createButton("Sjangeroversikt", event -> app.placeHolder("Sjangeroversikt"));
            case "organizer":
                createButton("Deleger teknikere", event -> app.placeHolder(""));
                createButton("Konsertoversikt", event -> app.placeHolder(""));
                break;
            case "tech":
                //TODO redirect to tech page
                break;
        }
    }

    private void createButton(String label, EventHandler eventHandler) throws IOException {
        Button btn = new Button(label);
        btn.setOnAction(eventHandler);
        btn.setPrefSize(160, 80); //FIXME flytt dette til css
        btnContainer.getChildren().add(btn);
    }

}
