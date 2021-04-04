package webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.entity.Product;
import webapp.entity.ProductType;
import webapp.entity.ShoppingCartItem;
import webapp.repository.ProductRepository;
import webapp.repository.ProductTypesRepository;
import webapp.repository.ShoppingItemsRepository;
import webapp.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductService {
  @Autowired ProductRepository productRepository;
  @Autowired ProductTypesRepository productTypesRepository;
  @Autowired ShoppingItemsRepository shoppingItemsRepository;
  @Autowired UserRepository userRepository;
  @PersistenceContext private EntityManager em;

  public List<Product> allProducts() {
    return productRepository.findAll();
  }

  public List<ProductType> allProductTypes() {
    return productTypesRepository.findAll();
  }

  public List<ShoppingCartItem> allShoppingCartItems(int userId) {
    return shoppingItemsRepository.findShoppingCartItemsByUserId(userId);
  }

  public void removeProduct(int id) {
    productRepository.deleteProductById(id);
  }

  public void removeShoppingCartItem(int id) {
    shoppingItemsRepository.deleteShoppingCartItemById(id);
  }

  public void removeAllShoppingCartItems(int userId) {
    shoppingItemsRepository.deleteShoppingCartItemsByUserId(userId);
  }

  public void addProduct(Product product) {
    productRepository.save(product);
  }

  public Product getProductById(int id) {
    return productRepository.findById(id);
  }

  public ProductType getTypeById(int id) {
    return productTypesRepository.findById(id);
  }

  public void addShoppingItem(int userId, int productId, int quantity) {
    ShoppingCartItem item =
        shoppingItemsRepository.findShoppingCartItemByUserAndProductIds(userId, productId);
    if (item != null) {
      item.setQuantity(item.getQuantity() + quantity);
    } else {
      item = new ShoppingCartItem();
      item.setProduct(productRepository.findById(productId));
      item.setUser(userRepository.findById(userId));
      item.setQuantity(quantity);
    }
    shoppingItemsRepository.save(item);
  }
}
