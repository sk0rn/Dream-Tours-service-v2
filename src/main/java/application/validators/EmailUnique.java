package application.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static application.consts.Messages.EMAIL_EXISTS;
import static application.consts.Messages.LOGIN_EXISTS;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EmailExistsValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface EmailUnique {

    String message() default EMAIL_EXISTS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}