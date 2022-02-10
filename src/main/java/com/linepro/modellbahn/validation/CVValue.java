package com.linepro.modellbahn.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
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
import org.hibernate.validator.constraints.Range;

@Documented
@ConstraintComposition(OR)
@Null
@Range(min=0, max=255, message = "{com.linepro.modellbahn.validator.constraints.cvvalue.range}")
@ReportAsSingleViolation
@Retention(RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Constraint(validatedBy = {})
public @interface CVValue {
    String message() default "{com.linepro.modellbahn.validator.constraints.cvvalue.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
