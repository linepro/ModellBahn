package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.API_ENDPOINTS;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
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

    // Authorities
    public static final String ADMIN_AUTHORITY = "ADMIN";
    protected static final SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority(ADMIN_AUTHORITY);

    public static final String GUEST_AUTHORITY = "GUEST";
    protected static final SimpleGrantedAuthority GUEST = new SimpleGrantedAuthority(GUEST_AUTHORITY);

    public static final String USER_AUTHORITY = "USER";
    protected static final SimpleGrantedAuthority USER = new SimpleGrantedAuthority(USER_AUTHORITY);

    // Pages
    protected static final String PAGE = ".html";

    protected static final String ABOUT = "/about";
    protected static final String CONFIRM_REGISTRATION = "/confirm";
    protected static final String ERRORS = "/error/*";
    protected static final String FORGOT_PASSWORD = "/forgot";
    protected static final String GENERAL_ERROR = "/error";
    protected static final String HOME = "/";
    protected static final String LOGIN = "/login";
    protected static final String LOGOUT = "/logout";
    protected static final String REGISTER = "/register";

    protected static final String LOGIN_PAGE = LOGIN + PAGE;
    protected static final String LOGIN_FAILURE_PAGE = LOGIN_PAGE + "?error=true";
    protected static final String REGISTER_PAGE = REGISTER + PAGE;

    // Open API
    protected static final String SWAGGER_UI = "/swagger-ui";
    protected static final String SWAGGER = SWAGGER_UI + "*/**";
    protected static final String DOCS = "/v3/api-docs.*";

    // Resources
    protected static final String FAVICON_ICO = "/favicon.ico";
    protected static final String CSS = "/css/*";
    protected static final String FONTS = "/fonts/*";
    protected static final String IMAGES = "/img/*";
    protected static final String JS = "/js/*";

    protected static final String[] INSECURE_URLS = {
        ABOUT,
        CONFIRM_REGISTRATION,
        ERRORS,
        FORGOT_PASSWORD,
        GENERAL_ERROR,
        LOGIN,
        LOGOUT,
        REGISTER
    };

    protected static final String[] INSECURE_PAGES = Arrays.asList(INSECURE_URLS)
                                                           .stream()
                                                           .map(u -> u + PAGE).collect(Collectors.toList())
                                                           .toArray(new String[0]);
    
    protected static final String[] INSECURE_RESOURCES = {
        SWAGGER,
        DOCS,

        FAVICON_ICO,
        CSS,
        FONTS,
        IMAGES,
        JS
    };

    // Login session
    protected static final String REMEMBER_ME_PARAM = "remember-me";

    protected static final String SESSION_COOKIE = "JSESSIONID";

    @Value("${com.linepro.modellbahn.remember-me.key:uniqueAndSecret}")
    private String rememberMeKey;

    @Value("${com.linepro.modellbahn.remember-me.validity:28}")
    private int rememberMeValidity;

    private final AccessDeniedHandler accessDeniedHandler;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    private final ModellBahnAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private final ResourceEndpoints resourceEndpoints;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        String[] endPoints = resourceEndpoints.getEndPoints()
                                              .keySet()
                                              .stream()
                                              .filter(e -> !StringUtils.contains(e, SWAGGER_UI))
                                              .collect(Collectors.toList())
                                              .toArray(new String[0]);

        http.headers().frameOptions().sameOrigin()
            .and().cors()
            .and().csrf().disable()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
            //.and().oauth2ResourceServer(oauth2 -> oauth2.jwt())
            //.and().anonymous().authorities(GUEST_AUTHORITY)
            .and().authorizeRequests()
                  .antMatchers(INSECURE_PAGES).permitAll()
                  .antMatchers(INSECURE_RESOURCES).permitAll()
                  .antMatchers(INSECURE_URLS).permitAll()
                  .antMatchers(API_ENDPOINTS).hasAuthority(USER_AUTHORITY)
                  .antMatchers(HttpMethod.POST, ApiPaths.REGISTER_ENDPOINTS).permitAll()
                  .antMatchers(HttpMethod.POST, ApiPaths.USER_ENDPOINTS).hasAnyAuthority(ADMIN_AUTHORITY, USER_AUTHORITY)
                  .antMatchers(ApiPaths.MANAGEMENT_PUBLIC).hasAnyAuthority(ADMIN_AUTHORITY, USER_AUTHORITY)
                  .antMatchers(ApiPaths.MANAGEMENT_SECURED).hasAuthority(ADMIN_AUTHORITY)
                  .antMatchers(endPoints).hasAnyAuthority(ADMIN_AUTHORITY, USER_AUTHORITY)
            .and().formLogin()
                  .loginPage(LOGIN_PAGE)
                  .loginProcessingUrl(LOGIN)
                  .defaultSuccessUrl(HOME)
                  .failureUrl(LOGIN_FAILURE_PAGE)
                  .permitAll()
            .and().logout()
                  .logoutUrl(HOME)
                  .logoutSuccessUrl(HOME)
                  .deleteCookies(SESSION_COOKIE)
                  .permitAll()
            .and().rememberMe()
                  .rememberMeParameter(REMEMBER_ME_PARAM)
                  .key(rememberMeKey)
                  .tokenValiditySeconds(rememberMeValidity * 86400)
            ;
        // @formatter:on
    }

    public static boolean isAdmin(Authentication authentication) {
        return (authentication.getAuthorities().contains(WebSecurityConfig.ADMIN));
    }
}
