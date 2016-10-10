package com.it1901.booking.JavaFX.Controllers.Calendar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

import java.time.format.DateTimeFormatter;

import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.Stage;
import com.it1901.booking.JavaFX.BookingApp;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import com.it1901.booking.Application.Stage.stages;

public class Calendar {

    private final LocalDate startOfWeek;
    private final LocalDate endOfWeek;
    private final GridPane calGrid = new GridPane();
    private final BookingApp app;

    public Calendar(LocalDate basis, BookingApp app) {
        this.startOfWeek = basis.minusDays(basis.getDayOfWeek().getValue() - 1);
        this.endOfWeek = startOfWeek.plusDays(6);
        this.app = app;
    }

    //returns a calendar GridPane loaded with this week's concerts
	public GridPane createCalendar(BookingApp app) throws SQLException {

        calGrid.getStyleClass().addAll("pane", "grid");

        this.loadConcerts();
        this.setDateLabels();
        this.setStageLabels();

		return calGrid;
	}

	private void loadConcerts() throws SQLException {
            ResultSet rs = getCalendarContent(startOfWeek, endOfWeek, app.getDatabaseHandler());

            Boolean noConcerts = rsIsEmpty(rs);

            for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
                int slotIndex = 1;

                for (stages stage : stages.values()) {
                    VBox eventsToday = addGridBox(date, stage, app);
                    if (!noConcerts) {
                        while (
                                !rs.isAfterLast() &&
                                        rs.getDate(2).toLocalDate().equals(date) &&
                                        rs.getString(7).equals(stage.toString())
                                ) {
                            Button btn = concertButton(rs);
                            eventsToday.getChildren().add(btn);
                            rs.next();
                        }
                    }
                    calGrid.add(eventsToday, date.getDayOfWeek().getValue(), slotIndex);
                    slotIndex++;
                }
            }
    }

    private Boolean rsIsEmpty(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return false;
        } else {
            return true;
        }
    }

	private static ResultSet getCalendarContent(LocalDate startOfWeek, LocalDate endOfWeek,
                                                DatabaseHandler dbh) throws SQLException {
		String query = "SELECT concertID, startDate, artist.artistID, artist.name, genre, state, stage.name " +
				"FROM concert, artist, offer, stage " +
				"WHERE concert.artistID = artist.artistID " +
				"AND concert.offerID = offer.offerID " +
				"AND concert.stageID = stage.stageID " +
				"AND startDate > ? " +
				"AND startDate < ? " +
				"ORDER BY startDate, stage.name";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setObject(1, startOfWeek.minusDays(1));
		prepStatement.setObject(2, endOfWeek.plusDays(1));
		return prepStatement.executeQuery();
	}

	private Button concertButton(ResultSet rs) throws SQLException {
        String concertText = rs.getString(4) + '\n' + rs.getString(5) + '\n' + rs.getString(6);
        Button newBtn = new ConcertButton(concertText, rs.getInt(1));
        newBtn.setOnAction(event -> app.makeOfferView());
        newBtn.setPrefWidth(Double.MAX_VALUE);
        newBtn.setMinHeight(60);

        //TODO modify states according to usecase
        switch (rs.getString(6)) {
            case "pending":
                newBtn.getStyleClass().addAll("concert", "pending");
                break;
            case "accepted":
                newBtn.getStyleClass().addAll("concert", "accepted");
                break;
            case "sent":
                newBtn.getStyleClass().addAll("concert", "sent");
                break;
            case "declined":
                newBtn.getStyleClass().addAll("concert", "declined");
                break;
        }
        return newBtn;
    }

    public GridBox addGridBox(LocalDate date, Stage.stages stage, BookingApp app) {
        GridBox eventsToday = new GridBox(date, stage);
        eventsToday.getStyleClass().addAll("pane", "vbox");
        eventsToday.setPrefWidth(110);
        eventsToday.setMinHeight(60);
        eventsToday.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                app.makeOffer(date, stage));
        return eventsToday;
    }

	private void setDateLabels() {
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E\nMMM d");

        for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
            Text dateText = new Text(date.format(dayFormatter));
            dateText.setTextAlignment(TextAlignment.CENTER);
            dateText.getStyleClass().addAll("pane", "label");
            GridPane.setHalignment(dateText, HPos.CENTER);
            calGrid.add(dateText, date.getDayOfWeek().getValue(), 0);
        }
    }

    private void setStageLabels() {
        int slotIndex = 1;
        for (stages stage : stages.values()) {
            Label label = new Label(stage.toString());
            label.setPadding(new Insets(2));
            GridPane.setHalignment(label, HPos.CENTER);
            calGrid.add(label, 0, slotIndex);
            slotIndex++;
        }
    }
}