package com.linepro.modellbahn.model.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target( {TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = {DELTAAdressValidator.class})
public @interface DELTAAdress {

    String message() default "{com.linepro.modellbahn.validator.constraints.deltaadress.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
