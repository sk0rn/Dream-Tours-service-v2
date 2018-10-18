package application.service.account;

import application.repository.UserRepository;
import application.utils.UtilMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer getRole(String login) {
        Integer role;
        return login == null ||
                (login = login.trim()).isEmpty() ||
                (role = userRepository.findUserRoleByLogin(login)) == null ?
                -1 :
                role;
    }

    @Override
    public boolean checkAuth(String login, String password) {
        return login != null &&
                !(login = login.trim()).isEmpty() &&
                password != null &&
                !(password = password.trim()).isEmpty() &&
                UtilMD5.md5Custom(password).equals(userRepository.findUserPassByLogin(login));
    }
}
