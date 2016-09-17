import com.it1901.booking.Application.DatabaseHandler;
import org.junit.Test;
import org.junit.runner.Result;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            ResultSet actual = DatabaseHandler.getUser("acme");
            actual.next();
            assertEquals("Wile E. Coyote", actual.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
