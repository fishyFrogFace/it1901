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
    private Controller curentController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ps) throws IOException{
        this.primarystage = ps;
        makeLogin();
    }

    public void makeLogin() throws IOException {
        setScene(loadGeneric("Login.fxml", "Login"));
    }

    public void makeDash() throws IOException {
        Parent parent = loadGeneric("Dashboard.fxml", "Dashboard");
        ((DashController)curentController).addDashElements(user);
        primarystage.setTitle("Dashboard");
        setScene(parent);
    }

    public Parent loadGeneric(String path, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path)); //need instantiated loader to get controller
        Parent parent = loader.load(); //Loads fxml
        curentController = loader.getController(); //Set current controller
        curentController.setApp(this); //register app in controller
        primarystage.setTitle(title);
        return parent;
    }

    public void setScene(Parent parent) {
        primarystage.setScene(new Scene(parent));
        primarystage.show();
    }

}
