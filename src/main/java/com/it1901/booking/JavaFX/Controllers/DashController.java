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
                //createButton("Price calculator", event -> app.placeHolder());
                //createButton("Concert rapports", event -> app.placeHolder());
                //createButton("View events", event -> app.placeHolder());
                //createButton("Review offers", event -> app.placeHolder());
                createButton("Example of artist view", event -> app.makeArtistView("Deathgore"));
            case "booker":
                //createButton("Seach by concert", event -> app.placeHolder());
                createButton("Search by artist", event -> app.makeSearchArtist());
                //createButton("Opprett tilbud", event -> app.placeHolder());
                createButton("Search by genre", event -> app.makeSearchGenre());
            case "organizer":
                //createButton("Assign Techs", event -> app.placeHolder());
                //createButton("Concert view", event -> app.placeHolder());
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
