package webapp.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import webapp.entity.ShoppingCartItem;
import webapp.entity.User;
import webapp.entity.UserType;
import webapp.service.ProductService;
import webapp.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {
  @InjectMocks
  private final ClientController clientController = new ClientController();

  @Mock
  private final UserService userService = new UserService();

  @Mock
  private final ProductService productService = new ProductService();

  @Mock
  private KeycloakAuthenticationToken authentication;

  @BeforeEach
  void injectDependencies() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testHomePage() {
    User test = new User();
    test.setBlocked(false);
    when(userService.saveUserFromToken(authentication, UserType.CLIENT)).thenReturn(test);
    assertEquals(clientController.homePage(authentication), "/client/home");
  }

  @Test
  public void testHomePageBlocked() {
    User test = new User();
    test.setBlocked(true);
    when(userService.saveUserFromToken(authentication, UserType.CLIENT)).thenReturn(test);
    assertEquals(clientController.homePage(authentication), "redirect:/client/blocked");
  }

  @Test
  public void testBlocked() {
    assertEquals(clientController.blockedPage(), "client/blocked");
  }

  @Test
  public void testShoppingCartPage() {
    assertEquals(clientController.shoppingCartPage(), "/client/shopping-cart");
  }

  @Test
  public void testGetShoppingCartItems() {
    List<ShoppingCartItem> items = new ArrayList<>();
    items.add(new ShoppingCartItem());
    when(productService.allShoppingCartItems(1)).thenReturn(items);
    when(userService.getUserId(authentication)).thenReturn(1);
    Gson gson = new Gson();
    assertEquals(clientController.getShoppingCartItems(authentication), gson.toJson(items.toArray(new ShoppingCartItem[] {})));
  }

  @Test
  public void testRedirection() {
    assertEquals(clientController.registration(), "redirect:/client/home");
  }
}
