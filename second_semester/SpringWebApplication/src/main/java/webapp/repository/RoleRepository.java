package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}