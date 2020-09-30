package com.linepro.modellbahn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.linepro.modellbahn.entity.HasTelefon;

public class TelephoneValidator implements ConstraintValidator<Telefon, HasTelefon> {

    @Override
    public boolean isValid(HasTelefon value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value.getTelefon())) {
            return true;
        }

        final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try {
            String sanitized = value.getTelefon()
                                    .replaceAll("[ -\\(\\)]","")
                                    .replaceAll("^00", "+");

                phoneUtil.parseAndKeepRawInput(sanitized, value.getLand());

                return true;
        } catch (Exception e) {
            return false;
        }
    }
}
