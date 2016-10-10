package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.Event.Offer.Event;
import com.it1901.booking.Application.Event.Offer.Offer;
import com.it1901.booking.JavaFX.BookingApp;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OfferViewController {
    private final BorderPane mainContainer;
    private final BookingApp app;
    private final Integer eventID;
    private final Text errorLabel;
    private Integer offerID;
    private LocalDate date;
    private Integer stageID;

    public OfferViewController(BookingApp app, Integer eventID) {
        this.mainContainer = new BorderPane();
        this.app = app;
        this.eventID = eventID;
        this.errorLabel = new Text();
        BorderPane.setMargin(errorLabel, new Insets(0, 0, 20, 0));
    }

    public BorderPane createOfferViewContainer() {
        mainContainer.setCenter(getCenter());

        MenuBar header = NavBar.createMenu(app);
        mainContainer.setTop(header);

        mainContainer.setBottom(errorLabel);
        BorderPane.setAlignment(errorLabel, Pos.CENTER);

        return mainContainer;
    }

    private SplitPane getCenter() {
        SplitPane center = new SplitPane();
        center.getItems().addAll(createLeft(), createRight());
        BorderPane.setMargin(center, new Insets(20));
        return center;
    }

    private VBox createLeft() {
        VBox left = new VBox();
        left.setMinWidth(150);
        left.setSpacing(10);

        try {
            ResultSet rs = getLeftContent(app.getDatabaseHandler(), eventID);
            rs.next();
            //offerID, startDate, artist.artistID, artist.name, genre, state, stage.name
            this.offerID = rs.getInt(1);
            this.date = rs.getDate(2).toLocalDate();
            this.stageID = rs.getInt(8);
            Text artist = new Text("Artist: "+rs.getString(4));
            Text genre = new Text("Genre: "+rs.getObject(5).toString());
            Text state = new Text("State: "+rs.getObject(6).toString());
            Text date = new Text("Date: "+this.date.toString());
            Text stage = new Text("Stage: "+rs.getString(7));

            left.getChildren().addAll(artist, genre, state, date, stage);
        } catch (SQLException e) {
            errorLabel.setText("Could not connect to the database");
            e.printStackTrace();
        }
        return left;
    }

    private VBox createRight() {
        VBox right = new VBox();
        right.setMaxWidth(150);
        right.setSpacing(25);

        String userType = app.getUser().getUserType();

        //TODO make pretty/generic
        Button accept = new Button("Accept");
        accept.setPrefWidth(Double.MAX_VALUE);
        accept.setOnAction(event -> {
            switch (userType) {
                case "administrator":
                    try {
                        Offer.changeStatus(Offer.offerState.accepted, offerID, app.getDatabaseHandler());
                        errorLabel.setText("State changed");
                    } catch (SQLException e) {
                        errorLabel.setText("Could not connect to database");
                        e.printStackTrace();
                    }
                    break;
                default:
                    errorLabel.setText("You are not allowed to perform this action");
            }
        });

        Button decline = new Button("Decline");
        decline.setPrefWidth(Double.MAX_VALUE);
        decline.setOnAction(event -> {
            switch (userType) {
                case "administrator":
                case "booker":
                    try {
                        Offer.changeStatus(Offer.offerState.declined, offerID, app.getDatabaseHandler());
                        errorLabel.setText("State changed");
                    } catch (SQLException e) {
                        errorLabel.setText("Could not connect to database");
                        e.printStackTrace();
                    }
                    break;
                default:
                    errorLabel.setText("You are not allowed to perform this action");
            }
        });

        Button book = new Button("Book");
        book.setPrefWidth(Double.MAX_VALUE);
        book.setOnAction(event -> {
            switch (userType) {
                case "administrator":
                case "booker":
                    try {
                        if (Event.checkAvailable(stageID, this.date, app.getDatabaseHandler())) {
                            Offer.changeStatus(Offer.offerState.booked, offerID, app.getDatabaseHandler());
                            errorLabel.setText("State changed");
                        } else {
                            errorLabel.setText("Stage not available on this date");
                        }
                    } catch (SQLException e) {
                        errorLabel.setText("Could not connect to database");
                        e.printStackTrace();
                    }
                    break;
                default:
                    errorLabel.setText("You are not allowed to perform this action");
            }
        });

        right.getChildren().addAll(accept, decline, book);
        return right;
    }

    private static ResultSet getLeftContent(DatabaseHandler dbh, Integer concertID) throws SQLException {
        String query = "SELECT offer.offerID, startDate, artist.artistID, artist.name, " +
                "genre, state, stage.name, concert.stageID " +
                "FROM concert, artist, offer, stage " +
                "WHERE concert.artistID = artist.artistID " +
                "AND concert.offerID = offer.offerID " +
                "AND concert.stageID = stage.stageID " +
                "AND concertID = ? ";
        PreparedStatement prepStatement = dbh.prepareQuery(query);
        prepStatement.setInt(1, concertID);
        return prepStatement.executeQuery();
    }
}
