package application.service.tour;

import application.domain.Duration;
import application.repository.DurationRepository;
import application.service.tour.iface.DurationService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DurationServiceImpl implements DurationService {
    @Autowired
    private DurationRepository durationRepository;

    @Override
    public boolean add(Duration duration) {
        return ServiceHelper.save(durationRepository, duration);
    }

    @Override
    public Duration getById(Integer id) {
        return ServiceHelper.getById(durationRepository, id);
    }

    @Override
    public List<Duration> getAllByTourId(Integer tourId) {
        return null;
    }

    @Override
    public boolean updateById(Duration duration) {
        return ServiceHelper.save(durationRepository, duration);
    }

    @Override
    public boolean deleteById(Integer id) {
        return ServiceHelper.delete(durationRepository, id);
    }

    @Override
    public List<Duration> getAll() {
        return ServiceHelper.getAll(durationRepository);
    }
}
