package application.service.user.iface;

import application.domain.User;
import application.domain.dto.RegistrationForm;

import java.util.List;

public interface UserService {
    boolean add(User user);

    User getById(Long id);

    User getByLogin(String login);

    List<User> getAll();

    boolean update(User user);

    public User registerUser(RegistrationForm registrationForm);
}
