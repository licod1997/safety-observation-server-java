package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User,Long>{
    User findById(Long id);

    List<User> findAllByUsernameContains(String username);
}
