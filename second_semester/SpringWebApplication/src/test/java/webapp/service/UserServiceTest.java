package webapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webapp.entity.Role;
import webapp.entity.User;
import webapp.entity.UserType;
import webapp.repository.RoleRepository;
import webapp.repository.UserRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
  @InjectMocks private final UserService userService = new UserService();
  @Mock KeycloakAuthenticationToken authentication;
  @Mock AccessToken accessToken;
  @Mock SimpleKeycloakAccount account;
  @Mock RefreshableKeycloakSecurityContext context;
  @Captor ArgumentCaptor<String> captor;
  @Mock private UserRepository userRepository;
  @Mock private RoleRepository roleRepository;

  @Test
  public void testGetUserId() {
    User user = new User();
    user.setId(2);
    user.setName("user");
    when(authentication.getDetails()).thenReturn(account);
    when(account.getKeycloakSecurityContext()).thenReturn(context);
    when(context.getToken()).thenReturn(accessToken);
    when(accessToken.getPreferredUsername()).thenReturn("user");
    when(userRepository.findByName("user")).thenReturn(user);
    assertEquals(userService.getUserId(authentication), user.getId());
    verify(userRepository, times(1)).findByName(captor.capture());
  }

  @Test
  public void testSaveUser() {
    User user = new User();
    user.setName("name");
    user.setType(UserType.CLIENT);
    when(userRepository.findByName("name")).thenReturn(null);
    assertTrue(userService.saveUser(user));
    user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
    verify(userRepository, times(1)).save(user);
  }

  @Test
  public void testSaveUserFoundInDB() {
    User user = new User();
    user.setName("name");
    when(userRepository.findByName("name")).thenReturn(user);
    assertFalse(userService.saveUser(user));
  }

  @Test
  public void testBlockUser() {
    userService.blockUser(1L);
    verify(userRepository, times(1)).changeBlockForUserWithId(1L, true);
  }

  @Test
  public void testUnblockUser() {
    userService.unblockUser(1L);
    verify(userRepository, times(1)).changeBlockForUserWithId(1L, false);
  }

  @Test
  public void testAllUsers() {
    userService.allUsers();
    verify(userRepository, times(1)).findAll();
  }
}
