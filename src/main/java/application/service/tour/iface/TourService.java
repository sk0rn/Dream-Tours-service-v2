package application.service.tour.iface;

import application.domain.Tour;

import java.util.List;

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

    List<Tour> superPuperDuperSearch(String userId,
                                     String subjectId, String placeId, String inWishList,
                                     String searchString, String dateBegin, String dateEnd,
                                     String costFrom, String costTo, String duration);
}
