package webapp.controller;

import com.google.gson.Gson;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import webapp.entity.ShoppingCartItem;
import webapp.entity.UserType;
import webapp.service.ProductService;
import webapp.service.UserService;

@Controller
public class ClientController {
  @Autowired private UserService userService;

  @Autowired private ProductService productService;

  @GetMapping("/client/home")
  public String homePage(KeycloakAuthenticationToken authentication) {
    if (userService.saveUserFromToken(authentication, UserType.CLIENT).isBlocked())
      return "redirect:/client/blocked";
    return "/client/home";
  }

  @GetMapping("/client/blocked")
  public String blockedPage() {
    return "client/blocked";
  }

  @GetMapping("/client/shopping-cart")
  public String shoppingCartPage() {
    return "/client/shopping-cart";
  }

  @GetMapping("/add-to-cart/product_id/{id}/quantity/{quantity}")
  public void addShoppingItemToCart(
      @PathVariable("id") int productId,
      @PathVariable("quantity") int quantity,
      KeycloakAuthenticationToken authentication) {
    productService.addShoppingItem(getCurrentUserId(authentication), productId, quantity);
  }

  @GetMapping("/remove-from-cart/{cartId}")
  public void removeProduct(@PathVariable int cartId) {
    productService.removeShoppingCartItem(cartId);
  }

  @GetMapping("/client/remove-all-items")
  public void removeAllItems(KeycloakAuthenticationToken authentication) {
    productService.removeAllShoppingCartItems(getCurrentUserId(authentication));
  }

  @GetMapping(path = "/get-product-cart-items", produces = "application/json")
  @ResponseBody
  public String getShoppingCartItems(KeycloakAuthenticationToken authentication) {
    Gson gson = new Gson();
    return gson.toJson(
        productService
            .allShoppingCartItems(getCurrentUserId(authentication))
            .toArray(new ShoppingCartItem[] {}));
  }

  private int getCurrentUserId(KeycloakAuthenticationToken authentication) {
    return userService.getUserId(authentication);
  }
}
