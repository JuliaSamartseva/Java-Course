package data;

public class User {
  private final Long id;
  private final UserType type;
  private String name;

  public User(Long id, String name, UserType type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public UserType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    User registeredUser = ((User) obj);
    return id.equals(registeredUser.id);
  }
}
