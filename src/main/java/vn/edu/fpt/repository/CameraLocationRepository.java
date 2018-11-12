package vn.edu.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.entity.CameraLocation;

public interface CameraLocationRepository extends JpaRepository<CameraLocation, Long> {
    CameraLocation findById(Long id);
}
