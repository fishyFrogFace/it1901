package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.Application.Artist;
import com.it1901.booking.Application.Event.Offer.Offer;
import com.it1901.booking.Application.Stage;
import com.it1901.booking.JavaFX.BookingApp;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class OfferController {
    private final BorderPane mainContainer;
    private final BookingApp app;

    public OfferController(BookingApp app) {
        this.mainContainer = new BorderPane();
        this.app = app;
    }

    public void onSubmitClick() throws SQLException{
        Integer offerID = Offer.newOffer(app.getUser().getUserID(), app.getDatabaseHandler());
/*        Event concert = event()
                .withStartDate(datePicker.getValue())
                .withDuration(rs.getInt(3))
                .withTicketPrice(rs.getInt(4))
                .withTicketsSold(rs.getInt(5))
                .withArtistID(rs.getInt(6))
                .withOfferID(rs.getInt(7))
                .withStageID(rs.getInt(8))
                .build();*/
        System.out.println("Offer sent!");
        app.makeDash();
    }

    public BorderPane createOfferContainer() {
        GridPane center = getCenter();
        mainContainer.setCenter(center);

        VBox header = createHeader();
        mainContainer.setTop(header);

        return mainContainer;
    }

    private VBox createHeader() {
        VBox header = new VBox();
        header.setAlignment(Pos.CENTER);

        MenuBar menuBar = createMenu();

        header.getChildren().addAll(menuBar);
        return header;
    }

    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Navigation");
        MenuItem goDash = new MenuItem("Dashboard");
        goDash.setOnAction(event ->  app.makeDash());
        menu.getItems().add(goDash);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private GridPane getCenter() {
        GridPane center = new GridPane();
        VBox choiceFields = new VBox();
        choiceFields.setSpacing(10);

        ChoiceBox<String> artists = new ChoiceBox<>();
        try {
            artists.getItems().setAll(Artist.getArtistNames(app.getDatabaseHandler()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        artists.getSelectionModel().selectFirst();

        ChoiceBox<Stage.stages> stagesChoiceBox = new ChoiceBox<>();
        stagesChoiceBox.getItems().setAll(Stage.stages.values());
        stagesChoiceBox.getSelectionModel().selectFirst();
        choiceFields.getChildren().addAll(artists, stagesChoiceBox);

        center.add(choiceFields, 1, 1);
        return center;
    }

    private VBox choiceFields() {
        VBox choiceFields = new VBox();

        TextField artist = new TextField();

        return choiceFields;
    }
}
