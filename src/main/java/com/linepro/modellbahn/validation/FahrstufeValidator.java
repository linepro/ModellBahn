package com.linepro.modellbahn.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FahrstufeValidator implements ConstraintValidator<Fahrstufe, Integer> {

    private static final List<Integer> VALID = Arrays.asList(2, 14, 27, 28, 128);

    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return (value == null || VALID.contains(value));
    }
}
