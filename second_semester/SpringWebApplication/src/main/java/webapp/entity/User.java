package webapp.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private int id;

  @Enumerated(EnumType.STRING)
  private UserType type;

  @Size(min = 3, max = 20, message = "min = 3, max = 20 symbols")
  @NotNull(message = "Name cannot be null")
  private String name;

  @Size(min = 3, max = 20, message = "min = 3, max = 20 symbols")
  @NotNull(message = "Password cannot be null")
  private String password;

  private boolean blocked;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Role> roles;

  public User() {}

  public User(int id, UserType type, String name, String password, boolean blocked) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.password = password;
    this.blocked = blocked;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    User registeredUser = ((User) obj);
    return id == registeredUser.id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !isBlocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public UserType getType() {
    return type;
  }

  public void setType(UserType type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
