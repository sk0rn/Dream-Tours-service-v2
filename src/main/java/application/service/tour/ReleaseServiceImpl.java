package application.service.tour;

import application.domain.Release;
import application.domain.Tour;
import application.repository.TourReleaseRepository;
import application.repository.TourRepository;
import application.service.tour.iface.ReleaseService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    @Autowired
    private TourReleaseRepository tourReleaseRepository;

    @Autowired
    private TourRepository tourRepository;

    @Override
    public boolean add(Release release) {
        return ServiceHelper.save(tourReleaseRepository, release);
    }

    @Override
    public Release getById(Long id) {
        return ServiceHelper.getById(tourReleaseRepository, id);
    }

    @Override
    public List<Release> getAllByTourId(Long tourId) {
        Tour tour;
        List<Release> releases;

        return tourId == null ||
                tourId < 1 ||
                (tour = tourRepository.getOne(tourId)) == null ||
                (releases = tourReleaseRepository.findAllByTour(tour)) == null ?
                Collections.emptyList() :
                releases;
    }

    @Override
    public List<Release> getAll() {
        return ServiceHelper.getAll(tourReleaseRepository);
    }

    @Override
    public boolean updateById(Release release) {
        return ServiceHelper.save(tourReleaseRepository, release);
    }

    @Override
    public boolean deleteById(Long id) {
        return ServiceHelper.delete(tourReleaseRepository, id);
    }
}
