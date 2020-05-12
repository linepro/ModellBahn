package com.linepro.modellbahn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.util.WithAdress;

public class DCCAdressValidator implements ConstraintValidator<DCCAdress, WithAdress> {

    @Override
    public boolean isValid(WithAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.DCC_SHORT != value.getAdressTyp()) {
            return true;
        }

        return 1 <= value.getAdress() && value.getAdress() <= 127;
   }
}
