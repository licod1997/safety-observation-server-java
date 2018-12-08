package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findById( Integer roleId );

    Role findByRoleName( String roleName );
}
