package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.security.password.PasswordProcessor;
import com.linepro.modellbahn.security.user.UserAuthenticationListener;
import com.linepro.modellbahn.security.user.UserController;
import com.linepro.modellbahn.security.user.UserModelProcessor;
import com.linepro.modellbahn.security.user.UserService;

@Import({
    EmailService.class,
    ModellBahnAccessDeniedHandler.class,
    ModellBahnAuthenticationEntryPoint.class,
    PasswordProcessor.class,
    UserAuthenticationListener.class,
    UserService.class,
    UserController.class,
    UserModelProcessor.class,
    WebSecurityConfig.class
})
@Component(PREFIX + "Security")
public class Security {

    @Bean(PREFIX + "PasswordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
