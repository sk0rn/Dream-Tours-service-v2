package application.service.tour.iface;

import application.domain.TourCost;

import java.util.List;

public interface TourCostService {
    boolean add(TourCost tourCoast);

    List<TourCost> getAllByTourRelease(Integer tourReleaseId);

    boolean updateById(TourCost tourCoast);

    boolean deleteById(Integer id);
}
