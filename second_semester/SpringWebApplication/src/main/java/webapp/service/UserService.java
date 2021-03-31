package webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

@Service
public class UserService implements UserDetailsService {
  @PersistenceContext
  private EntityManager em;
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userRepository.findByName(name);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return user;
  }

  public void blockUser(Long userId) {
    userRepository.changeBlockForUserWithId(userId, true);
  }

  public void unblockUser(Long userId) {
    userRepository.changeBlockForUserWithId(userId, false);
  }

  public User findUserById(Long userId) {
    Optional<User> userFromDb = userRepository.findById(userId);
    return userFromDb.orElse(new User());
  }

  public List<User> allUsers() {
    return userRepository.findAll();
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
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    userRepository.save(user);
    return true;
  }

  public boolean deleteUser(Long userId) {
    if (userRepository.findById(userId).isPresent()) {
      userRepository.deleteById(userId);
      return true;
    }
    return false;
  }
}