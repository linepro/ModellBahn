package com.linepro.modellbahn.security.password;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service(PREFIX + "PasswordProcessor")
public class PasswordProcessor {

    private final PasswordEncoder passwordEncoder;

    private final Validator validator;

    public PasswordProcessor(@Qualifier(PREFIX + "PasswordEncoder") PasswordEncoder passwordEncoder, Validator validator) {
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    public Set<ConstraintViolation<RawPassword>> validate(String password) {
        return validator.validate(RawPassword.builder().password(password).build());
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
