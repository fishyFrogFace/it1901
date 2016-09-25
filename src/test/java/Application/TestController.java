package Application;

import com.it1901.booking.Application.DatabaseHandler;

public class TestController {
    protected final static DatabaseHandler dbh = new DatabaseHandler(
            "org.postgresql.Driver",
            "jdbc:postgresql://52.40.176.177:5432/booking",
            "team",
            "it1901");
    
}
