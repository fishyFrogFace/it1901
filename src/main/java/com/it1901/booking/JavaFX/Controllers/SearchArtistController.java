package com.it1901.booking.JavaFX.Controllers;

import java.sql.SQLException;

import com.it1901.booking.Application.ArtistInfo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SearchArtistController extends Controller {
    private ArtistInfo ainfo = new ArtistInfo();
    @FXML
    private TextField nameString; //TODO name hints
    @FXML
    private TextArea result;
    @FXML
    private TextArea result1;


    public void onSearchClick() throws SQLException {
        result.setText(ainfo.getArtistInfo(nameString.getText())); //TODO cleanup, use methods in Search handler instead
        result1.setText(ainfo.getPrevConcerts(nameString.getText())); //TODO cleanup, use methods in Search handler instead
    }

    public void openArtistView() {
        app.makeArtistView(nameString.getText()); //TODO verify string
    }
}
