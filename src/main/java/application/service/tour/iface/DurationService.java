package application.service.tour.iface;

import application.domain.Duration;

import java.util.List;

public interface DurationService {
    boolean add(Duration duration);

    Duration getById(Long id);

    List<Duration> getAllByTourId(Long tourId);

    boolean updateById(Duration duration);

    boolean deleteById(Long id);

    List<Duration> getAll();
}
