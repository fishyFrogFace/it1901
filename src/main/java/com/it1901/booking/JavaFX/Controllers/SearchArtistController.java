package com.it1901.booking.JavaFX.Controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.it1901.booking.Application.ArtistInfo;

import com.it1901.booking.Application.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

public class SearchArtistController extends Controller implements Initializable {
    private ArtistInfo ainfo = new ArtistInfo();
    @FXML
    private TextField nameString;
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
/*        try {
            //todo input some stuff here
        }
        catch(SQLException e){
            e.printStackTrace();
        }*/
    }
}
