package application.repository;

import application.domain.Place;
import application.domain.Subject;
import application.domain.Tour;
import application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    Tour findOneByName(String name);

    Tour findOneById(Long id);

    List<Tour> findAllBySubjects(Subject subject);

    List<Tour> findAllByUsers(User user);

    List<Tour> findAllByPlaces(Place place);

    @Query(nativeQuery = true, value = "with places1 as ( \n" +
            "    select id \n" +
            "    from place \n" +
            "    where name ilike :searchString or descr ilike :searchString \n" +
            ") \n" +
            "  , subjects1 as ( \n" +
            "    select id \n" +
            "    from subject \n" +
            "    where name ilike :searchString or descr ilike :searchString \n" +
            ")\n" +
            "select distinct tour.*\n" +
            "from tour\n" +
            "  left outer join tour_place as tp on tp.tour_id = tour.id and exists(select id from places1 where places1.id = tp.place_id)\n" +
            "  left outer join tour_subject ts on ts.tour_id = tour.id and exists(select id from subjects1 where subjects1.id = ts.subject_id)\n" +
            "where tour.name ilike :searchString or tour.descr ilike :searchString\n" +
            "   or tp.place_id is not null\n" +
            "   or ts.subject_id is not null;")
    List<Tour> findAllBySearchString(@Param(value = "searchString") String searchString);

    @Query(nativeQuery = true, value = "with places1 as (\n" +
            "    select id\n" +
            "    from place\n" +
            "    where name ilike :searchString or descr ilike :searchString\n" +
            "    )\n" +
            "    , subjects1 as (\n" +
            "    select id\n" +
            "    from subject\n" +
            "    where name ilike :searchString or descr ilike :searchString\n" +
            "    )\n" +
            ", wishlist1 as(\n" +
            "     select tour_id as id from wishlist where client_id = :client_id\n" +
            "    )\n" +
            "select distinct tour.*\n" +
            "from tour\n" +
            "       left outer join tour_place as tp on tp.tour_id = tour.id and exists(select id from places1 where places1.id = tp.place_id)\n" +
            "       left outer join tour_subject ts on ts.tour_id = tour.id and exists(select id from subjects1 where subjects1.id = ts.subject_id)\n" +
            "where (tour.name ilike :searchString or tour.descr ilike :searchString\n" +
            "         or tp.place_id is not null\n" +
            "         or ts.subject_id is not null)\n" +
            "  and (:place_id = -1 or tp.place_id = :place_id)\n" +
            "  and (:subject_id = -1 or ts.subject_id = :subject_id)\n" +
            "  and (:client_id = -1 or exists(select id from wishlist1 where wishlist1.id = tour.id))\n")
    List<Tour> findAllBy(
            @Param(value = "subject_id") Long subjectId,
            @Param(value = "place_id") Long placeId,
            @Param(value = "client_id") Long clientId,
            @Param(value = "searchString") String searchString
    );

    @Query(nativeQuery = true, value = "with places1 as (select id from place where name ilike :searchString\n" +
            "                                         or descr ilike :searchString),\n" +
            "     subjects1 as (select id from subject where name ilike :searchString\n" +
            "                                             or descr ilike :searchString),\n" +
            "     wishlist1 as (select tour_id as id from wishlist where client_id = :client_id)\n" +
            "select distinct tour.*\n" +
            "from tour\n" +
            "       left outer join tour_place as tp\n" +
            "         on tp.tour_id = tour.id and exists(select id from places1 where places1.id = tp.place_id limit 1)\n" +
            "       left outer join tour_subject ts\n" +
            "         on ts.tour_id = tour.id and exists(select id from subjects1 where subjects1.id = ts.subject_id limit 1)\n" +
            "where (tour.name ilike :searchString or tour.descr ilike :searchString\n" +
            "         or tp.place_id is not null\n" +
            "         or ts.subject_id is not null)\n" +
            "  and (:place_id = -1 or tp.place_id = :place_id)\n" +
            "  and (:subject_id = -1 or ts.subject_id = :subject_id)\n" +
            "  and (:client_id = -1 or exists(select id from wishlist1 where wishlist1.id = tour.id limit 1))\n" +
            "  and (not (:date_begin is not null or :date_end is not null or :duration_id is not null or :cost_from is not null or\n" +
            "            :cost_to is not null)\n" +
            "         or exists(select id\n" +
            "                   from tour_release\n" +
            "                   where (:date_begin is not null or tour_release.begin_time >= :date_begin)\n" +
            "                     and (:date_end is not null or tour_release.begin_time <= :date_end)\n" +
            "                     and (:duration_id is null or :duration_id = tour_release.duration_id)\n" +
            "                     and ((:cost_from is null and :cost_to is null) or exists(select id\n" +
            "                                                                              from tour_cost\n" +
            "                                                                              where tour_cost.tour_release_id = tour_release.id\n" +
            "                                                                                and tour_cost.is_participant = true\n" +
            "                                                                                and tour_cost.clipping_age is null\n" +
            "                                                                                and (:cost_from is not null or :cost_from <= tour_cost.cost)\n" +
            "                                                                                and (:cost_to is not null or :cost_to >= tour_cost.cost)\n" +
            "                                                                              limit 1\n" +
            "                       ))\n" +
            "                   limit 1)\n" +
            "          )")
    List<Tour> findAllBy(
            @Param(value = "subject_id") Long subjectId,
            @Param(value = "place_id") Long placeId,
            @Param(value = "client_id") Long clientId,
            @Param(value = "searchString") String searchString,
            @Param(value = "date_begin") Timestamp date_begin,
            @Param(value = "date_end") Timestamp date_end,
            @Param(value = "cost_from") double cost_from,
            @Param(value = "cost_to") double cost_to,
            @Param(value = "duration_id") Long duration_id
    );
}
