package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import webapp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Transactional
  void deleteProductById(int id);

  Product findById(int id);
}
