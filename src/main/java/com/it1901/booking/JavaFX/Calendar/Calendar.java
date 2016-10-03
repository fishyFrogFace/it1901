package com.it1901.booking.JavaFX.Calendar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

import java.time.format.DateTimeFormatter;

import com.it1901.booking.Application.DatabaseHandler;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import com.it1901.booking.Application.Stage.stages;

public class Calendar extends Application {

	@Override
    //TODO break into smaller methods and remove main
    //is going to be changed to a method that returns the calendar GridPane
    //so it can be embedded in other elements as child
	public void start(Stage primaryStage) {
		DatabaseHandler dbh = new DatabaseHandler(
				"org.postgresql.Driver",
				"jdbc:postgresql://52.40.176.177:5432/booking",
				"team",
				"it1901");

		GridPane calendar = new GridPane();
        calendar.getStyleClass().addAll("pane", "grid");

        //FIXME remove adding of a day to today
		LocalDate today = LocalDate.now().plusDays(1);
		LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
		LocalDate endOfWeek = startOfWeek.plusDays(6);

		try {
			ResultSet rs = getCalendarContent(today, startOfWeek, endOfWeek, dbh);
            rs.next();
			for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
				int slotIndex = 1;

				for (stages stage : stages.values()) {
					VBox eventsToday = new VBox();
                    eventsToday.getStyleClass().addAll("pane", "vbox");

                    //loop through grid works now
                    while (
                            !rs.isAfterLast() &&
                            rs.getDate(2).toLocalDate().equals(date) &&
                            rs.getString(7).equals(stage.toString())
                            ) {
                        System.out.println("2. Date: "+date+" startDate: "+rs.getDate(2).toLocalDate().toString());
                        Button newBtn = new Button(' ' + rs.getInt(1) + " fdfk");
                        String state = rs.getString(6);
                        if (state.equals("pending")) {
                            newBtn.setStyle("-fx-base: blue"); //TODO move to css
                        }
                        else if (state.equals("accepted")) {
                            newBtn.setStyle("-fx-base: green");
                        }
                        else if (state.equals("sent")) {
                            newBtn.setStyle("-fx-base: yellow");
                        }
                        else if (state.equals("declined")) {
                            newBtn.setStyle("-fx-base: red");
                        }
                        eventsToday.getChildren().add(newBtn);
                        rs.next();
                    }

					calendar.add(eventsToday, date.getDayOfWeek().getValue(), slotIndex);
					slotIndex++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}



		//labels for dates (top) and stages (side)
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E\nMMM d");

		for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
			Label label = new Label(date.format(dayFormatter));
			label.setPadding(new Insets(1));
			label.setTextAlignment(TextAlignment.CENTER);
			GridPane.setHalignment(label, HPos.CENTER);
			calendar.add(label, date.getDayOfWeek().getValue(), 0);
		}

		int slotIndex = 1;
		for (stages stage : stages.values()) {
			Label label = new Label(stage.toString());
			label.setPadding(new Insets(2));
			GridPane.setHalignment(label, HPos.RIGHT);
			calendar.add(label, 0, slotIndex);
			slotIndex++;
		}

		ScrollPane scroller = new ScrollPane(calendar);

		Scene scene = new Scene(scroller);
		scene.getStylesheets().add(getClass().getResource("/calendar.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
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

	public static void main(String[] args) {
		launch(args);
	}

}