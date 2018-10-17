package application.service.tour.iface;

import application.domain.Duration;

import java.util.List;

public interface DurationService {
    boolean add(Duration duration);

    Duration getById(Integer id);

    List<Duration> getAllByTourId(Integer tourId);

    boolean updateById(Duration duration);

    boolean deleteById(Integer id);

    List<Duration> getAll();
}
