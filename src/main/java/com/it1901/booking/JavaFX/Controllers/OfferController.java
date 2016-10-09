package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Artist;
import com.it1901.booking.Application.Event.Offer.Event;
import com.it1901.booking.Application.Event.Offer.EventBuilder;
import com.it1901.booking.Application.Event.Offer.Offer;
import com.it1901.booking.Application.Stage;
import com.it1901.booking.JavaFX.BookingApp;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.time.LocalDate;

public class OfferController {
    private final Integer width = 160;
    private final BorderPane mainContainer;
    private final BookingApp app;
    private final DatePicker datePicker;
    private final TextField price;
    private final ChoiceBox<Stage.stages> stages;
    private final ChoiceBox<String> artists;
    private Text errorLabel;

    public OfferController(BookingApp app) {
        this.mainContainer = new BorderPane();
        this.datePicker = new DatePicker(LocalDate.now());
        datePicker.setPrefWidth(width);
        this.price = new TextField();
        price.setPrefWidth(width);
        this.app = app;
        this.errorLabel = new Text();
        this.stages = fillStages();
        this.artists = fillArtists();
    }

    public BorderPane createOfferContainer() {
        mainContainer.setCenter(getCenter());

        MenuBar header = NavBar.createMenu(app);
        mainContainer.setTop(header);

        return mainContainer;
    }

    private GridPane getCenter() {
        GridPane center = new GridPane();
        center.setVgap(5);
        center.setHgap(5);

        center.add(new Text("Artist: "), 0, 0);
        center.add(new Text("Stage: "), 0, 1);
        center.add(new Text("Date: "), 0, 2);
        center.add(new Text("Ticket price: "), 0, 3);

        //TODO make prettier
        center.add(artists, 1, 0);
        center.add(stages, 1, 1);
        center.add(datePicker, 1, 2);
        center.add(price, 1, 3);
        center.add(submit(), 1, 4);
        center.add(errorLabel, 1, 5);

        //TODO add button -> calendar, calendar -> this, newArtist -> new artist
        Button newArtist = new Button("New Artist");
        center.add(newArtist, 2, 0);

        BorderPane.setMargin(center, new Insets(40));
        center.setGridLinesVisible(true);
        return center;
    }

    private ChoiceBox<String> fillArtists() {
        ChoiceBox<String> artists = new ChoiceBox<>();
        try {
            artists.getItems().setAll(Artist.getArtistNames(app.getDatabaseHandler()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        artists.setPrefWidth(160);
        artists.getSelectionModel().selectFirst();
        return artists;
    }

    private ChoiceBox<Stage.stages> fillStages() {
        ChoiceBox<Stage.stages> stagesChoiceBox = new ChoiceBox<>();
        stagesChoiceBox.setPrefWidth(160);
        stagesChoiceBox.getItems().setAll(Stage.stages.values());
        stagesChoiceBox.getSelectionModel().selectFirst();
        return stagesChoiceBox;
    }

    private Button submit() {
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            try {
                Integer stageID = Stage.fetchStageID(app.getDatabaseHandler(), stages.getValue().toString());
                Integer artistID = Artist.fetchArtistID(app.getDatabaseHandler(), artists.getValue());
                //TODO check price for int
                if (Event.checkAvailable(stageID, datePicker.getValue(), app.getDatabaseHandler())) {
                    Integer offerID = Offer.newOffer(app.getUser().getUserID(), app.getDatabaseHandler());
                    Event newEvent = EventBuilder.event()
                            .withOfferID(offerID)
                            .withStageID(stageID)
                            .withArtistID(artistID)
                            .withTicketPrice(Integer.valueOf(price.getText()))
                            .withStartDate(datePicker.getValue())
                            .build();
                    newEvent.newEvent(app.getDatabaseHandler());
                    errorLabel.setText("Offer created");
                } else {
                    errorLabel.setText("Stage not available on this date");
                }
            } catch (SQLException e) {
                errorLabel.setText("Invalid data");
                e.printStackTrace();
            }
        });
        return submitButton;
    }
}
