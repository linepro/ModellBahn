package com.linepro.modellbahn.security.password;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(PREFIX + ".PasswordProcessor")
public class PasswordProcessor {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final Validator validator;

    public Set<ConstraintViolation<RawPassword>> validate(String password) {
        return validator.validate(RawPassword.builder().password(password).build());
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
