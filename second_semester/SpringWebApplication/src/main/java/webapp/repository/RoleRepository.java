package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapp.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}
