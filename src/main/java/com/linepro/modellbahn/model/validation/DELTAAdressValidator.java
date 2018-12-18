package com.linepro.modellbahn.model.validation;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.linepro.modellbahn.model.IAdress;
import com.linepro.modellbahn.model.util.AdressTyp;

public class DELTAAdressValidator implements ConstraintValidator<DELTAAdress, IAdress> {

    private static final Collection<Integer> VALID_ADRESSES = Arrays
            .asList(2, 6, 8, 18, 20, 24, 26, 54, 56, 60, 62, 72, 74, 78, 80);

    @Override
    public boolean isValid(IAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.DELTA != value.getAdressTyp()) {
            return true;
        }

        return VALID_ADRESSES.contains(value.getAdress());
    }
}
