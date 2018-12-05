package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.entity.Role;
import vn.edu.fpt.entity.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById( Long id );

    User findByUsername( String username );

    List<User> findByRolesIn( Set<Role> role );
}
