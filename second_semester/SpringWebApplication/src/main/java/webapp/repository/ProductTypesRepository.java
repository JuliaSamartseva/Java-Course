package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapp.entity.ProductType;

@Repository
public interface ProductTypesRepository extends JpaRepository<ProductType, Long> {
  ProductType findById(int id);
}
