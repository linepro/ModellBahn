package com.linepro.modellbahn.validation;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryValidator implements ConstraintValidator<Country, String> {

    private static final List<String> COUNTRIES = Arrays.asList(Locale.getISOCountries());
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if (value != null) {
            return COUNTRIES.contains(value);
        }
        
        return true;
   }
}
