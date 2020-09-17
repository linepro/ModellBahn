package com.linepro.modellbahn.security.password;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {PasswordValiator.class})
public @interface Password {
    String message() default "{com.linepro.modellbahn.validator.constraints.password.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
