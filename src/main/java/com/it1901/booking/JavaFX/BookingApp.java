package com.it1901.booking.JavaFX;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.User;
import com.it1901.booking.JavaFX.Controllers.ArtistViewController;
import com.it1901.booking.JavaFX.Controllers.Controller;
import com.it1901.booking.JavaFX.Controllers.DashController;
import com.it1901.booking.JavaFX.Controllers.OfferController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingApp extends Application {

    private User user;
    private DatabaseHandler dbh;
    private Stage primarystage;
    private Controller currentController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ps) {
        this.primarystage = ps;
        makeLogin();
    }

    public void makeLogin() {
        setScene(loadGeneric("/Login.fxml", "Login"));
    }

    public void makeDash() {
        Parent parent = loadGeneric("/Dashboard.fxml", "Dashboard");
        try {
            ((DashController)currentController).addDashElements(user);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not add dashboard elements.");
        }
        setScene(parent);
    }

    public void makeSearchArtist() {
    	setScene(loadGeneric("/SearchArtist.fxml", "Search for artist"));
    }

    public void makeSearchGenre(){
    	setScene(loadGeneric("/InformationByGenre.fxml", "Search by genre"));
    }
    public void makePriceGenerator(){
    	setScene(loadGeneric("/CalculatePrice.fxml", "Get ticketprice"));
    }
    
    public void makeTable(){
    	setScene(loadGeneric("/TableController.fxml", "Table"));
    }
    public Parent loadGeneric(String path, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path)); //need instantiated loader to get controller
        Parent parent = null; //Loads fxml
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentController = loader.getController(); //Set current controller
        currentController.setApp(this); //register app in controller
        primarystage.setTitle(title);
        return parent;
    }

    public void makeArtistView(String artist) {
        Parent parent = loadGeneric("/ArtistView.fxml", artist);
        ((ArtistViewController) currentController).displayArtist(artist);
        setScene(parent);
    }

    public void makeOffer() {
        Parent parent = loadGeneric("/OfferView.fxml", "Make an offer");
        setScene(parent);
    }

    public void placeHolder() {
        System.out.println("PlaceHolder, change method to your make___() method!");
    }

    public void setScene(Parent parent) {
        primarystage.setScene(new Scene(parent));
        primarystage.show();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDatabaseHandler(DatabaseHandler dbh) {
        this.dbh = dbh;
    }

    public User getUser(){ return this.user; }

    public DatabaseHandler getDatabaseHandler() {
        return this.dbh;
    }
}
