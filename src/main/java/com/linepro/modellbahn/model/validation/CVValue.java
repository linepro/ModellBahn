package com.linepro.modellbahn.model.validation;

import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.Range;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Null;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hibernate.validator.constraints.CompositionType.OR;

@Documented
@ConstraintComposition(OR)
@Null
@Range(min=0,max=255)
@ReportAsSingleViolation
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface CVValue {
    String message() default "{org.hibernate.validator.constraints.Range.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
