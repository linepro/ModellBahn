package com.linepro.modellbahn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.linepro.modellbahn.model.WithAdress;
import com.linepro.modellbahn.model.enums.AdressTyp;

public class DigitalAdressValidator implements ConstraintValidator<DigitalAdress, WithAdress> {

    @Override
    public boolean isValid(WithAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.DIGITAL != value.getAdressTyp()) {
            return true;
        }

        return 1 <= value.getAdress() && value.getAdress() <= 80;
    }
}
