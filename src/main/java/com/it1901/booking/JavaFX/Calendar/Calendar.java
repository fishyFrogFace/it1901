package com.it1901.booking.JavaFX.Calendar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

import java.time.format.DateTimeFormatter;

import com.it1901.booking.Application.DatabaseHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import com.it1901.booking.Application.Stage.stages;

public class Calendar {

    private final LocalDate basis;
    private final LocalDate startOfWeek;
    private final LocalDate endOfWeek;
    private GridPane calGrid = new GridPane();

    public Calendar(LocalDate basis) {
        this.basis = LocalDate.now();
        this.startOfWeek = basis.minusDays(basis.getDayOfWeek().getValue() - 1);
        this.endOfWeek = startOfWeek.plusDays(6);
    }

    //returns a calendar GridPane loaded with this week's concerts
	public GridPane createCalendar(DatabaseHandler dbh) {

        calGrid.getStyleClass().addAll("pane", "grid");

        this.loadConcerts(dbh);
        this.setDateLabels();
        this.setStageLabels();

		return calGrid;
	}

	private void loadConcerts(DatabaseHandler dbh) {
        try {
            ResultSet rs = getCalendarContent(basis, startOfWeek, endOfWeek, dbh);
            rs.next();
            for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
                int slotIndex = 1;

                for (stages stage : stages.values()) {
                    VBox eventsToday = new VBox();
                    eventsToday.getStyleClass().addAll("pane", "vbox");

                    while (
                            !rs.isAfterLast() &&
                                    rs.getDate(2).toLocalDate().equals(date) &&
                                    rs.getString(7).equals(stage.toString())
                            ) {
                        eventsToday.getChildren().add(concertButton(eventsToday, rs));
                        rs.next();
                    }

                    calGrid.add(eventsToday, date.getDayOfWeek().getValue(), slotIndex);
                    slotIndex++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private static ResultSet getCalendarContent(LocalDate basis, LocalDate startOfWeek,
												LocalDate endOfWeek, DatabaseHandler dbh) throws SQLException {
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

	private Button concertButton(VBox eventsToday, ResultSet rs) throws SQLException {
        String concertText = rs.getString(4) + '\n' + rs.getString(5) + '\n' + rs.getString(6);
        Button newBtn = new ConcertButton(concertText, rs.getInt(1));

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

	private void setDateLabels() {
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E\nMMM d");

        for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
            Label label = new Label(date.format(dayFormatter));
            label.setPadding(new Insets(1));
            label.setTextAlignment(TextAlignment.CENTER);
            GridPane.setHalignment(label, HPos.CENTER);
            calGrid.add(label, date.getDayOfWeek().getValue(), 0);
        }
    }

    private void setStageLabels() {
        int slotIndex = 1;
        for (stages stage : stages.values()) {
            Label label = new Label(stage.toString());
            label.setPadding(new Insets(2));
            GridPane.setHalignment(label, HPos.RIGHT);
            calGrid.add(label, 0, slotIndex);
            slotIndex++;
        }
    }
}