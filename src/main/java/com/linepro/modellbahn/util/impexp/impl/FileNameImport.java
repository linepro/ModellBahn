package com.linepro.modellbahn.util.impexp.impl;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.linepro.modellbahn.controller.impl.ApiNames;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNameImport {

    String modelType() default "";

    String fieldName() default "";

    String[] keyFields() default { ApiNames.NAMEN };
}
