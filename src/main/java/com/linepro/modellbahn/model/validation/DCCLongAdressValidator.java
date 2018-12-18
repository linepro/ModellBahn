package com.linepro.modellbahn.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.linepro.modellbahn.model.IAdress;
import com.linepro.modellbahn.model.util.AdressTyp;

class DCCLongAdressValidator implements ConstraintValidator<DELTAAdress, IAdress> {

    @Override
    public boolean isValid(IAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.DCC != value.getAdressTyp()) {
            return true;
        }

        return 0 <= value.getAdress() && value.getAdress() <= 10239;
    }
}
