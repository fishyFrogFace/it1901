import com.it1901.booking.Application.DatabaseHandler;
import com.it1901.booking.Application.LoginHandler;
import com.it1901.booking.Application.User;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TestLoginHandler {

    private final static DatabaseHandler dbh = new DatabaseHandler(
            "org.postgresql.Driver",
            "jdbc:postgresql://52.40.176.177:5432/booking",
            "team",
            "it1901");

    @Test
    public void testGetUser() {
        try {
            ResultSet actual = LoginHandler.getUser("acme", dbh);
            actual.next();
            assertEquals("Wile E. Coyote", actual.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin() {
        try {
            User actual = LoginHandler.login("acme", "1234", dbh);
            assertEquals("administrator", actual.getUserType());
            User notExist = LoginHandler.login("nobody", "password", dbh);
            assertEquals(null, notExist);
            User wrongPassword = LoginHandler.login("acme", "123", dbh);
            assertEquals(null, wrongPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
