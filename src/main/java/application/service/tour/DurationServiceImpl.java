package application.service.tour;

import application.domain.Duration;
import application.repository.DurationRepository;
import application.service.tour.iface.DurationService;
import application.utils.ServiceHelper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Setter(onMethod = @__({@Autowired}))
public class DurationServiceImpl implements DurationService {
    private DurationRepository durationRepository;

    @Override
    public boolean add(Duration duration) {
        return ServiceHelper.save(durationRepository, duration);
    }

    @Override
    public Duration getById(Long id) {
        return ServiceHelper.getById(durationRepository, id);
    }

    @Override
    public List<Duration> getAllByTourId(Long tourId) {
        return Collections.emptyList();
    }

    @Override
    public boolean updateById(Duration duration) {
        return ServiceHelper.save(durationRepository, duration);
    }

    @Override
    public boolean deleteById(Long id) {
        return ServiceHelper.delete(durationRepository, id);
    }

    @Override
    public List<Duration> getAll() {
        return ServiceHelper.getAll(durationRepository);
    }

    @Override
    public List<Duration> findAllOrderByName() {
        return ServiceHelper.getListByParam(durationRepository::findAll, Sort.by(Sort.Order.by("numberDays")));
    }
}
