package com.linepro.modellbahn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.linepro.modellbahn.entity.HasTelefon;

public class TelephoneValidator implements ConstraintValidator<Telefon, HasTelefon> {

    @Override
    public boolean isValid(HasTelefon value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try {
                PhoneNumber telefon = phoneUtil.parseAndKeepRawInput(value.getTelefon(), value.getLand());

                return true;
        } catch (Exception e) {
            return false;
        }
    }
}
