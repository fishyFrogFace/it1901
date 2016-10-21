package com.it1901.booking.JavaFX.Controllers.ConcertView;

import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.Application.TableViewMaker;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import java.sql.SQLException;
import java.util.Arrays;

public class TechController{

    ConcertViewController cvc;

    @FXML
    private AnchorPane tableAnchor;

    public void load(ConcertViewController concertViewController) throws SQLException {
        this.cvc = concertViewController;
        tableAnchor.getChildren().add(
                TableViewMaker.makeTable(
                        SearchHandler.getAssignedTechs(cvc.concert.getConcertID(), cvc.app.getDatabaseHandler()),
                        Arrays.asList("Name", "Role", "Hours")));
    }
}
