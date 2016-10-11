package com.it1901.booking.JavaFX.Controllers.Calendar;

import javafx.scene.control.Button;

public class ConcertButton extends Button {

    //need to store concertID in case user clicks on the button to see details
    //about the concert
    protected final Integer concertID;

    public ConcertButton(String text, Integer concertID) {
        super(text);
        this.concertID = concertID;
    }

    public Integer getConcertID() {
        return this.concertID;
    }
}
