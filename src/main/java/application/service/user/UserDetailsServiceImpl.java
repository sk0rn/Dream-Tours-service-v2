package application.service.user;

import application.domain.User;
import application.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "UserDetailsServiceImpl")
@Setter(onMethod = @__({@Autowired}))
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.getByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("login not found");
        }

        return user;
    }
}
