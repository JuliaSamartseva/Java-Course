package service;

import data.*;
import jdbc.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserService {
  private static final Logger log = Logger.getLogger(UserService.class.getName());

  private static final String userByIdQuery =
      "SELECT users.id, name, password, type, blocked FROM internetshop.public.users WHERE users.id = ?";
  private static final String getAllUsersQuery =
      "SELECT users.id, name, password, type, blocked FROM internetshop.public.users";
  private static final String checkUserQuery =
      "SELECT users.id, users.type, users.blocked FROM internetshop.public.users WHERE name = ? AND password = internetshop.public.crypt(?, password)";
  private static final String addUserQuery =
      "INSERT INTO internetshop.public.users(name, password, blocked, type) VALUES (?, internetshop.public.crypt(?, internetshop.public.gen_salt('bf')), ?, ?::user_type)";
  private static final String changeBlockUserQuery =
      "UPDATE internetshop.public.users SET blocked = ? WHERE id = ?";

  public static void editBlocked(int id, boolean blocked) {
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement prepareStatement = connection.prepareStatement(changeBlockUserQuery);
      prepareStatement.setBoolean(1, blocked);
      prepareStatement.setInt(2, id);
      if (prepareStatement.executeUpdate() <= 0) log.warning("Cannot change blocked status.");
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }

  public static void registerUser(User user) {
    if (user == null) {
      log.warning("Cannot register user because it was null.");
      return;
    }
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement prepareStatement = connection.prepareStatement(addUserQuery);
      prepareStatement.setString(1, user.getName());
      prepareStatement.setString(2, user.getPassword());
      prepareStatement.setBoolean(3, false);
      prepareStatement.setString(4, String.valueOf(user.getType()).toUpperCase());
      if (prepareStatement.executeUpdate() <= 0) log.warning("Cannot register user.");
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }

  public static UserInfo findUser(String name, String password) {
    log.info("Checking username and password");
    try (Connection connection = DatabaseConnection.getConnection()) {
      log.info("Connected to the database.");
      PreparedStatement ps = connection.prepareStatement(checkUserQuery);
      ps.setString(1, name);
      ps.setString(2, password);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        int id = rs.getInt(1);
        UserType type = UserType.valueOf(rs.getString(2));
        boolean blocked = rs.getBoolean(3);
        log.info("Found user, redirecting to " + type + " page");
        return new UserInfo(id, type, blocked);
      }
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static List<User> getClientUsers() {
    log.info("Getting client users from the database.");
    List<User> users = new ArrayList<>();
    try (Connection connection = DatabaseConnection.getConnection()) {
      log.info("Connected to the database.");
      PreparedStatement ps = connection.prepareStatement(getAllUsersQuery);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        User user = getUserFromResultSet(rs);
        if (user.getType() == UserType.CLIENT) users.add(user);
      }
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
    return users;
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
    boolean blocked = rs.getBoolean(5);
    if (type == UserType.ADMINISTRATOR) return new Administrator(id, name, password, blocked);
    else return new Client(id, name, password, blocked);
  }

  public static class UserInfo {
    public int id;
    public UserType type;
    public boolean blocked;

    public UserInfo(int id, UserType type, boolean blocked) {
      this.id = id;
      this.type = type;
      this.blocked = blocked;
    }
  }
}
