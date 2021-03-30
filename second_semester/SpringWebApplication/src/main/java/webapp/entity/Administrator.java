package webapp.entity;

public class Administrator extends User {
  public Administrator(int id, String name, String password) {
    super(id, UserType.ADMINISTRATOR, name, password, false);
  }

  public Administrator(int id, String name, String password, boolean blocked) {
    super(id, UserType.ADMINISTRATOR, name, password, blocked);
  }
}
