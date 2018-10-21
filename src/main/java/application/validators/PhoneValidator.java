package application.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {

    @Override
    public void initialize(final ValidPhone constraintAnnotation) {}

    @Override
    public boolean isValid(final String phone, final ConstraintValidatorContext context) {
        return phone.matches(("\\d{10}"));
    }
}
