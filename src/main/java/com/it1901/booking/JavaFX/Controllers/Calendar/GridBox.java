package com.it1901.booking.JavaFX.Controllers.Calendar;

import com.it1901.booking.Application.Stage;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class GridBox extends VBox {

    //stores date and scene to pass to makeNewOffer()
    protected final LocalDate date;
    private final Stage.stages stage;

    public GridBox(LocalDate date, Stage.stages stage) {
        super();
        this.date = date;
        this.stage = stage;
    }
}
