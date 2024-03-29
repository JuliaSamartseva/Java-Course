package service;

import data.ShoppingCartItem;
import jdbc.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CartService {
  private static final Logger log = Logger.getLogger(CartService.class.getName());

  private static final String addProductToShoppingCartQuery =
      "INSERT INTO internetshop.public.shopping_cart(user_id, product_id, quantity) VALUES (?, ?, ?)";
  private static final String getShoppingCartItemQuery =
      "SELECT shopping_cart.id, quantity FROM internetshop.public.shopping_cart WHERE product_id = ? AND user_id = ?";
  private static final String updateShoppingCartItemQuery =
      "UPDATE internetshop.public.shopping_cart SET quantity = ? WHERE id = ?";
  private static final String getProductsFromShoppingCartQuery =
      "SELECT shopping_cart.id, shopping_cart.quantity, p.name, p.price FROM internetshop.public.shopping_cart INNER JOIN internetshop.public.product p on p.id = shopping_cart.product_id WHERE user_id = ?";
  private static final String removeShoppingCartItemWithIdQuery =
      "DELETE FROM internetshop.public.shopping_cart WHERE shopping_cart.id = ?";

  public static void removeAllItems(int userId) {
    List<ShoppingCartProductInfo> items = getProductsFromCart(userId);
    for (ShoppingCartProductInfo item : items) removeShoppingCartItemWithId(item.shoppingCartId);
  }

  public static void removeShoppingCartItemWithId(int id) {
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement prepareStatement =
          connection.prepareStatement(removeShoppingCartItemWithIdQuery);
      prepareStatement.setInt(1, id);
      if (prepareStatement.executeUpdate() <= 0)
        log.warning("Cannot remove shopping cart item with id " + id);
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }

  public static List<ShoppingCartProductInfo> getProductsFromCart(int userId) {
    log.info("Getting information about products in the shopping cart.");
    List<ShoppingCartProductInfo> products = new ArrayList<>();
    try (Connection connection = DatabaseConnection.getConnection()) {
      log.info("Connected to the database.");
      PreparedStatement ps = connection.prepareStatement(getProductsFromShoppingCartQuery);
      ps.setInt(1, userId);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int shoppingCartId = rs.getInt(1);
        int quantity = rs.getInt(2);
        String productName = rs.getString(3);
        int productPrice = rs.getInt(4);
        products.add(
            new ShoppingCartProductInfo(
                shoppingCartId, productName, productPrice * quantity, quantity));
      }
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
    return products;
  }

  public static void addProductToCart(ShoppingCartItem item) {
    if (item == null) {
      log.warning("Cannot add product because it was null.");
      return;
    }
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement prepareStatement = connection.prepareStatement(getShoppingCartItemQuery);
      prepareStatement.setInt(1, item.getProductId());
      prepareStatement.setInt(2, item.getUserId());
      ResultSet rs = prepareStatement.executeQuery();
      if (rs.next()) {
        log.info("There was product item in the shopping cart, increasing its quantity");
        int id = rs.getInt(1);
        int quantity = rs.getInt(2);
        prepareStatement = connection.prepareStatement(updateShoppingCartItemQuery);
        prepareStatement.setInt(1, item.getQuantity() + quantity);
        prepareStatement.setInt(2, id);
      } else {
        log.info("There was no product item in the shopping cart, adding it to the cart");
        prepareStatement = connection.prepareStatement(addProductToShoppingCartQuery);
        prepareStatement.setInt(1, item.getUserId());
        prepareStatement.setInt(2, item.getProductId());
        prepareStatement.setInt(3, item.getQuantity());
      }
      if (prepareStatement.executeUpdate() <= 0)
        log.warning("Cannot add product to the shopping cart.");
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }

  public static class ShoppingCartProductInfo {
    public int shoppingCartId;
    public String productName;
    public int price;
    public int quantity;

    public ShoppingCartProductInfo(
        int shoppingCartId, String productName, int price, int quantity) {
      this.shoppingCartId = shoppingCartId;
      this.productName = productName;
      this.price = price;
      this.quantity = quantity;
    }
  }
}
