package data;

public class Administrator extends User {
  public Administrator(Long id, String name) {
    super(id, name, UserType.ADMINISTRATOR);
  }
}
