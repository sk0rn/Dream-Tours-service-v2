package application.service.tour;

import application.domain.Cost;
import application.repository.TourCostRepository;
import application.repository.TourReleaseRepository;
import application.service.tour.iface.CostService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostServiceImpl implements CostService {
    @Autowired
    private TourCostRepository tourCostRepository;

    @Autowired
    private TourReleaseRepository tourReleaseRepository;

    @Override
    public boolean add(Cost tourCoast) {
        return ServiceHelper.save(tourCostRepository, tourCoast);
    }

    @Override
    public List<Cost> getAllByTourRelease(Long tourReleaseId) {
        return ServiceHelper.getListByParam(tourCostRepository::findAllByRelease,
                                            ServiceHelper.getById(tourReleaseRepository, tourReleaseId));
    }

    @Override
    public boolean updateById(Cost tourCoast) {
        return ServiceHelper.save(tourCostRepository, tourCoast);
    }

    @Override
    public boolean deleteById(Long id) {
        return ServiceHelper.delete(tourCostRepository, id);
    }
}
