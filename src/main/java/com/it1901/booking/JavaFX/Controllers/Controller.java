package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.JavaFX.BookingApp;

public abstract class Controller {
    protected BookingApp app;

    public void setApp(BookingApp app) {
        this.app = app;
    }
}
