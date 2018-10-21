package application.domain.transformers;

import application.domain.User;
import application.domain.dto.RegistrationForm;

public class RegistrationFormUserConverter implements Converter<User, RegistrationForm> {

	@Override
	public User convert(RegistrationForm value) {
		User user = new User();

		user.setLogin(value.getLogin());

		return user;
	}

}
