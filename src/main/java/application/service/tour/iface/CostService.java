package application.service.tour.iface;

import application.domain.Cost;

import java.util.List;

public interface CostService {
    boolean add(Cost tourCoast);

    List<Cost> getAllByTourRelease(Long tourReleaseId);

    boolean updateById(Cost tourCoast);

    boolean deleteById(Long id);
}
