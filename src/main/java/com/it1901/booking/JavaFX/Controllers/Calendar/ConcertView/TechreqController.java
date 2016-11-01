package com.it1901.booking.JavaFX.Controllers.Calendar.ConcertView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

import com.it1901.booking.Application.SearchHandler;
import com.it1901.booking.Application.TableViewMaker;

import com.it1901.booking.JavaFX.Controllers.Calendar.ConcertView.ConcertViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
			TableView table = TableViewMaker.makeTable(rs, Arrays.asList("Name", "Item", "Description", "Comment"));
			for (Object col: table.getColumns()) {
				if (((TableColumn)col).getText().equals("Description")){
					((TableColumn) col).setPrefWidth(400);
				}
			}
			tableAnchor.getChildren().add(table);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
