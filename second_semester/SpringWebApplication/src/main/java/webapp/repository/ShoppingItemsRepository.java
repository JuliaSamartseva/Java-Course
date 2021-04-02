package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import webapp.entity.ShoppingCartItem;

import java.util.List;

public interface ShoppingItemsRepository extends JpaRepository<ShoppingCartItem, Integer> {
  @Query(value = "SELECT * FROM shopping_cart WHERE user_id = ?1 AND product_id = ?2",
          nativeQuery = true)
  ShoppingCartItem findShoppingCartItemByUserAndProductIds(int userId, int productId);

  List<ShoppingCartItem> findShoppingCartItemsByUserId(int id);

  @Transactional
  @Modifying
  @Query(value = "UPDATE shopping_cart SET quantity = ?1 WHERE id = ?2",
          nativeQuery = true)
  void addItemToCart(int quantity, int id);

  @Transactional
  void deleteShoppingCartItemById(int id);

  @Transactional
  void deleteShoppingCartItemsByUserId(int id);
}

