package application.service.tour.iface;

import application.domain.Release;

import java.util.List;

public interface ReleaseService {
    boolean add(Release release);

    Release getById(Long id);

    List<Release> getAllByTourId(Long tourId);

    List<Release> getAll();

    boolean updateById(Release release);

    boolean deleteById(Long id);
}
