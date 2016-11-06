package Application;

import com.it1901.booking.Application.Employee.LoginHandler;
import static Application.TestController.dbh;
import com.it1901.booking.Application.Employee.User;
import org.junit.Test;

import java.sql.SQLException;

import static com.it1901.booking.Application.Employee.User.Role.administrator;
import static org.junit.Assert.assertEquals;

public class TestLoginHandler {

    @Test
    public void testLogin() {
        try {
            User actual = LoginHandler.login("acme", "1234", dbh);
            assertEquals(administrator, actual.getUserRole());
            User notExist = LoginHandler.login("nobody", "password", dbh);
            assertEquals(null, notExist);
            User wrongPassword = LoginHandler.login("acme", "123", dbh);
            assertEquals(null, wrongPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
