package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.DatabaseHandler;
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

public class OfferViewController {
    private final BorderPane mainContainer;
    private final BookingApp app;
    private final Integer eventID;
    private final Text errorLabel;

    public OfferViewController(BookingApp app, Integer eventID) {
        this.mainContainer = new BorderPane();
        this.app = app;
        this.eventID = eventID;
        this.errorLabel = new Text();
    }

    public BorderPane createOfferViewContainer() {
        mainContainer.setCenter(getCenter());

        MenuBar header = NavBar.createMenu(app);
        mainContainer.setTop(header);

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
            Integer offerID = rs.getInt(1);
            Text artist = new Text("Artist: "+rs.getString(4));
            Text genre = new Text("Genre: "+rs.getObject(5).toString());
            Text state = new Text("State: "+rs.getObject(6).toString());
            Text date = new Text("Date: "+rs.getObject(2).toString());
            Text stage = new Text("Stage: "+rs.getString(7));

            left.getChildren().addAll(artist, genre, state, date, stage);
        } catch (SQLException e) {
            //TODO errorlabel
            e.printStackTrace();
        }
        return left;
    }

    private VBox createRight() {
        VBox right = new VBox();
        right.setMaxWidth(150);
        right.setSpacing(25);

        String userType = app.getUser().getUserType();

        Button accept = new Button("Accept");
        accept.setPrefWidth(Double.MAX_VALUE);
        accept.setOnAction(event -> {
            switch (userType) {
                case "administrator":
                    System.out.println("yo");
                    break;
                default:
                    errorLabel.setText("You are not allowed to perform this action");
            }
        });

        Button decline = new Button("Decline");
        decline.setPrefWidth(Double.MAX_VALUE);
        decline.setOnAction(event -> {
            System.out.println("2");
        });

        Button book = new Button("Book");
        book.setPrefWidth(Double.MAX_VALUE);
        book.setOnAction(event -> {
            System.out.println("3");
        });

        right.getChildren().addAll(accept, decline, book);
        return right;
    }

    private static ResultSet getLeftContent(DatabaseHandler dbh, Integer concertID) throws SQLException {
        String query = "SELECT offer.offerID, startDate, artist.artistID, artist.name, genre, state, stage.name " +
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
