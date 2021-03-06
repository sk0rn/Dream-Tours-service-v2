package application.validators;

import com.google.common.base.Joiner;
import lombok.extern.log4j.Log4j;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

@Log4j
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword constraintAnnotation) {
        //Because ...
    }

	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext context) {
		Properties props = new Properties();
		try {
			// override default error messages
			props.load(new FileInputStream("src/main/resources/messages.properties"));
		} catch (IOException e) {
			log.error(e);
		}
		MessageResolver resolver = new PropertiesMessageResolver(props);
		final PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(
				new LengthRule(8, 30),
				new CharacterRule(EnglishCharacterData.UpperCase, 2 ),
				new CharacterRule(EnglishCharacterData.LowerCase, 2 ),
				new CharacterRule(EnglishCharacterData.Digit, 2 ),
				new WhitespaceRule()));
		final RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(Joiner.on(",")
				.join(validator.getMessages(result))).addConstraintViolation();
		return false;
	}
}
