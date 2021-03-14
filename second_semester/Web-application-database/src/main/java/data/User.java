package data;

public class User {
  private int id;
  private final UserType type;
  private String name;
  private String password;

  public User(String name, String password, UserType type) {
    this.name = name;
    this.password = password;
    this.type = type;
  }
  public User(int id, String name, String password, UserType type) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public UserType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    User registeredUser = ((User) obj);
    return id == registeredUser.id;
  }
}
