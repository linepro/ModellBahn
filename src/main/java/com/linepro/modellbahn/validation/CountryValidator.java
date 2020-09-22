package com.linepro.modellbahn.validation;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class CountryValidator implements ConstraintValidator<Country, String> {

    private static final Set<String> COUNTRIES = Arrays.asList(Locale.getISOCountries())
                                                       .stream()
                                                       .collect(Collectors.toSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.hasText(value)) {
            return COUNTRIES.contains(value);
        }

        return true;
   }
}
