package application.validators;

import application.domain.dto.RegistrationForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {}

	@Override
	public boolean isValid(final Object obj, final ConstraintValidatorContext context){
		RegistrationForm form = (RegistrationForm) obj;
		return form.getPassword() != null && form.getPassword().equals(form.getRepeatPassword());
	}
}
