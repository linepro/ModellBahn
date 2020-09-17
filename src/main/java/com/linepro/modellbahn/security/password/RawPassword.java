package com.linepro.modellbahn.security.password;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RawPassword {

    @Password
    public String password;
}
