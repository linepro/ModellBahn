package com.linepro.modellbahn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {
            if (StringUtils.hasText(value)) {
                java.util.Currency.getInstance(value);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
   }

}
