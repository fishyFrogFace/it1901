package com.it1901.booking.JavaFX.Controllers.ConcertView;

import com.it1901.booking.Application.Concert.Offer.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class StatusController {

    ConcertViewController cvc;

    @FXML
    Button accept;

    @FXML
    Button decline;

    @FXML
    Button email;

    @FXML
    Button book;

    @FXML
    Text messageLabel;

    public void load(ConcertViewController concertViewController) {
        cvc = concertViewController;
    }

    public void acceptOffer() {
        switch (cvc.app.getUser().getUserType()) {
            case "administrator":
                try {
                    cvc.offer.setManager(cvc.app.getUser().getUserID(), cvc.app.getDatabaseHandler());
                    cvc.offer.saveState(Offer.offerState.accepted, cvc.app.getDatabaseHandler());
                    messageLabel.setText("State changed");
                    //app.makeCalendar(this.date); //move to "back"
                } catch (SQLException e) {
                    messageLabel.setText("Could not connect to database");
                    e.printStackTrace();
                }
                break;
            default:
                messageLabel.setText("You are not allowed to perform this action");
        }
    }

    public void declineOffer() {
        System.out.println("declined clicked");
    }

    public void sendEmail() {
        System.out.println("send clicked");
    }

    public void bookConcert() {
        System.out.println("book clicked");
    }

    public void setEmpty() {
        System.out.println("text should be empty");
    }
}
