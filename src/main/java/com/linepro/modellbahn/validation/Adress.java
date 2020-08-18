package com.linepro.modellbahn.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hibernate.validator.constraints.CompositionType.OR;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.ConstraintComposition;

@Documented
@ConstraintComposition(OR)
@Null
@DELTAAdress
@DigitalAdress
@DCCAdress
@DCCLongAdress
@Magnetartikel
@ReportAsSingleViolation
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface Adress {
    String message() default "{com.linepro.modellbahn.validator.constraints.adress.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
