package application.service.user.iface;

import application.domain.Tour;
import application.domain.User;
import application.domain.dto.RegistrationForm;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean add(User user);

    User getById(Long id);

    User getByLogin(String login);

    List<User> getAll();

    boolean update(User user);

    User registerUser(RegistrationForm registrationForm);

    boolean addTourToSetTours(User user, Tour tour);

    boolean removeTourFromSetTours(User user, Tour tour);

    Set<Long> getWishList(long userId);
}
