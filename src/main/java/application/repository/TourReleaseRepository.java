package application.repository;

import application.domain.Release;
import application.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourReleaseRepository extends JpaRepository<Release, Long> {
    List<Release> findAllByTour(Tour tour);
}
