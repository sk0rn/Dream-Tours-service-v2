package application.service.user;

import application.domain.Role;
import application.domain.Tour;
import application.domain.User;
import application.domain.dto.RegistrationForm;
import application.domain.transformers.RegistrationFormUserTransformer;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import application.service.subsets.IdOnly;
import application.service.user.iface.UserService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
        return userRepository.getByLogin(login);
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
    public User registerUser(RegistrationForm registrationForm) {
        final String encodedPassword = passwordEncoder.encode(registrationForm.getPassword());
        final Role defaultRole = roleRepository.findOneByName("ROLE_USER");

        User user = registrationForm.transformTo(new RegistrationFormUserTransformer());
        user.setPass(encodedPassword);
        user.getRoles().add(defaultRole);
        user.setActive(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean addTourToSetTours(User user, Tour tour) {
        Set<Tour> tours = user.getTours();
        tours.add(tour);
        user.setTours(tours);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean removeTourFromSetTours(User user, Tour tour) {
        user.getTours().remove(tour);
        userRepository.save(user);
        return true;
    }

    @Override
    public Set<Long> getWishList(long userId) {
        return ServiceHelper.getById(userRepository::getWishList, userId).stream().map(IdOnly::getId).collect(Collectors.toSet());
    }
}
