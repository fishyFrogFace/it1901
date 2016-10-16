package com.it1901.booking.JavaFX.Controllers.ConcertView;

import com.it1901.booking.JavaFX.Controllers.Controller;
import javafx.fxml.FXML;

public class ConcertViewController extends Controller {
    protected Integer concertID = 5;

    @FXML
    private InfoController infoController;

    @FXML
    private StatusController statusController;

    @Override
    public void onLoad() {
        System.out.println("Concertview onload");
        infoController.onLoad();
        //load StatusController
    }

}
