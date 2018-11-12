package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findAll();
    Role findById(Integer roleId);
}
