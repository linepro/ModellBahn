package com.linepro.modellbahn.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target( {TYPE})
@Constraint(validatedBy = UniqueValidator.class)
public @interface Unique {
    String message() default "{com.linepro.modellbahn.validator.constraints.${type}.nonunique}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}
