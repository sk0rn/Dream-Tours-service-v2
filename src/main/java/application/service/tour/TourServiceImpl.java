package application.service.tour;

import application.domain.Tour;
import application.repository.SubjectRepository;
import application.repository.TourRepository;
import application.repository.UserRepository;
import application.service.tour.iface.TourService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
//    @Transactional
    public boolean add(Tour tour) {
        return ServiceHelper.save(tourRepository, tour);
    }

    @Override
    public Tour getById(Integer id) {
        return ServiceHelper.getById(tourRepository, id);
    }

    @Override
    public List<Tour> getAll() {
        return ServiceHelper.getAll(tourRepository);
    }

    @Override
    public List<Tour> getBySubjectId(Integer subjectId) {
        return ServiceHelper.getListByParam(tourRepository::findAllBySubjects, ServiceHelper.getById(subjectRepository, subjectId));
    }

    @Override
    public List<Tour> getByClientId(Integer clientId) {
        return ServiceHelper.getListByParam(tourRepository::findAllByUsers, ServiceHelper.getById(userRepository, clientId));
    }

    @Override
    public List<Tour> getByOrderId(Integer orderId) {
//        TODO Пока метод не задействован,
//        для ускорения разработки оставляю на потом
        return Collections.emptyList();
    }

    @Override
    public boolean update(Tour tour) {
        return ServiceHelper.save(tourRepository, tour);
    }

    @Override
    public boolean deleteById(Integer id) {
        return ServiceHelper.delete(tourRepository, id);
    }
}
