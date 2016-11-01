package com.it1901.booking.JavaFX.Controllers.Calendar.ConcertView;

import com.it1901.booking.Application.SearchHandler;
import javafx.fxml.FXML;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.text.Text;

public class ReportController {

    ConcertViewController cvc;

    @FXML
    private Text concertName;

    @FXML
    private Text audience;

    @FXML
    private Text cost;

    @FXML
    private Text result;

    public void load(ConcertViewController concertViewController) throws SQLException {
        this.cvc = concertViewController;
        updateResults(cvc.concert.getConcertID());

    }

    private void updateResults(Integer concertID){
        try{
            ResultSet rs = SearchHandler.getConcertInformation(cvc.app.getDatabaseHandler(), concertID);
            rs.next();

            concertName.setText("Viser resultat fra konsert med " + rs.getString(4) + " den " + rs.getObject(2).toString());
            audience.setText("Publikumstall: " +  rs.getInt(11) +  " / " + rs.getInt(15));
            cost.setText("Kostnad: " + ((rs.getInt(12)*rs.getInt(14)) + rs.getInt(13) + rs.getInt(15)));
            result.setText("Resultat: " + ((rs.getInt(10)*rs.getInt(11)) - ((rs.getInt(12)*rs.getInt(14)) + rs.getInt(13) + rs.getInt(15))));
            System.out.println(audience.getText());
            System.out.println(cost.getText());
            System.out.println(result.getText());
            System.out.println(rs.getInt(10));
            System.out.println(rs.getInt(11));


        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        }



    }
}
