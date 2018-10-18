package application.repository;

import application.domain.TourCost;
import application.domain.TourRelease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourCostRepository extends JpaRepository<TourCost, Long> {
    List<TourCost> findAllByTourRelease(TourRelease param);
}
