package application.service.tour.iface;

import application.domain.TourRelease;

import java.util.List;

public interface TourReleaseService {
    boolean add(TourRelease tourRelease);

    TourRelease getById(Integer id);

    List<TourRelease> getAllByTourId(Integer tourId);

    boolean updateById(TourRelease tourRelease);

    boolean deleteById(Integer id);
}
