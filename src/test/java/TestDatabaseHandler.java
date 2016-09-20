import com.it1901.booking.Application.DatabaseHandler;
import org.junit.Test;
import org.junit.runner.Result;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TestDatabaseHandler {

    private DatabaseHandler dbh = new DatabaseHandler(
            "org.postgresql.Driver",
            "jdbc:postgresql://52.40.176.177:5432/booking",
            "team",
            "it1901");

    @Test
    public void testGetUser() {
        try {
            ResultSet actual = dbh.getUser("acme");
            actual.next();
            assertEquals("Wile E. Coyote", actual.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //unsure about how to test for successful insertions
/*    @Test
    public void testCreateOffer() {
        try {
            dbh.createOffer(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

/*    @Test
    public void testCreateEvent() {
        try {
            dbh.createEvent(LocalDate.now(), 5, 200, 2, 2, 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

/*    @Test
    public void testCreateEvent() {
        try {
            dbh.createEmail("test subject", "test body", 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void testGetArtistKey() {
        try {
            ResultSet actual = dbh.getArtistKey("Bob Marley");
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
            ResultSet actual = dbh.eventsByGenre("electronic");
            actual.next();
            assertEquals(1, actual.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
