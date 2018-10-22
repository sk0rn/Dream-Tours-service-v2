package application.validators;

import application.domain.User;
import application.service.user.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginExistsValidator implements ConstraintValidator<LoginUnique, String> {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(final LoginUnique constraintAnnotation) {}

    @Override
    public boolean isValid(final String login, final ConstraintValidatorContext context) {
        User user = userService.getByLogin(login);
        return user == null;
    }
}
