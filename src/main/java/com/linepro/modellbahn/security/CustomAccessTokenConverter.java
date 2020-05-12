package com.linepro.modellbahn.security;

import java.util.Map;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    /** Cognito user id is the sent as "sub" */
    public static final String SUB = "sub";

    @Value("${clientId:clientId}")
    private String clientId;

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        if (claims.get(UserAuthenticationConverter.USERNAME) != null) {
            MDC.put("username", claims.get(UserAuthenticationConverter.USERNAME).toString());
        }

        OAuth2Authentication authentication = super.extractAuthentication(claims);
        authentication.setDetails(claims);
        return authentication;
    }
}

