package com.linepro.modellbahn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.util.WithAdress;

public class DCCLongAdressValidator implements ConstraintValidator<DCCLongAdress, WithAdress> {

    @Override
    public boolean isValid(WithAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.DCC != value.getAdressTyp()) {
            return true;
        }

        return 0 <= value.getAdress() && value.getAdress() <= 10239;
    }
}
