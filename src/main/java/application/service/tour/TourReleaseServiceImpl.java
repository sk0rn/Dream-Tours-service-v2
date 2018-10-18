package application.service.tour;

import application.domain.Tour;
import application.domain.TourRelease;
import application.repository.TourReleaseRepository;
import application.repository.TourRepository;
import application.service.tour.iface.TourReleaseService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TourReleaseServiceImpl implements TourReleaseService {
    @Autowired
    private TourReleaseRepository tourReleaseRepository;

    @Autowired
    private TourRepository tourRepository;

    @Override
    public boolean add(TourRelease tourRelease) {
        return ServiceHelper.save(tourReleaseRepository, tourRelease);
    }

    @Override
    public TourRelease getById(Integer id) {
        return ServiceHelper.getById(tourReleaseRepository, id);
    }

    @Override
    public List<TourRelease> getAllByTourId(Integer tourId) {
        Tour tour;
        List<TourRelease> releases;

        return tourId == null ||
                tourId < 1 ||
                (tour = tourRepository.findOneById(tourId)) == null ||
                (releases = tourReleaseRepository.findAllByTour(tour)) == null ?
                Collections.emptyList() :
                releases;
    }

    @Override
    public boolean updateById(TourRelease tourRelease) {
        return ServiceHelper.save(tourReleaseRepository, tourRelease);
    }

    @Override
    public boolean deleteById(Integer id) {
        return ServiceHelper.delete(tourReleaseRepository, id);
    }
}
