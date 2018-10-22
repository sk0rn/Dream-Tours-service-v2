package application.domain.transformers;

import application.domain.Contact;
import application.domain.User;
import application.domain.dto.RegistrationForm;

public class RegistrationFormUserTransformer implements Transformer<User, RegistrationForm> {

	@Override
	public User transform(RegistrationForm value) {
		User user = new User();
		user.setLogin(value.getLogin());
		user.addContact(new Contact("email:" + value.getEmail()));
		user.addContact(new Contact("phone:" + value.getPhone()));
		return user;
	}
}
