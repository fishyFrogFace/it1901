package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.JavaFX.BookingApp;

public abstract class Controller {
    public BookingApp app;

    public void setApp(BookingApp app) {
        this.app = app;
    }

    public void goToDash(){
        app.makeDash();
    }

    public void logOut() {
        app.setUser(null);
        app.makeLogin();
    }

    public void onLoad(){} //for loading autocomplete, data, etc. Override method to use

    public void onLoad(Integer id){}
}
