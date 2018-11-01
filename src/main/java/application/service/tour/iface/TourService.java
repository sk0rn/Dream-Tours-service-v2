package application.service.tour.iface;

import application.domain.Tour;
import application.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface TourService {
    boolean add(Tour tour);

    Tour getById(Long id);

    List<Tour> getAll();

    List<Tour> getBySubjectId(Long subjectId);

    List<Tour> getByClientId(Long clientId);

    List<Tour> getByPlaceId(Long placeId);

    List<Tour> getBySearchString(String searchString);

    List<Tour> getByOrderId(Long orderId);

    boolean update(Tour tour);

    boolean deleteById(Long id);

    List<Tour> superPuperDuperSearch(Long userId,
                                     String subjectId, String placeId, String inWishList,
                                     String searchString, Date dateBegin, Date dateEnd,
                                     String costFrom, String costTo, String duration);

    boolean addUserToSetUsers(Tour tour, User user);

    boolean addUserToSetUsers(long tourId, User user);

    boolean removeUserFromSetUsers(Tour tour, User user);

    boolean removeUserFromSetUsers(long tourId, User user);

    Set<Long> getWishList(long userId);
}
