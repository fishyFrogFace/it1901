package com.it1901.booking.JavaFX;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.Employee.User;
import com.it1901.booking.JavaFX.Controllers.*;
import com.it1901.booking.JavaFX.Controllers.Calendar.CalendarContainer;
import com.it1901.booking.JavaFX.Controllers.Dashboard.Buttons.ArtistViewController;
import com.it1901.booking.JavaFX.Controllers.Dashboard.Buttons.OfferController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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
        setScene(loadGeneric("/Dashboard/Dashboard.fxml", "Dashboard"));
    }

    public void makeSearchArtist() {
    	setScene(loadGeneric("/Dashboard/Buttons/SearchArtist.fxml", "Search for artist"));
    }

    public void makeSearchGenre(){
        setScene(loadGeneric("/Dashboard/Buttons/InformationByGenre.fxml", "Search by genre"));
    }

    public void makePriceGenerator(){
    	setScene(loadGeneric("/Dashboard/Buttons/CalculatePrice.fxml", "Get ticketprice"));
    }

    public void makeCalendar(LocalDate basis) {
        CalendarContainer calendarContainer = new CalendarContainer(basis, this);
        BorderPane parent = calendarContainer.getCalendarContainer();
        parent.getStylesheets().add(getClass().getResource("/Calendar/calendar.css").toExternalForm());
        setScene(parent);
    }

    private Parent loadGeneric(String path, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path)); //need instantiated loader to get controller
        Parent parent = null; //Loads fxml
        try {
            parent = loader.load();
            System.out.println("Loaded: "+path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentController = loader.getController(); //Set current controller
        currentController.setApp(this); //register app in controller
        if (!title.equals("Concert")) {
            currentController.onLoad();
        }
        primarystage.setTitle(title);
        return parent;
    }

    public void makeArtistView(String artist) {
        Parent parent = loadGeneric("/Dashboard/Buttons/ArtistView.fxml", "Artist");
        ((ArtistViewController) currentController).displayArtist(artist);
        setScene(parent);
    }

    public void makeOffer(LocalDate date, com.it1901.booking.Application.Stage.stages stage) {
        OfferController oc = new OfferController(this, date, stage);
        BorderPane parent = oc.createOfferContainer();
        parent.getStylesheets().add(getClass().getResource("/standard.css").toExternalForm());
        setScene(parent);
    }

    public void makeConcertView(Integer concertID) {
        Parent parent = loadGeneric("/Calendar/ConcertView/ConcertView.fxml", "Concert");
        currentController.onLoad(concertID);
        setScene(parent);
    }

    public void placeHolder() {
        System.out.println("PlaceHolder, change method to your make___() method!");
    }

    public void setScene(Parent parent) {
        primarystage.setScene(new Scene(parent));
        parent.getStylesheets().add(getClass().getResource("/standard.css").toExternalForm());
        parent.getStylesheets().add("https://fonts.googleapis.com/css?family=Lato");
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

    public void makeTechView() {
        setScene(loadGeneric("/Dashboard/Buttons/TechView.fxml", user.getName() + "'s work hours"));
    }

    public void makeRequirements() {
        setScene(loadGeneric("/Calendar/ConcertView/Requirements.fxml", "Requirements"));
    }

    public void makeConcertList() {
        setScene(loadGeneric("/Calendar/ConcertListView.fxml", "List of concerts"));
    }
}
