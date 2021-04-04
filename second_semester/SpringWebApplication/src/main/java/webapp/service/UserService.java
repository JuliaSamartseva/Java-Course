package webapp.service;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.entity.Role;
import webapp.entity.User;
import webapp.entity.UserType;
import webapp.repository.RoleRepository;
import webapp.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
  @PersistenceContext
  private EntityManager em;
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;

  public void blockUser(Long userId) {
    userRepository.changeBlockForUserWithId(userId, true);
  }

  public void unblockUser(Long userId) {
    userRepository.changeBlockForUserWithId(userId, false);
  }

  public List<User> allUsers() {
    return userRepository.findAll();
  }

  public User saveUserFromToken(KeycloakAuthenticationToken authentication, UserType userType) {
    SimpleKeycloakAccount account = (SimpleKeycloakAccount) authentication.getDetails();
    AccessToken token = account.getKeycloakSecurityContext().getToken();
    User savedUser = new User();
    savedUser.setName(token.getPreferredUsername());
    savedUser.setType(userType);
    savedUser.setBlocked(false);
    savedUser.setPassword("111");
    if (saveUser(savedUser)) return savedUser;
    else return userRepository.findByName(savedUser.getName());
  }

  public boolean saveUser(User user) {
    User userFromDB = userRepository.findByName(user.getName());

    if (userFromDB != null) {
      return false;
    }

    if (user.getType() == UserType.ADMINISTRATOR) {
      user.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
    } else if (user.getType() == UserType.CLIENT) {
      user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
    }

    userRepository.save(user);
    return true;
  }

  public int getUserId(KeycloakAuthenticationToken authentication) {
    SimpleKeycloakAccount account = (SimpleKeycloakAccount) authentication.getDetails();
    AccessToken token = account.getKeycloakSecurityContext().getToken();
    return userRepository.findByName(token.getPreferredUsername()).getId();
  }
}