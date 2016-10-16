package com.it1901.booking.JavaFX.Controllers.ConcertView;

import com.it1901.booking.JavaFX.Controllers.Controller;

public class StatusController extends Controller {

    private final ConcertViewController cvc;

    public StatusController(ConcertViewController cvc) {
        this.cvc = cvc;
    }

    @Override
    public void onLoad() {
        Integer concertID = cvc.concertID;
    }
}
