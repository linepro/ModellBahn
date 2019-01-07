package com.linepro.modellbahn.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.linepro.modellbahn.model.refs.IAdress;
import com.linepro.modellbahn.model.util.AdressTyp;

public class DigitalAdressValidator implements ConstraintValidator<DigitalAdress, IAdress> {

    @Override
    public boolean isValid(IAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.DIGITAL != value.getAdressTyp()) {
            return true;
        }

        return 1 <= value.getAdress() && value.getAdress() <= 80;
    }
}
