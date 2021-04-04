package webapp.controller;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import webapp.entity.User;
import webapp.entity.UserType;
import webapp.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {
  @Spy
  @InjectMocks private final AdminController adminController = new AdminController();

  @Mock private final UserService userService = new UserService();

  @Mock
  private KeycloakAuthenticationToken authentication;

  @BeforeEach
  void injectDependencies() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testBlockUser() {
    User user = new User();
    user.setId(1);
    user.setName("testUser");
    when(userService.getUserByUsername("testUser")).thenReturn(user);
    when(userService.saveUser(user)).thenReturn(true);

    doAnswer(i->{
      user.setBlocked(true);
      return null;
    }).when(userService).blockUser(1L);

    adminController.blockUser(1L);
    assertTrue(user.isBlocked());
  }

  @Test
  public void testHomePage() {
    assertEquals(adminController.homePage(authentication), "/administrator/home");
  }

  static class PrincipalMock implements Principal {
    @Override
    public String getName() {
      return "XXXXXXX";
    }
  }
}
