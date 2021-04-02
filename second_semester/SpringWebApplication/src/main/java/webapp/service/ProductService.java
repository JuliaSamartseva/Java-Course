package webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.entity.Product;
import webapp.entity.ProductType;
import webapp.repository.ProductRepository;
import webapp.repository.ProductTypesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductService {
  @PersistenceContext
  private EntityManager em;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductTypesRepository productTypesRepository;

  public List<Product> allProducts() {
    return productRepository.findAll();
  }

  public List<ProductType> allProductTypes() {
    return  productTypesRepository.findAll();
  }

  public void removeProduct(int id) {
    productRepository.deleteProductById(id);
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


}
