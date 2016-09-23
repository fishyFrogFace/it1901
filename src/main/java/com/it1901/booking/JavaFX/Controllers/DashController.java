package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;


public class DashController extends Controller {

    @FXML
    private Label userType;
    @FXML
    private FlowPane btnContainer;

    public void addDashElements(User user){
        userType.setText(user.getUserType());
        //TODO Add appropriate elemets for this type of user
        switch (user.getUserType()) {
            case "administrator":
                createButton("Prisforslag");
                createButton("Konsert rapporter");
                createButton("Hendelses oversikt");
                createButton("Godkjenn tilbud");
            case "booker":
                createButton("Søk blant konserter");
                createButton("Søk blant band");
                createButton("Opprett tilbud");
                createButton("Sjangeroversikt");
            case "organizer":
                createButton("Deleger teknikere");
                createButton("Konsertoversikt");
                break;
            case "tech":
                //TODO redirect to tech page
                break;
        }
    }

    private void createButton(String label) {
        Button btn = new Button(label);
        //btn.setOnAction();
        btn.setPrefSize(160, 80); //FIXME flytt dette til css
        btnContainer.getChildren().add(btn);
    }

}
