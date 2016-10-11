package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Stage;
import com.it1901.booking.Application.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.time.LocalDate;


public class DashController extends Controller {

    @FXML
    private Label userType;
    @FXML
    private FlowPane btnContainer;

    public void addDashElements(User user) throws IOException {
        setUserType(user.getUserType());
        //TODO Add appropriate elements for this type of user
        switch (user.getUserType()) {
            case "administrator":
                createButton("Price calculator", event -> app.makePriceGenerator());
                //createButton("Concert rapports", event -> app.placeHolder());
                createButton("Artist view", event -> app.makeArtistView("init"));
            case "booker":

                createButton("Calendar", event -> app.makeCalendar(LocalDate.now()));
                //createButton("Search by concert", event -> app.placeHolder());
                createButton("Search by artist", event -> app.makeSearchArtist());
                createButton("Make new offer", event -> app.makeOffer(LocalDate.now(), Stage.stages.Storsalen));
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

    private void setUserType(String type) {
        switch (type) {
            case "administrator":
                userType.setText("Bookingsjef");
                break;
            case "booker":
                userType.setText("Bookingansvarlig");
                break;
            case "organizer":
                userType.setText("Arrangør");
                break;
            case "tech":
                userType.setText("Tekniker");
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