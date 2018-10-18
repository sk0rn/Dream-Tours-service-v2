package application.service.user.iface;

import application.domain.User;

import java.util.List;

public interface UserService {
    boolean add(User user);

    User getById(Integer id);

    User getByLogin(String login);

    List<User> getAll();

    boolean update(User user);
}
