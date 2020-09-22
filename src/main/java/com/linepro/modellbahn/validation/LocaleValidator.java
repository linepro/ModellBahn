package com.linepro.modellbahn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class LocaleValidator implements ConstraintValidator<Locale, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.hasText(value)) {
            return java.util.Locale.forLanguageTag(value) != null;
        }

        return true;
   }
}
