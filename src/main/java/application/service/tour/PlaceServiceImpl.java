package application.service.tour;

import application.domain.Place;
import application.repository.PlaceRepository;
import application.service.tour.iface.PlaceService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public boolean add(Place place) {
        return ServiceHelper.save(placeRepository, place);
    }

    @Override
    public Place getById(Long id) {
        return ServiceHelper.getById(placeRepository, id);
    }

    @Override
    public List<Place> getAll() {
        return ServiceHelper.getAll(placeRepository);
    }

    @Override
    public boolean updateById(Place place) {
        return ServiceHelper.save(placeRepository, place);
    }

    @Override
    public boolean deleteById(Long id) {
        return ServiceHelper.delete(placeRepository, id);
    }

    @Override
    public List<Place> findAllOrderByName() {
        return ServiceHelper.getListByParam(placeRepository::findAll, Sort.by(Sort.Order.by("name")));
    }
}
