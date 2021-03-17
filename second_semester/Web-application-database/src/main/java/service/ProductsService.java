package service;

import data.Product;
import data.ProductType;
import data.User;
import jdbc.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductsService {
  private static final Logger log = Logger.getLogger(UserService.class.getName());

  private static final String addProductQuery =
          "INSERT INTO internetshop.public.product(name, price, description, product_type_id) VALUES (?, ?, ?, ?)";
  private static final String allProductsQuery =
          "SELECT product.id, product.name, product.price, product.description, product_type.id, product_type.name, product_type.description FROM internetshop.public.product INNER JOIN internetshop.public.product_type ON product.product_type_id = product_type.id";

  public static void addProduct(Product product) {
    if (product == null) {
      log.warning("Cannot add product because it was null.");
      return;
    }
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement prepareStatement = connection.prepareStatement(addProductQuery);
      prepareStatement.setString(1, product.getName());
      prepareStatement.setInt(2, product.getPrice());
      prepareStatement.setString(3, product.getDescription());
      prepareStatement.setInt(4, product.getType().getId());
      if (prepareStatement.executeUpdate() <= 0)
        log.warning("Cannot add product.");
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }

  public static List<Product> getProducts() {
    log.info("Getting products from the database.");
    List<Product> products = new ArrayList<>();
    try (Connection connection = DatabaseConnection.getConnection()) {
      log.info("Connected to the database.");
      PreparedStatement ps = connection.prepareStatement(allProductsQuery);
      ResultSet rs = ps.executeQuery();
      while(rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        int price = rs.getInt(3);
        String description = rs.getString(4);
        int productTypeId = rs.getInt(5);
        String productTypeName = rs.getString(6);
        String productTypeDescription = rs.getString(7);
        Product product = new Product(id, name, price, description, new ProductType(productTypeId, productTypeName, productTypeDescription));
        products.add(product);
      }
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
    return products;
  }
}

