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
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

import static application.consts.Consts.*;
import static application.utils.ServiceHelper.getUserFromSession;
import static application.utils.ServiceHelper.getUserIdFromSession;

@Service
@Setter(onMethod = @__({@Autowired}))
public class TourServiceImpl implements TourService {
    private TourRepository tourRepository;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;
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
        if (userId == -1) return Collections.emptySet();
        else {
            Set<Long> result = new HashSet<>();
            for (IdOnly id : ServiceHelper.getById(userRepository::getWishList, userId)) {
                result.add(id.getId());
            }
            return result;
        }
    }

    @Override
    @Transactional
    public String modifyWishList(long tourId, int operation) {
        String result;
        User user = getUserFromSession();

        if (ADD_TOUR_TO_WISH_LIST_OPERATION == operation) {
            result = addUserToSetUsers(tourId, user) ? ADD : ERROR;
        } else if (REMOVE_TOUR_FROM_WISH_LIST_OPERATION == operation) {
            result = removeUserFromSetUsers(tourId, user) ? REMOVE : ERROR;
        } else result = ERROR;

        return result;
    }
}
