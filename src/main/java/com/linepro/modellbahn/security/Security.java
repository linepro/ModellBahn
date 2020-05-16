package com.linepro.modellbahn.security;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    AuthorizationServerConfig.class,
    CorsConfig.class,
    CustomAccessTokenConverter.class,
    OAuth2ResourceServerConfig.class,
    OAuth2TokenForwardingContext.class,
    EmailService.class,
    UserController.class,
    WebSecurityConfig.class
})
@Component
public class Security {
}
