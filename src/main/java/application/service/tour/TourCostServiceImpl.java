package application.service.tour;

import application.domain.TourCost;
import application.repository.TourCostRepository;
import application.repository.TourReleaseRepository;
import application.service.tour.iface.TourCostService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourCostServiceImpl implements TourCostService {
    @Autowired
    private TourCostRepository tourCostRepository;

    @Autowired
    private TourReleaseRepository tourReleaseRepository;

    @Override
    public boolean add(TourCost tourCoast) {
        return ServiceHelper.save(tourCostRepository, tourCoast);
    }

    @Override
    public List<TourCost> getAllByTourRelease(Long tourReleaseId) {
        return ServiceHelper.getListByParam(tourCostRepository::findAllByTourRelease, ServiceHelper.getById(tourReleaseRepository, tourReleaseId));
    }

    @Override
    public boolean updateById(TourCost tourCoast) {
        return ServiceHelper.save(tourCostRepository, tourCoast);
    }

    @Override
    public boolean deleteById(Long id) {
        return ServiceHelper.delete(tourCostRepository, id);
    }
}
