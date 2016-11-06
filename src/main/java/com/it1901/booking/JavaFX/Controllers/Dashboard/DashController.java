package com.it1901.booking.JavaFX.Controllers.Dashboard;

import com.it1901.booking.Application.Stage;
import com.it1901.booking.Application.Employee.User;
import com.it1901.booking.JavaFX.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;


public class DashController extends Controller {

    @FXML
    private Label userRole;

    @FXML
    private FlowPane btnContainer;

    @FXML
    Text messageLabel;

    private void addDashElements(User user) throws IOException {
        setUserRole(user.getUserRole());
        //TODO Add appropriate elements for this type of user
        switch (user.getUserRole()) {
            case administrator:
                createButton("Price calculator", event -> app.makePriceGenerator());
            case booker:
                createButton("Make new offer", event -> app.makeOffer(LocalDate.now(), Stage.stages.Storsalen));
                createButton("Artists", event -> app.makeArtistView("init"));
                createButton("Search by genre", event -> app.makeSearchGenre());
            case organizer:
            case tech:
                createButton("View work hours", event -> app.makeTechView());
            case manager:
                createButton("Calendar", event -> app.makeCalendar(LocalDate.now()));
                break;
        }
    }

    private void setUserRole(User.Role role) {
        switch (role) {
            case administrator:
                userRole.setText("Bookingsjef");
                break;
            case booker:
                userRole.setText("Bookingansvarlig");
                break;
            case organizer:
                userRole.setText("Arrangør");
                break;
            case tech:
                userRole.setText("Tech");
                break;
            case manager:
                userRole.setText("Manager");
                break;
        }
    }

    private void createButton(String label, EventHandler<ActionEvent> eventHandler) throws IOException {
        Button btn = new Button(label);
        btn.setOnAction(eventHandler);
        btn.setPrefSize(160, 80); //FIXME flytt dette til css
        btnContainer.getChildren().add(btn);
    }


    @Override
    public void onLoad(){
        try {
            addDashElements(app.getUser());
        } catch (IOException e) {
            messageLabel.setText("Dashboard elements could not be added");
            e.printStackTrace();
            System.out.println("Could not add dashboard elements.");
        }
    }
}