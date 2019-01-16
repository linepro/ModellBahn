package com.linepro.modellbahn.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.linepro.modellbahn.model.refs.IAdress;
import com.linepro.modellbahn.model.enums.AdressTyp;

public class WeicheAdressValidator implements ConstraintValidator<WeicheAdress, IAdress> {

    @Override
    public boolean isValid(IAdress value, ConstraintValidatorContext context) {
        if (value == null || AdressTyp.WEICHE != value.getAdressTyp()) {
            return true;
        }

        return 1 <= value.getAdress() && value.getAdress() <= 512;
    }
}
