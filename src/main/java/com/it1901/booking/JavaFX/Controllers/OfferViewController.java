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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class OfferViewController {
    private final Integer width = 160;
    private final BorderPane mainContainer;
    private final BookingApp app;
    private final TextField price;
    private final ChoiceBox<String> artists;
    private Text errorLabel;

    public OfferViewController(BookingApp app) {
        this.mainContainer = new BorderPane();
        this.price = new TextField();
        price.setPrefWidth(width);
        this.app = app;
        this.errorLabel = createLabel();
        this.artists = fillArtists();
    }

    public BorderPane createOfferViewContainer() {
        mainContainer.setCenter(getCenter());

        MenuBar header = NavBar.createMenu(app);
        mainContainer.setTop(header);

        return mainContainer;
    }

    private SplitPane getCenter() {
        SplitPane center = new SplitPane();
        final StackPane sp1 = new StackPane();
        sp1.getChildren().add(new Button("Button One"));
        final StackPane sp2 = new StackPane();
        sp2.getChildren().add(new Button("Button Two"));
        final StackPane sp3 = new StackPane();
        sp3.getChildren().add(new Button("Button Three"));
        center.getItems().addAll(sp1, sp2, sp3);
        return center;
    }
}
