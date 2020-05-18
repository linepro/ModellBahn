package com.linepro.modellbahn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Import({
//    AuthorizationServerConfig.class,
//    CorsConfig.class,
//    CustomAccessTokenConverter.class,
//    OAuth2ResourceServerConfig.class,
//    OAuth2TokenForwardingContext.class,
    EmailService.class,
    UserController.class,
    WebSecurityConfig.class
})
@Component
public class Security {
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
