package application.service.user;

import application.domain.Tour;
import application.domain.User;
import application.repository.UserRepository;
import application.service.user.iface.UserService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean add(User user) {
        return ServiceHelper.save(userRepository, user);
    }

    @Override
    public User getById(Long id) {
        return ServiceHelper.getById(userRepository, id);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return ServiceHelper.getAll(userRepository);
    }

    @Override
    public boolean update(User user) {
        return ServiceHelper.save(userRepository, user);
    }

    @Override
    public boolean addTourInSetTours(User user, Tour tour) {
        Set<Tour> tours = user.getTours();
        tours.add(tour);
        user.setTours(tours);
        return true;
    }
}
