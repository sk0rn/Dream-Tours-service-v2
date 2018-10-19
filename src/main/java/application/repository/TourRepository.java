package application.repository;

import application.domain.Place;
import application.domain.Subject;
import application.domain.Tour;
import application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    Tour findOneByName(String name);

    Tour findOneById(Long id);

    List<Tour> findAllBySubjects(Subject subject);

    List<Tour> findAllByUsers(User user);

    List<Tour> findAllByPlaces(Place place);

    @Query(nativeQuery = true, value = "with places1 as (\n" +
            "    select id\n" +
            "    from place\n" +
            "    where name ilike :searchString or descr ilike :searchString\n" +
            ")\n" +
            "  , subjects1 as (\n" +
            "    select id\n" +
            "    from subject\n" +
            "    where name ilike :searchString or descr ilike :searchString\n" +
            ")\n" +
            "select distinct tour.*\n" +
            "from tour\n" +
            "  left join tour_place as tp on tp.tour_id = tour.id\n" +
            "  left outer join places1 on places1.id = tp.place_id\n" +
            "  left join tour_subject ts on ts.tour_id = tour.id\n" +
            "  left outer join subjects1 on subjects1.id = ts.subject_id\n" +
            "where tour.name ilike :searchString or tour.descr ilike :searchString\n" +
            "      or places1.id is not null" +
            "      or subjects1.id is not null;")
    List<Tour> findAllBySearchString(String searchString);
}
