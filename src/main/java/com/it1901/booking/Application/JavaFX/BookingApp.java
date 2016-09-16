package com.it1901.booking.Application.JavaFX;

import com.it1901.booking.Application.Booker;
import com.it1901.booking.Application.LoginHandler;
import com.it1901.booking.Application.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingApp extends Application {

    private User user = new Booker(123); //FIXME temp test
    private Stage primarystage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ps) throws IOException{
        this.primarystage = ps;
        makeLogin();
    }

    public void makeLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml")); //need instantiated loader to get controller later
        Parent parent = loader.load();
        LoginController controller = loader.getController();
        controller.setApp(this);
        primarystage.setTitle("Login");
        primarystage.setScene(new Scene(parent));
        primarystage.show();
    }

    public void makeDash() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent parent = loader.load();
        DashController controller = loader.getController();
        controller.setApp(this);
        controller.addDashElements(user);
        primarystage.setTitle("Dashboard");
        primarystage.setScene(new Scene(parent));
        primarystage.show();
    }

    //TODO clean up duplicate code

}
