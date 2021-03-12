package data;

public class Member {
  private final Long id;
  private String userName;
  private String password;

  public Member(Long id, String userName, String password) {
    this.id = id;
    this.userName = userName;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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
    Member registeredUser = ((Member) obj);
    return id.equals(registeredUser.id);
  }
}
