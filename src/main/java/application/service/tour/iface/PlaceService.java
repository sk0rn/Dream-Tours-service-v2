package application.service.tour.iface;

import application.domain.Place;

import java.util.List;

public interface PlaceService {
    boolean add(Place place);

    Place getById(Long id);

    List<Place> getAll();

    boolean updateById(Place place);

    boolean deleteById(Long id);

    List<Place> findAllOrderByName();
}
