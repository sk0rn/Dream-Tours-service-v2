package application.repository;

import application.domain.Duration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DurationRepository extends JpaRepository<Duration, Long> {
}
