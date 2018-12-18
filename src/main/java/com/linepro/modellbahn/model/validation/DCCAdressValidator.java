package com.linepro.modellbahn.model.validation;

import com.linepro.modellbahn.model.IAdress;
import com.linepro.modellbahn.model.util.AdressTyp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DCCAdressValidator implements ConstraintValidator<DELTAAdress, IAdress> {

    @Override
    public boolean isValid(IAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.DCC_SHORT != value.getAdressTyp()) {
            return true;
        }

        return 1 <= value.getAdress() && value.getAdress() <= 127;
   }
}
