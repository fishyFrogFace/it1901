package com.it1901.booking.JavaFX.Controllers.ConcertView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.Application.TableViewMaker;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class TechreqController {
	ConcertViewController cvc;
	
    @FXML
    AnchorPane tableAnchor;

	public void load(ConcertViewController concertViewController) {
        this.cvc = concertViewController;
        getTechReq(cvc.concert.getConcertID());
    }
	
	public void getTechReq(Integer concertID) {
		tableAnchor.getChildren().clear();
		try {
			ResultSet rs = SearchHandler.getTechReq(concertID, cvc.app.getDatabaseHandler());
			tableAnchor.getChildren().add(TableViewMaker.makeTable(rs, Arrays.asList("Name", "Item", "Description", "Comment")));
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
