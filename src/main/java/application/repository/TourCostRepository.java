package application.repository;

import application.domain.Cost;
import application.domain.Release;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourCostRepository extends JpaRepository<Cost, Long> {
    List<Cost> findAllByRelease(Release param);
}
