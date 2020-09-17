package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.API_ENDPOINTS;
import static com.linepro.modellbahn.io.MvcConfig.RESOURCE_ENDPOINTS;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.security.user.UserService;

import lombok.RequiredArgsConstructor;

@Configuration(PREFIX + "WebSecurityConfig")
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ROLE = "ADMIN";

    private static final String USER_ROLE = "USER";

    private static final String[] ACTUATOR_ENDPOINTS = { "/actuator/**" };

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
                 .disable()
            .headers()
                 .frameOptions()
                      .sameOrigin()
            .and()
                .cors()
            .and()
                .exceptionHandling()
            .and()
                .authorizeRequests()
                    .antMatchers(RESOURCE_ENDPOINTS)
                        .permitAll()
                    .antMatchers(HttpMethod.POST, ApiPaths.REGISTER_USER, ApiPaths.CONFIRM_USER, ApiPaths.FORGOT_PASSWORD, ApiPaths.RESET_PASSWORD)
                        .permitAll()
                    .antMatchers(API_ENDPOINTS)
                        .hasRole(USER_ROLE)
                    .antMatchers(ACTUATOR_ENDPOINTS)
                        .hasRole(ADMIN_ROLE)
//               .and()
//                   .formLogin()
//                       .loginPage("/")
//                       .loginProcessingUrl("/login")
//                       .defaultSuccessUrl("/home")
//               .and()
//                  .logout()
//                       .logoutSuccessUrl("/")
//                       .logoutUrl("/logout") // POST only
            .and()
               .anonymous()
                   .authorities(USER_ROLE);
        // @formatter:on
    }
}
