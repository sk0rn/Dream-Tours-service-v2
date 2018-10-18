package application.repository;

import application.domain.Subject;
import application.domain.Tour;
import application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    Tour findById(Integer id);

    Tour findOneByName(String name);

    Tour findOneById(Integer id);

    List<Tour> findAllBySubjects(Subject subject);

    List<Tour> findAllByUsers(User user);

}
