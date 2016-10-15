package com.it1901.booking.JavaFX.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InfoController extends VBox {

    @FXML
    private Text artist;

    @FXML
    private Text genre;

    @FXML
    private Text state;

    @FXML
    private Text date;

    @FXML
    private Text stage;

/*
    private VBox createLeft() {
        VBox left = new VBox();
        left.setMinWidth(150);
        left.setSpacing(10);

        try {
            ResultSet rs = getLeftContent(app.getDatabaseHandler(), eventID);
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
*/

    public InfoController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/Info.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            System.out.print("goddammit");
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
    }
}