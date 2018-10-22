package application.service.user;

import application.domain.User;
import application.domain.dto.RegistrationForm;
import application.domain.transformers.RegistrationFormUserTransformer;
import application.repository.UserRepository;
import application.service.user.iface.UserService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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

    public User registerUser(RegistrationForm registrationForm) {
        final String encodedPassword = passwordEncoder.encode(registrationForm.getPassword());

        // mapping user from regform dto
        User user = registrationForm.transformTo(new RegistrationFormUserTransformer());
        user.setPass(encodedPassword);
        user.setOptions(0);
        userRepository.save(user);
        return user;
    }
}
