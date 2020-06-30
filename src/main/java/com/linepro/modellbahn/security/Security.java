package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

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
@Component(PREFIX + "Security")
public class Security {
    
    @Bean(PREFIX + "PasswordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
