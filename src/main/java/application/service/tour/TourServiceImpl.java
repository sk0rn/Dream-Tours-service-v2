package application.service.tour;

import application.domain.Tour;
import application.domain.User;
import application.repository.PlaceRepository;
import application.repository.SubjectRepository;
import application.repository.TourRepository;
import application.repository.UserRepository;
import application.service.subsets.IdOnly;
import application.service.tour.iface.TourService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static application.utils.ServiceHelper.getUserIdFromSession;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Override
//    @Transactional
    public boolean add(Tour tour) {
        return ServiceHelper.save(tourRepository, tour);
    }

    @Override
    public Tour getById(Long id) {
        return tourRepository.findOneById(id);
    }

    @Override
    public List<Tour> getAll() {
        return ServiceHelper.getAll(tourRepository);
    }

    @Override
    public List<Tour> getBySubjectId(Long subjectId) {
        return ServiceHelper.getListByParam(tourRepository::findAllBySubjects,
                                            ServiceHelper.getById(subjectRepository, subjectId));
    }

    @Override
    public List<Tour> getByClientId(Long clientId) {
        return ServiceHelper.getListByParam(user -> tourRepository.findAllByUsers(user),
                                            ServiceHelper.getById(userRepository, clientId));
    }

    @Override
    public List<Tour> getByPlaceId(Long placeId) {
        return ServiceHelper.getListByParam(tourRepository::findAllByPlaces, ServiceHelper.getById(placeRepository, placeId));
    }

    @Override
    public List<Tour> getBySearchString(String searchString) {
        return ServiceHelper.getListByParam(tourRepository::findAllBySearchString, searchString);
    }

    @Override
    public List<Tour> getByOrderId(Long orderId) {
//        TODO Пока метод не задействован,
//        для ускорения разработки оставляю на потом
        return Collections.emptyList();
    }

    @Override
    public boolean update(Tour tour) {
        return ServiceHelper.save(tourRepository, tour);
    }

    @Override
    public boolean deleteById(Long id) {
        return ServiceHelper.delete(tourRepository, id);
    }

    @Override
    public List<Tour> complexQuery(String subjectId, String placeId, String inWishList,
                                   String searchString, Date dateBegin, Date dateEnd,
                                   String costFrom, String costTo, String duration) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

        List<Tour> tours = tourRepository.findAllBy(
                ServiceHelper.stringToId(subjectId),
                ServiceHelper.stringToId(placeId),
                ServiceHelper.stringToBool(inWishList) ? getUserIdFromSession() : -1,
                "%" + searchString + "%",
                dateBegin == null ? "1970-01-01 08:00:00.000" : dateFormat.format(dateBegin),
                dateEnd == null ? "1970-01-01 08:00:00.000" : dateFormat.format(dateEnd),
                ServiceHelper.stringToDouble(costFrom),
                ServiceHelper.stringToDouble(costTo),
                ServiceHelper.stringToId(duration));
        return tours == null ? Collections.emptyList() : tours;
    }

    private List<Tour> internalSuperPuperDuperRealisesSearch(long user_id, long subject_id, long place_id, boolean wish_list, String searchString, Timestamp date_begin, Timestamp date_end, double cost_from, double cost_to, Long duration_id) {
//        List<Tour> tours = tourRepository.findAllBy(subject_id, place_id, wish_list ? user_id : -1, searchString, date_begin, date_end, cost_from, cost_to, duration_id);
//        return tours == null ? Collections.emptyList() : tours;
        return Collections.emptyList();
    }

    private List<Tour> internalSuperPuperDuperToursSearch(long user_id, long subject_id, long place_id, boolean wish_list, String searchString) {
        if (subject_id == -1 && place_id == -1 && !wish_list && searchString.isEmpty()) return getAll();
        else if (place_id == -1 && !wish_list && searchString.isEmpty()) return getBySubjectId(subject_id);
        else if (subject_id == -1 && !wish_list && searchString.isEmpty()) return getByPlaceId(place_id);
        else if (subject_id == -1 && place_id == -1 && searchString.isEmpty()) return getByClientId(user_id);
        else if (subject_id == -1 && place_id == -1 && !wish_list) return this.getBySearchString(searchString);
        return tourRepository.findAllBy(subject_id, place_id, wish_list ? user_id : -1, searchString);
    }

    @Override
    public boolean addUserToSetUsers(Tour tour, User user) {
        tour.getUsers().add(user);
        update(tour);
        return true;
    }

    @Override
    public boolean addUserToSetUsers(long tourId, User user) {
        return addUserToSetUsers(getById(tourId), user);
    }

    @Override
    @Transactional
    public boolean removeUserFromSetUsers(Tour tour, User user) {
        tour.getUsers().remove(user);
        update(tour);
        return true;
    }

    @Override
    @Transactional
    public boolean removeUserFromSetUsers(long tourId, User user) {
        return removeUserFromSetUsers(getById(tourId), user);
    }

    @Override
    public Set<Long> getWishList(long userId) {
        Set<Long> result = new HashSet<>();

        for (IdOnly id : ServiceHelper.getById(userRepository::getWishList, userId)) {
            result.add(id.getId());
        }

        return result;
    }
}
