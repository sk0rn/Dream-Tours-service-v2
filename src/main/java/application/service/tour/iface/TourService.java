package application.service.tour.iface;

import application.domain.Tour;

import java.util.List;

public interface TourService {
    boolean add(Tour tour);

    Tour getById(Long id);

    List<Tour> getAll();

    List<Tour> getBySubjectId(Long subjectId);

    List<Tour> getByClientId(Long clientId);

    List<Tour> getByOrderId(Long orderId);

    boolean update(Tour tour);

    boolean deleteById(Long id);
}
