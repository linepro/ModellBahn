package com.linepro.modellbahn.model.validation;

import com.linepro.modellbahn.model.IAdress;
import com.linepro.modellbahn.model.util.AdressTyp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DigitalAdressValidator implements ConstraintValidator<DELTAAdress, IAdress> {

    @Override
    public boolean isValid(IAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.DIGITAL != value.getAdressTyp()) {
            return true;
        }

        return 1 <= value.getAdress() && value.getAdress() <= 80;
    }
}
