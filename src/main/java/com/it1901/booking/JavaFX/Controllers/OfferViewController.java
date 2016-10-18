package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Concert.Email.Email;
import com.it1901.booking.Application.Concert.Offer.Concert;
import com.it1901.booking.Application.Concert.Offer.ConcertHandler;
import com.it1901.booking.Application.Concert.Offer.Offer;
import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.JavaFX.BookingApp;
import com.it1901.booking.JavaFX.Controllers.Elements.NavBar;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    private Offer.offerState startingState;

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
            ResultSet rs = SearchHandler.getConcertInformation(app.getDatabaseHandler(), eventID);
            rs.next();

            this.offerID = rs.getInt(1);
            this.date = rs.getDate(2).toLocalDate();
            this.stageID = rs.getInt(8);
            this.startingState = Offer.offerState.valueOf(rs.getString(9));
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
        });

        Button decline = new Button("Decline");
        decline.setPrefWidth(Double.MAX_VALUE);
        decline.setOnAction(event -> {
            switch (userType) {
                case "administrator":
                case "booker":
                    try {
                        //Offer.changeStatus(Offer.offerState.declined, offerID, app.getDatabaseHandler());
                        errorLabel.setText("State changed");
                        app.makeCalendar(this.date);
                    } catch (Exception e) {
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
                        if (ConcertHandler.checkAvailable(stageID, this.date, app.getDatabaseHandler())) {
                            //Offer.changeStatus(Offer.offerState.booked, offerID, app.getDatabaseHandler());
                            errorLabel.setText("State changed");
                            app.makeCalendar(this.date);
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

        if (startingState.equals(Offer.offerState.accepted) ||
                userType.equals("administrator")) {
            Button sendEmail = new Button("Send email");
            sendEmail.setPrefWidth(Double.MAX_VALUE);
            sendEmail.setOnAction(event -> {
                try {
                    Email thisEmail = Email.fetchEmail(offerID, app.getDatabaseHandler());
                    Boolean sent = thisEmail.sendThisEmail(app.getDatabaseHandler());
                    if (sent) {
                        errorLabel.setText("Email was sent successfully");
                        //Offer.changeStatus(Offer.offerState.sent, offerID, app.getDatabaseHandler());
                    } else {
                        errorLabel.setText("Could not find any email for this artist");
                    }
                } catch (SQLException e) {
                    errorLabel.setText("Could not find any emails for this event");
                    e.printStackTrace();
                }
            });
            right.getChildren().add(sendEmail);
        }

        right.getChildren().addAll(accept, decline, book);
        return right;
    }
}
