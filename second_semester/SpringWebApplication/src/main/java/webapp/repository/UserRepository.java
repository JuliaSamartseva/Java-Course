package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import webapp.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
  User findByName(String name);

  @Transactional
  @Modifying
  @Query(value = "UPDATE users SET blocked = ?2 WHERE id = ?1",
          nativeQuery = true)
  void changeBlockForUserWithId(Long id, boolean blocked);
}