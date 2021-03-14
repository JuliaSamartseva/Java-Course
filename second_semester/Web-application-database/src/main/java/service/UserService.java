package service;

import data.Administrator;
import data.Client;
import data.User;
import data.UserType;
import jdbc.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserService {
  private static final Logger log = Logger.getLogger(UserService.class.getName());

  private static final String userByIdQuery =
      "SELECT users.id, name, type FROM internetshop.public.users WHERE users.id = ?";
  private static final String addUserQuery =
      "INSERT INTO internetshop.public.users(name, password, type) VALUES (?, ?, ?::user_type)";

  public static void registerUser(User user) {
    if (user == null) {
      log.warning("Cannot register user because it was null.");
      return;
    }
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement prepareStatement = connection.prepareStatement(addUserQuery);
      prepareStatement.setString(1, user.getName());
      prepareStatement.setString(2, user.getPassword());
      prepareStatement.setString(3, String.valueOf(user.getType()));
      if (prepareStatement.executeUpdate() <= 0)
        log.warning("Cannot register user.");
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }

  public static User getUser(int id) {
    User user = null;
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement prepareStatement = connection.prepareStatement(userByIdQuery);
      prepareStatement.setInt(1, id);
      ResultSet resultSet = prepareStatement.executeQuery();
      if (resultSet.next()) {
        user = getUserFromResultSet(resultSet);
        log.info("Found user by id.");
      } else log.info("Couldn't find user with the given id.");
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
    return user;
  }

  private static User getUserFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt(1);
    String name = rs.getString(2);
    String password = rs.getString(3);
    UserType type = UserType.valueOf(rs.getString(4));
    if (type == UserType.ADMINISTRATOR) return new Administrator(id, name, password);
    else return new Client(id, name, password);
  }
}
