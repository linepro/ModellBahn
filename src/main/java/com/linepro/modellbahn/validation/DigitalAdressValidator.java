package com.linepro.modellbahn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.util.WithAdress;

public class DigitalAdressValidator implements ConstraintValidator<DigitalAdress, WithAdress> {

    @Override
    public boolean isValid(WithAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.DIGITAL != value.getAdressTyp()) {
            return true;
        }

        return 1 <= value.getAdress() && value.getAdress() <= 80;
    }
}
