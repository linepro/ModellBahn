package com.linepro.modellbahn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocaleValidator implements ConstraintValidator<Locale, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value != null) {
            return java.util.Locale.forLanguageTag(value) != null;
        }

        return true;
   }
}
