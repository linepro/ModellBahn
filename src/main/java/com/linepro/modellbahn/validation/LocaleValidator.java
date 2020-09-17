package com.linepro.modellbahn.validation;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocaleValidator implements ConstraintValidator<Locale, String> {

    private static final Set<String> LOCALES = Arrays.asList(java.util.Locale.getAvailableLocales())
                                                     .stream()
                                                     .map(l -> l.toString())
                                                     .collect(Collectors.toSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value != null) {
            return LOCALES.contains(value);
        }

        return true;
   }
}
