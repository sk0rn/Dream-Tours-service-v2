package application.repository;

import application.domain.TourRelease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourReleaseRepository extends JpaRepository<TourRelease, Long> {

}
