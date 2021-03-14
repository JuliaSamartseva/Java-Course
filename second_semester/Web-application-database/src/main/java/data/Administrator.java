package data;

public class Administrator extends User {
  public Administrator(int id, String name, String password) {
    super(id, name, password, UserType.ADMINISTRATOR);
  }
}
