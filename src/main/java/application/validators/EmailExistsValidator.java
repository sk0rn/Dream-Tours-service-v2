package application.validators;

import application.service.user.iface.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistsValidator implements ConstraintValidator<EmailUnique, String> {
    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void initialize(final EmailUnique constraintAnnotation) {
        //Because ...
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return !contactService.checkForMatch("email:" + value);
    }
}
