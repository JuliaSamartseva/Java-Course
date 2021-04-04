package webapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webapp.entity.Product;
import webapp.entity.ProductType;
import webapp.entity.ShoppingCartItem;
import webapp.entity.User;
import webapp.repository.ProductRepository;
import webapp.repository.ProductTypesRepository;
import webapp.repository.ShoppingItemsRepository;
import webapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
  @InjectMocks
  private final ProductService productService = new ProductService();

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ProductTypesRepository productTypesRepository;

  @Mock
  private ShoppingItemsRepository shoppingItemsRepository;

  @Mock
  private UserRepository userRepository;

  @Captor
  ArgumentCaptor<ShoppingCartItem> captor;

  @Test
  public void testAddNonNullShoppingItem() {
    ShoppingCartItem item = new ShoppingCartItem();
    item.setQuantity(2);
    when(shoppingItemsRepository.findShoppingCartItemByUserAndProductIds(1, 1)).thenReturn(item);
    productService.addShoppingItem(1, 1, 2);
    assertEquals(item.getQuantity(), 4);
    verify(shoppingItemsRepository, times(1)).save(item);
  }

  @Test
  public void addNullShoppingItem() {
    when(shoppingItemsRepository.findShoppingCartItemByUserAndProductIds(1, 1)).thenReturn(null);
    int userId = 1;
    int productId = 1;
    Product product = new Product();
    User user = new User();
    when(productRepository.findById(productId)).thenReturn(product);
    when(userRepository.findById(userId)).thenReturn(user);
    productService.addShoppingItem(userId, productId, 2);
    verify(shoppingItemsRepository, times(1)).save(captor.capture());
    assertEquals(captor.getValue().getQuantity(), 2);
    assertEquals(captor.getValue().getProduct(), product);
    assertEquals(captor.getValue().getUser(), user);
  }

  @Test
  public void testAllProducts() {
    List<Product> products = new ArrayList<>();
    when(productRepository.findAll()).thenReturn(products);
    assertEquals(products, productService.allProducts());
  }

  @Test
  public void testAllProductTypes() {
    List<ProductType> productTypes = new ArrayList<>();
    when(productTypesRepository.findAll()).thenReturn(productTypes);
    assertEquals(productTypes, productService.allProductTypes());
  }

  @Test
  public void testAllShoppingCartItems() {
    List<ShoppingCartItem> items = new ArrayList<>();
    when(shoppingItemsRepository.findShoppingCartItemsByUserId(1)).thenReturn(items);
    assertEquals(items, productService.allShoppingCartItems(1));
  }

  @Test
  public void testRemoveProduct() {
    productService.removeProduct(1);
    verify(productRepository, times(1)).deleteProductById(1);
  }

  @Test
  public void testRemoveShoppingCartItem() {
    productService.removeShoppingCartItem(1);
    verify(shoppingItemsRepository, times(1)).deleteShoppingCartItemById(1);
  }

  @Test
  public void testRemoveAllShoppingCartItems() {
    productService.removeAllShoppingCartItems(1);
    verify(shoppingItemsRepository, times(1)).deleteShoppingCartItemsByUserId(1);
  }

  @Test
  public void testAddProduct() {
    Product product = new Product();
    productService.addProduct(product);
    verify(productRepository, times(1)).save(product);
  }

  @Test
  public void testGetProductById() {
    Product product = new Product();
    when(productRepository.findById(1)).thenReturn(product);
    assertEquals(product, productService.getProductById(1));
  }

  @Test
  public void testGetProductTypeById() {
    ProductType productType = new ProductType();
    when(productTypesRepository.findById(1)).thenReturn(productType);
    assertEquals(productType, productService.getTypeById(1));
  }
}
