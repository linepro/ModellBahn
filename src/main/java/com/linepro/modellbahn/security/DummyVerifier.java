package com.linepro.modellbahn.security;

import org.springframework.security.jwt.crypto.sign.SignatureVerifier;

public class DummyVerifier implements SignatureVerifier {

    public static final String ALGORITHM = "HMACSHA256";

    @Override
    public String algorithm() {
        return ALGORITHM;
    }

    /**
     * accept any
     *
     * @param content
     * @param signature
     */
    @Override
    public void verify(byte[] content, byte[] signature) {
    }
}

