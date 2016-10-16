package com.it1901.booking.JavaFX.Controllers.ConcertView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StatusController extends ConcertViewController {

    @FXML
    Button accept;

    @FXML
    Button decline;

    @FXML
    Button email;

    @FXML
    Button book;

    public void load() {
        Integer concertID = this.concertID;
    }

    public void acceptOffer() {
        System.out.println("accepted clicked");
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
}
