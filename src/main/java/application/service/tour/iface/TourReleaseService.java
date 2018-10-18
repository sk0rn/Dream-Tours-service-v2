package application.service.tour.iface;

import application.domain.TourRelease;

import java.util.List;

public interface TourReleaseService {
    boolean add(TourRelease tourRelease);

    TourRelease getById(Long id);

    List<TourRelease> getAllByTourId(Long tourId);

    boolean updateById(TourRelease tourRelease);

    boolean deleteById(Long id);
}
