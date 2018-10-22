package application.service.user.iface;

import application.domain.Tour;
import application.domain.User;

import java.util.List;

public interface UserService {
    boolean add(User user);

    User getById(Long id);

    User getByLogin(String login);

    List<User> getAll();

    boolean update(User user);

    boolean addTourInSetTours(User user, Tour tour);
}
