package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.entity.Demo;

public interface DemoRepository extends JpaRepository<Demo, Long> {
    Demo findDemoByUsername( String username);
}
