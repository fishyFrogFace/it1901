package com.it1901.booking.JavaFX.Controllers.ConcertView;

import com.it1901.booking.JavaFX.Controllers.Controller;

public class ConcertViewController extends Controller {
    protected Integer concertID = 5;
    private InfoController infoController;
    private StatusController statusController;

    @Override
    public void onLoad() {
        System.out.println("Concertview onload");
        //load InfoController
        //load StatusController
    }

}
