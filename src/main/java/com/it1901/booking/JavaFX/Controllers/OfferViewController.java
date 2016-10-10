package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.JavaFX.BookingApp;
import javafx.scene.control.*;
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

    public OfferViewController(BookingApp app, Integer eventID) {
        this.mainContainer = new BorderPane();
        this.app = app;
        this.eventID = eventID;
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
        return center;
    }

    private VBox createLeft() {
        VBox left = new VBox();
        left.setMinWidth(150);

        try {
            ResultSet rs = getLeftContent(app.getDatabaseHandler(), eventID);
            //concertID, startDate, artist.artistID, artist.name, genre, state, stage.name
            
        } catch (SQLException e) {
            //TODO errorlabel
            e.printStackTrace();
        }

        return left;
    }

    private VBox createRight() {
        VBox right = new VBox();
        right.setMaxWidth(150);
        right.setSpacing(10);

        Button accept = new Button("Accept");
        accept.setPrefWidth(Double.MAX_VALUE);

        Button decline = new Button("Decline");
        decline.setPrefWidth(Double.MAX_VALUE);

        Button book = new Button("Book");
        book.setPrefWidth(Double.MAX_VALUE);

        right.getChildren().addAll(accept, decline, book);
        return right;
    }

    private static ResultSet getLeftContent(DatabaseHandler dbh, Integer concertID) throws SQLException {
        String query = "SELECT concertID, startDate, artist.artistID, artist.name, genre, state, stage.name " +
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
