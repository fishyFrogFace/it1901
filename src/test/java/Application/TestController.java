package Application;

import com.it1901.booking.Application.DatabaseHandler;

import java.sql.SQLException;

public class TestController {
    protected static DatabaseHandler dbh = null;

    static {
        try {
            dbh = new DatabaseHandler(
                        "org.postgresql.Driver",
                        "jdbc:postgresql://localhost:5432/booking",
                        "team",
                        "it1901");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
