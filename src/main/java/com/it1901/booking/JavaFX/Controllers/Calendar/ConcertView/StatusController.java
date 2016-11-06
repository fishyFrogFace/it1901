package com.it1901.booking.JavaFX.Controllers.Calendar.ConcertView;

import com.it1901.booking.Application.Concert.Email.Email;
import com.it1901.booking.Application.Concert.Offer.ConcertHandler;
import com.it1901.booking.Application.Concert.Offer.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;

import static com.it1901.booking.Application.Employee.User.Role.*;

public class StatusController {

    ConcertViewController cvc;
    Offer.offerState startingState;

    @FXML
    Button accept;

    @FXML
    Button decline;

    @FXML
    Button email;

    @FXML
    Button book;

    @FXML
    Label messageLabel;

    public void load(ConcertViewController concertViewController) {
        cvc = concertViewController;
        startingState = cvc.offer.getState();
    }

    public void acceptOffer() {
        switch (cvc.app.getUser().getUserRole()) {
            case administrator:
                try {
                    //TODO store all state changes in a separate table so you can show them
                    //TODO load concert view information again if state is changed
                    cvc.offer.setStatusChange(cvc.app.getUser().getUserID(), cvc.app.getDatabaseHandler());
                    cvc.offer.saveState(Offer.offerState.accepted, cvc.app.getDatabaseHandler());
                    messageLabel.setText("State changed");
                    cvc.updateInfoTab();
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
        switch (cvc.app.getUser().getUserRole()) {
            case administrator:
            case booker:
                try {
                    cvc.offer.saveState(Offer.offerState.declined, cvc.app.getDatabaseHandler());
                    messageLabel.setText("State changed");
                    cvc.updateInfoTab();
                } catch (SQLException e) {
                    messageLabel.setText("Could not connect to database");
                    e.printStackTrace();
                }
                break;
            default:
                messageLabel.setText("You are not allowed to perform this action");
        }
    }

    public void sendEmail() {
        if (startingState.equals(Offer.offerState.accepted) ||
                cvc.app.getUser().getUserRole().equals(administrator)) {
            try {
                Email thisEmail = Email.fetchEmail(
                        cvc.offer.getOfferID(),
                        cvc.app.getDatabaseHandler());
                Boolean sent = thisEmail.sendThisEmail(cvc.app.getDatabaseHandler());
                if (sent) {
                    messageLabel.setText("Email was sent successfully");
                    cvc.offer.saveState(Offer.offerState.sent, cvc.app.getDatabaseHandler());
                    cvc.updateInfoTab();
                } else {
                    messageLabel.setText("Could not find any email for this artist");
                }
            } catch (SQLException e) {
                messageLabel.setText("Could not find any emails for this event");
                e.printStackTrace();
            }
        }
    }

    public void bookConcert() {
        if (startingState.equals(Offer.offerState.accepted) ||
                startingState.equals(Offer.offerState.sent) ||
                cvc.app.getUser().getUserRole().equals(administrator)) {
            try {
                if (ConcertHandler.checkAvailable(
                        cvc.concert.getStageID(),
                        cvc.concert.getStartDate(),
                        cvc.app.getDatabaseHandler())) {
                    cvc.offer.saveState(Offer.offerState.booked, cvc.app.getDatabaseHandler());
                    messageLabel.setText("State changed");
                    cvc.updateInfoTab();
                } else {
                    messageLabel.setText("Stage not available on this date");
                }
            } catch (SQLException e) {
                messageLabel.setText("Could not connect to database");
                e.printStackTrace();
            }
        } else {
                messageLabel.setText("You are not allowed to perform this action");
        }
    }

    public void setEmpty() {
        messageLabel.setText("");
    }
}
