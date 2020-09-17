package com.linepro.modellbahn.security.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.nulabinc.zxcvbn.Zxcvbn;

public class PasswordValiator implements ConstraintValidator<Password, String> {

    private static final Zxcvbn evaluator = new Zxcvbn();

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return StringUtils.hasText(password) && evaluator.measure(password).getScore() >= 3;
   }
}