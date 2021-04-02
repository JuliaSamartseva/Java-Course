package webapp.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import webapp.entity.Product;
import webapp.entity.ShoppingCartItem;
import webapp.service.ProductService;
import webapp.service.UserService;

import java.security.Principal;

@Controller
public class ClientController {
  @Autowired private UserService userService;

  @Autowired private ProductService productService;

  @GetMapping("/client/home")
  public String homePage() {
    return "/client/home";
  }

  @GetMapping("/client/shopping-cart")
  public String shoppingCartPage() {
    return "/client/shopping-cart";
  }

  @GetMapping("/add-to-cart/product_id/{id}/quantity/{quantity}")
  public void addShoppingItemToCart(
      @PathVariable("id") int productId,
      @PathVariable("quantity") int quantity,
      Principal principal) {
    productService.addShoppingItem(getCurrentUserId(principal), productId, quantity);
  }

  @GetMapping("/remove-from-cart/{cartId}")
  public void removeProduct(@PathVariable int cartId) {
    productService.removeShoppingCartItem(cartId);
  }

  @GetMapping("/client/remove-all-items")
  public void removeAllItems(Principal principal) {
    productService.removeAllShoppingCartItems(getCurrentUserId(principal));
  }

  @GetMapping(path = "/get-product-cart-items", produces = "application/json")
  @ResponseBody
  public String getShoppingCartItems(Principal principal) {
    Gson gson = new Gson();
    return gson.toJson(
        productService
            .allShoppingCartItems(getCurrentUserId(principal))
            .toArray(new ShoppingCartItem[] {}));
  }

  private int getCurrentUserId(Principal principal) {
    // Get current user details
    String userName = principal.getName();
    return userService.getUserByUsername(userName).getId();
  }
}
