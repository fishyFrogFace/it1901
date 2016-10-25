package com.it1901.booking.JavaFX;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.Employee.User;
import com.it1901.booking.JavaFX.Controllers.*;
import com.it1901.booking.JavaFX.Controllers.Calendar.CalendarContainer;
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
        setScene(loadGeneric("/Dashboard.fxml", "Dashboard"));
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

    public void makeCalendar(LocalDate basis) {
        CalendarContainer calendarContainer = new CalendarContainer(basis, this);
        BorderPane parent = calendarContainer.getCalendarContainer();
        parent.getStylesheets().add(getClass().getResource("/calendar.css").toExternalForm());
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
        Parent parent = loadGeneric("/ArtistView.fxml", artist);
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
        Parent parent = loadGeneric("/ConcertView/ConcertView.fxml", "Concert");
        currentController.onLoad(concertID);
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

    public void makeTechView() {
        setScene(loadGeneric("/TechView.fxml", user.getName() + "'s work hours"));
    }

    public void makeRequirements() {
        setScene(loadGeneric("/Requirements.fxml", "Requirements"));
    }

    public void makeConcertList() {
        setScene(loadGeneric("/ConcertView/ConcertListView.fxml", "List of concerts"));
    }
}
