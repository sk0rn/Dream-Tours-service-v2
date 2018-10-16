package application.repository;

import application.domain.TourCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourCostRepository extends JpaRepository<TourCost, Long> {

}
