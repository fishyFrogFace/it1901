import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.SearchHandler;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TestSearchHandler {

    private final static DatabaseHandler dbh = new DatabaseHandler(
            "org.postgresql.Driver",
            "jdbc:postgresql://52.40.176.177:5432/booking",
            "team",
            "it1901");

    @Test
    public void testGetArtistKey() {
        try {
            ResultSet actual = SearchHandler.getArtistKey("Bob Marley", dbh);
            actual.next();
            assertEquals("reggae", actual.getString(3));
            assertEquals(5004549, actual.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEventsByGenre() {
        try {
            ResultSet actual = SearchHandler.eventsByGenre("electronic", dbh);
            actual.next();
            assertEquals(1, actual.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBookedInPast() {
        try {
            ResultSet actual = SearchHandler.bookedInPast("Storsalen", dbh);
            actual.next();
            assertEquals("Super Mario", actual.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
