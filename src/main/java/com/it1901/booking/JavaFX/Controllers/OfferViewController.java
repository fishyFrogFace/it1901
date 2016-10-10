package com.it1901.booking.JavaFX.Controllers;

import com.it1901.booking.JavaFX.BookingApp;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class OfferViewController {
    private final Integer width = 160;
    private final BorderPane mainContainer;
    private final BookingApp app;
    private final TextField price;

    public OfferViewController(BookingApp app) {
        this.mainContainer = new BorderPane();
        this.price = new TextField();
        this.app = app;
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

    private VBox createLeft() {
        return new VBox();
    }

    private VBox createRight() {
        return new VBox();
    }
}
