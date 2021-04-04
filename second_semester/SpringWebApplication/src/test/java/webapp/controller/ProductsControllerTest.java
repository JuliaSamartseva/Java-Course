package webapp.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import webapp.entity.Product;
import webapp.entity.ProductType;
import webapp.service.ProductService;
import webapp.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest {
  @InjectMocks
  ProductsController productsController = new ProductsController();
  @Mock
  private final ProductService productService = new ProductService();

  @Mock
  private Model model;

  @Mock
  BindingResult bindingResult;

  @BeforeEach
  void injectDependencies() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testShowProducts() {
    assertEquals(productsController.showProduct(1, model), "administrator/product-manager");
    assertEquals(productsController.addProductPage(model), "administrator/product-manager");
  }

  @Test
  public void testAddProductWithMistake() {
    when(bindingResult.hasErrors()).thenReturn(true);
    Product product = new Product(1, "name", 20, "Description", new ProductType());
    when(bindingResult.hasErrors()).thenReturn(true);
    assertEquals(productsController.addProduct(product, bindingResult), "administrator/product-manager");
  }

  @Test
  public void testAddProduct() {
    when(bindingResult.hasErrors()).thenReturn(false);
    Product product = new Product(1, "name", 20, "Description", new ProductType());
    assertEquals(productsController.addProduct(product, bindingResult), "redirect:/administrator/home");
  }

  @Test
  public void testEditProductWithMistake() {
    when(bindingResult.hasErrors()).thenReturn(true);
    Product product = new Product(1, "name", 20, "Description", new ProductType());
    when(bindingResult.hasErrors()).thenReturn(true);
    assertEquals(productsController.editProduct(1, product, bindingResult), "administrator/product-manager");
  }

  @Test
  public void testEditProduct() {
    when(bindingResult.hasErrors()).thenReturn(false);
    Product product = new Product(1, "name", 20, "Description", new ProductType());
    assertEquals(productsController.editProduct(1, product, bindingResult), "redirect:/administrator/home");
  }

  @Test
  public void testGetProducts() {
    Product product = new Product(1, "name", 20, "Description", new ProductType());
    List<Product> productList = new ArrayList<>();
    productList.add(product);
    when(productService.allProducts()).thenReturn(productList);
    Gson gson = new Gson();
    assertEquals(productsController.getProducts(), gson.toJson(productList.toArray(new Product[] {})));
  }

  @Test
  public void testGetProductTypes() {
    List<ProductType> productTypesList = new ArrayList<>();
    ProductType productType = new ProductType();
    productTypesList.add(productType);
    when(productService.allProductTypes()).thenReturn(productTypesList);
    Gson gson = new Gson();
    assertEquals(productsController.getProductTypes(), gson.toJson(productTypesList.toArray(new ProductType[] {})));
  }

  @Test
  public void testGetProductWithId() {
    Product product = new Product(1, "name", 20, "Description", new ProductType());
    when(productService.getProductById(1)).thenReturn(product);
    Gson gson = new Gson();
    assertEquals(productsController.getProductWithId(1), gson.toJson(product));
  }

}
