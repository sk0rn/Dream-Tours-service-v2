package application.service.tour;

import application.domain.Tour;
import application.repository.PlaceRepository;
import application.repository.SubjectRepository;
import application.repository.TourRepository;
import application.repository.UserRepository;
import application.service.tour.iface.TourService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    //    private static final Logger LOGGER = Logger.getLogger(TourServiceImpl.class);

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
        return ServiceHelper.getById(tourRepository, id);
    }

    @Override
    public List<Tour> getAll() {
        return ServiceHelper.getAll(tourRepository);
    }

    @Override
    public List<Tour> getBySubjectId(Long subjectId) {
        return ServiceHelper.getListByParam(tourRepository::findAllBySubjects, ServiceHelper.getById(subjectRepository, subjectId));
    }

    @Override
    public List<Tour> getByClientId(Long clientId) {
        return ServiceHelper.getListByParam(tourRepository::findAllByUsers, ServiceHelper.getById(userRepository, clientId));
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
    public List<Tour> superPuperDuperSearch(Long userId,
                                            String subjectId, String placeId, String inWishList,
                                            String searchString, String dateBegin, String dateEnd,
                                            String costFrom, String costTo, String duration) {
        long subject_id = ServiceHelper.StringToId(subjectId);
        long place_id = ServiceHelper.StringToId(placeId);
        boolean wish_list = ServiceHelper.StringToBool(inWishList);

        Timestamp date_begin = ServiceHelper.StringToTimeStamp(dateBegin);
        Timestamp date_end = ServiceHelper.StringToTimeStamp(dateEnd);
        double cost_from = ServiceHelper.StringToDouble(costFrom);
        double cost_to = ServiceHelper.StringToDouble(costTo);
        Long duration_id = ServiceHelper.StringToId(duration);


        return date_begin == null && date_end == null && cost_from == 0.0d && cost_to == 0.0d && duration_id == -1 ?
                internalSuperPuperDuperToursSearch(userId, subject_id, place_id, wish_list, searchString) :
                internalSuperPuperDuperRealisesSearch(userId, subject_id, place_id, wish_list, searchString, date_begin, date_end, cost_from, cost_to, duration_id);
    }

    private List<Tour> internalSuperPuperDuperRealisesSearch(long user_id, long subject_id, long place_id, boolean wish_list, String searchString, Timestamp date_begin, Timestamp date_end, double cost_from, double cost_to, Long duration_id) {
        List<Tour> tours = tourRepository.findAllBy(subject_id, place_id, wish_list ? user_id : -1, searchString, date_begin, date_end, cost_from, cost_to, duration_id);
        return tours == null ? Collections.emptyList() : tours;
    }

    private List<Tour> internalSuperPuperDuperToursSearch(long user_id, long subject_id, long place_id, boolean wish_list, String searchString) {
        if (subject_id == -1 && place_id == -1 && !wish_list && searchString.isEmpty()) return getAll();
        else if (place_id == -1 && !wish_list && searchString.isEmpty()) return getBySubjectId(subject_id);
        else if (subject_id == -1 && !wish_list && searchString.isEmpty()) return getByPlaceId(place_id);
        else if (subject_id == -1 && place_id == -1 && searchString.isEmpty()) return getByClientId(user_id);
        else if (subject_id == -1 && place_id == -1 && !wish_list) return this.getBySearchString(searchString);
        return tourRepository.findAllBy(subject_id, place_id, wish_list ? user_id : -1, searchString);
    }
}
