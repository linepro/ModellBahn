package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.API_ENDPOINTS;
import static com.linepro.modellbahn.io.MvcConfig.RESOURCE_ENDPOINTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.security.user.UserService;

import lombok.RequiredArgsConstructor;

@Configuration(PREFIX + "WebSecurityConfig")
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN_ROLE = "ADMIN";

    public static final SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority(ADMIN_ROLE);

    public static final String USER_ROLE = "USER";

    public static final SimpleGrantedAuthority USER = new SimpleGrantedAuthority(USER_ROLE);

    private static final String MANAGEMENT_ROOT = "/management";

    private static final String[] MANAGEMENT_PUBLIC = { MANAGEMENT_ROOT, MANAGEMENT_ROOT + "/health", MANAGEMENT_ROOT + "/info" };

    private static final String[] MANAGEMENT_SECURED = { MANAGEMENT_ROOT + "/**" };

    private final AccessDeniedHandler accessDeniedHandler;
    
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    private final ModellBahnBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.headers().frameOptions().sameOrigin()
            .and().cors()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
            .and().authorizeRequests()
                  .antMatchers(RESOURCE_ENDPOINTS).permitAll()
                  .antMatchers(MANAGEMENT_PUBLIC).permitAll()
                  .antMatchers(HttpMethod.POST, ApiPaths.REGISTER_ENDPOINTS).permitAll()
                  .antMatchers(HttpMethod.POST, ApiPaths.USER_ENDPOINTS).hasAnyRole(ADMIN_ROLE,USER_ROLE)
                  .antMatchers(API_ENDPOINTS).hasAnyRole(USER_ROLE)
                  .antMatchers(MANAGEMENT_SECURED).hasRole(ADMIN_ROLE)
            //.and().anonymous().authorities(USER_ROLE)
            //.and().oauth2ResourceServer(oauth2 -> oauth2.jwt())
            //.and().formLogin().loginPage("/").loginProcessingUrl("/login").defaultSuccessUrl("/home").failureHandler(authenticationFailureHandler())
            //.and().logout().logoutSuccessUrl("/").logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler())
            ;
        // @formatter:on
    }
}
