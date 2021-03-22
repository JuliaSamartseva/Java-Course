import data.Administrator;
import data.Client;
import data.UserType;
import jdbc.DatabaseConnection;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.*;

public class AppTest {
  @Test
  public void testAdministratorHasType() {
    Administrator administrator = new Administrator(1, "test", "password");
    assertNotNull(administrator.getType());
    assertEquals(administrator.getType(), UserType.ADMINISTRATOR);
  }

  @Test
  public void testClientHasType() {
    Client client = new Client(1, "test", "password");
    assertNotNull(client.getType());
    assertEquals(client.getType(), UserType.CLIENT);
  }

  @Test
  public void testDefaultAdministratorIsNotBlocked() {
    Administrator administrator = new Administrator(1, "test", "password");
    assertFalse(administrator.isBlocked());
  }

  @Test
  public void testDefaultClientIsNotBlocked() {
    Client client = new Client(1, "test", "password");
    assertFalse(client.isBlocked());
  }

  @Test
  public void testPostgresqlConnectionExists() throws IOException, SQLException {
    Connection connection = DatabaseConnection.getConnection();
    assertNotNull(connection);
  }
}
