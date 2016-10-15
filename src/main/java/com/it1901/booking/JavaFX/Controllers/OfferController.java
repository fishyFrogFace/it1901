package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Artist;
import com.it1901.booking.Application.Event.Email.Email;
import com.it1901.booking.Application.Event.Email.EmailBuilder;
import com.it1901.booking.Application.Event.Offer.Event;
import com.it1901.booking.Application.Event.Offer.EventBuilder;
import com.it1901.booking.Application.Event.Offer.Offer;
import com.it1901.booking.Application.PriceGenerator;
import com.it1901.booking.Application.Stage;
import com.it1901.booking.JavaFX.BookingApp;
import com.it1901.booking.JavaFX.Controllers.Elements.NavBar;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.time.LocalDate;

public class OfferController {
    private final Integer width = 160;
    private final BorderPane mainContainer;
    private final BookingApp app;
    private final DatePicker datePicker;
    private final TextField priceField;
    private final ChoiceBox<Stage.stages> stages;
    private final ChoiceBox<String> artists;
    private final TextField subject;
    private final TextArea body;
    private Text errorLabel;

    public OfferController(BookingApp app, LocalDate date, Stage.stages stage) {
        this.mainContainer = new BorderPane();
        this.datePicker = new DatePicker(date);
        datePicker.setPrefWidth(width);
        this.priceField = new TextField();
        priceField.setPrefWidth(width);
        this.app = app;
        this.errorLabel = createLabel();
        this.stages = fillStages(stage);
        this.artists = fillArtists();
        this.subject = new TextField();
        this.body = new TextArea();
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
        center.add(new Text("E-mail subject: "), 0, 4);
        center.add(new Text("E-mail body: "), 0, 5);

        //TODO make prettier
        center.add(artists, 1, 0);
        center.add(stages, 1, 1);
        center.add(datePicker, 1, 2);
        center.add(priceField, 1, 3);
        center.add(subject, 1, 4);
        center.add(body, 1, 5);
        center.add(submit(), 1, 6);
        center.add(errorLabel, 1, 7);

        //TODO add button -> calendar, newArtist -> new artist
        Button newArtist = new Button("New Artist"); //comment out if no time
        //center.add(newArtist, 2, 0);

        //TODO make this automated
        Button calcPrice = new Button("Calculate price");
        calcPrice.setOnAction(event -> {
            try{
                PriceGenerator price = new PriceGenerator();
                String artist = artists.getValue().toLowerCase();
                int fee = price.getArtistFee(artist, app.getDatabaseHandler());
                int scenePrice = price.getScenePrice(stages.getValue().toString(), app.getDatabaseHandler());
                int maxAttendance = price.getSceneCapacity(stages.getValue().toString(), app.getDatabaseHandler());
                float ticketPrice = price.computeTicketPrice(fee, scenePrice, maxAttendance);
                System.out.println("ticketprice:" + ticketPrice);
                priceField.setText(""+Math.round(ticketPrice));
            }
            catch(Exception e){
                System.out.println("Not all values given");
                errorLabel.setText("Må ha noe i begge felter for å kunne gjennomføre søk");
            }
        });
        center.add(calcPrice, 2, 3);

        BorderPane.setMargin(center, new Insets(40));
        center.setGridLinesVisible(false); //for debugging set true
        return center;
    }

    private ChoiceBox<String> fillArtists() {
        ChoiceBox<String> artists = new ChoiceBox<>();
        try {
            artists.getItems().setAll(Artist.getArtistNames(app.getDatabaseHandler()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        artists.setPrefWidth(width);
        artists.getSelectionModel().selectFirst();
        return artists;
    }

    private ChoiceBox<Stage.stages> fillStages(Stage.stages stage) {
        ChoiceBox<Stage.stages> stagesChoiceBox = new ChoiceBox<>();
        stagesChoiceBox.setPrefWidth(width);
        stagesChoiceBox.getItems().setAll(Stage.stages.values());
        stagesChoiceBox.getSelectionModel().select(stage);
        return stagesChoiceBox;
    }

    private Button submit() {
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            try {
                Integer stageID = Stage.fetchStageID(app.getDatabaseHandler(), stages.getValue().toString());
                Integer artistID = Artist.fetchArtistID(app.getDatabaseHandler(), artists.getValue());
                String priceVal = priceField.getText();
                if (Event.checkAvailable(stageID, datePicker.getValue(), app.getDatabaseHandler())) {
                    if (priceVal.matches("\\d+")) { //check for integer
                        Integer offerID = Offer.newOffer(app.getUser().getUserID(), app.getDatabaseHandler());
                        Event newEvent = EventBuilder.event()
                                .withOfferID(offerID)
                                .withStageID(stageID)
                                .withArtistID(artistID)
                                .withTicketPrice(Integer.valueOf(priceVal)) //fails for numbers larger than an int
                                .withStartDate(datePicker.getValue())
                                .build();
                        Email email = EmailBuilder.email()
                                .withEmailSubject(subject.getText())
                                .withEmailBody(body.getText())
                                .withOfferID(offerID)
                                .build();
                        email.saveEmail(app.getDatabaseHandler());
                        newEvent.newEvent(app.getDatabaseHandler());
                        errorLabel.setText("Offer created");
                        errorLabel.setFill(Paint.valueOf("black"));
                    } else {
                        errorLabel.setText("Ticket price is not valid"); //css doesn't paint this red wat
                    }
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

    private Text createLabel() {
        Text label = new Text();
        label.getStyleClass().addAll("standard", "label");
        return label;
    }
}
