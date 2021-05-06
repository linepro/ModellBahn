package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.API_ENDPOINTS;

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
import com.linepro.modellbahn.io.ResourceEndpoints;
import com.linepro.modellbahn.security.user.UserService;

import lombok.RequiredArgsConstructor;

@Configuration(PREFIX + "WebSecurityConfig")
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN_AUTHORITY = "ADMIN";

    public static final SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority(ADMIN_AUTHORITY);

    public static final String GUEST_AUTHORITY = "GUEST";

    public static final SimpleGrantedAuthority GUEST = new SimpleGrantedAuthority(GUEST_AUTHORITY);

    public static final String USER_AUTHORITY = "USER";

    public static final SimpleGrantedAuthority USER = new SimpleGrantedAuthority(USER_AUTHORITY);

    private final AccessDeniedHandler accessDeniedHandler;
    
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    private final ModellBahnAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private final ResourceEndpoints resourceEndpoints;

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
            .and().anonymous().authorities(GUEST_AUTHORITY)
            .and().authorizeRequests()
                  .antMatchers(API_ENDPOINTS).hasAuthority(USER_AUTHORITY)
                  .antMatchers(HttpMethod.POST, ApiPaths.REGISTER_ENDPOINTS).hasAuthority(GUEST_AUTHORITY)
                  .antMatchers(HttpMethod.POST, ApiPaths.USER_ENDPOINTS).hasAnyAuthority(ADMIN_AUTHORITY, USER_AUTHORITY)
                  .antMatchers(ApiPaths.MANAGEMENT_PUBLIC).hasAnyAuthority(ADMIN_AUTHORITY, GUEST_AUTHORITY, USER_AUTHORITY)
                  .antMatchers(ApiPaths.MANAGEMENT_SECURED).hasAuthority(ADMIN_AUTHORITY)
                  .antMatchers(resourceEndpoints.getEndPoints().keySet().toArray(new String[0])).permitAll()
            //.and().oauth2ResourceServer(oauth2 -> oauth2.jwt())
            //.and().formLogin().loginPage("/").loginProcessingUrl("/login").defaultSuccessUrl("/home").failureHandler(authenticationFailureHandler())
            //.and().logout().logoutSuccessUrl("/").logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler())
            ;
        // @formatter:on
    }
}
