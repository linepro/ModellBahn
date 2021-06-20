package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.controller.impl.ApiPaths.API_ENDPOINTS;
import static com.linepro.modellbahn.security.user.UserController.ABOUT_ENDPOINT;
import static com.linepro.modellbahn.security.user.UserController.CONFIRM_REGISTRATION_ENDPOINT;
import static com.linepro.modellbahn.security.user.UserController.FORGOT_PASSWORD_ENDPOINT;
import static com.linepro.modellbahn.security.user.UserController.LOGIN_ENDPOINT;
import static com.linepro.modellbahn.security.user.UserController.LOGIN_FAILURE_ENDPOINT;
import static com.linepro.modellbahn.security.user.UserController.LOGOUT_ENDPOINT;
import static com.linepro.modellbahn.security.user.UserController.LOGOUT_SUCCESS_ENDPOINT;
import static com.linepro.modellbahn.security.user.UserController.REGISTER_ENDPOINT;
import static com.linepro.modellbahn.security.user.UserController.RESET_PASSWORD_ENDPOINT;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.linepro.modellbahn.configuration.ModellBahnErrorFilter;
import com.linepro.modellbahn.configuration.OpenApiConfiguration;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.io.ResourceEndpoints;
import com.linepro.modellbahn.security.user.UserService;

import lombok.RequiredArgsConstructor;

@Configuration(PREFIX + "WebSecurityConfig")
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final int SECONDS_IN__DAY = 86400;

    // Authorities
    public static final String ADMIN_AUTHORITY = "ADMIN";
    public static final SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority(ADMIN_AUTHORITY);

    public static final String GUEST_AUTHORITY = "GUEST";
    public static final SimpleGrantedAuthority GUEST = new SimpleGrantedAuthority(GUEST_AUTHORITY);

    public static final String USER_AUTHORITY = "USER";
    public static final SimpleGrantedAuthority USER = new SimpleGrantedAuthority(USER_AUTHORITY);

    // Pages
    protected static final String PAGE = ".html";

    protected static final String ERRORS = "/error/*";
    protected static final String GENERAL_ERROR = "/error";

    protected static final String LOGIN_PAGE = LOGIN_ENDPOINT + PAGE;
    protected static final String LOGIN_FAILURE_PAGE = LOGIN_FAILURE_ENDPOINT + PAGE;
    protected static final String LOGOUT_PAGE = LOGOUT_ENDPOINT + PAGE;
    protected static final String LOGOUT_SUCCESS_PAGE = LOGOUT_SUCCESS_ENDPOINT + PAGE;
    protected static final String REGISTER_PAGE = REGISTER_ENDPOINT + PAGE;

    // Resources
    protected static final String FAVICON_ICO = "/favicon.ico";
    protected static final String CSS = "/css/*";
    protected static final String FONTS = "/fonts/*";
    protected static final String IMAGES = "/img/*";
    protected static final String JS = "/js/*";

    protected static final String[] INSECURE_URLS = {
        ABOUT_ENDPOINT,
        ERRORS,
        GENERAL_ERROR
    };

    protected static final String[] REGISTER_URLS = {
                    CONFIRM_REGISTRATION_ENDPOINT,
                    FORGOT_PASSWORD_ENDPOINT,
                    LOGIN_ENDPOINT,
                    REGISTER_ENDPOINT,
                    RESET_PASSWORD_ENDPOINT
                    };

    protected static final String[] INSECURE_PAGES = Arrays.asList(INSECURE_URLS)
                                                           .stream()
                                                           .map(u -> u + PAGE).collect(Collectors.toList())
                                                           .toArray(new String[0]);

    protected static final String[] REGISTER_PAGES = Arrays.asList(REGISTER_URLS)
                    .stream()
                    .map(u -> u + PAGE).collect(Collectors.toList())
                    .toArray(new String[0]);

    protected static final String[] INSECURE_RESOURCES = {
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

    @Autowired
    private final OpenApiConfiguration docConfig;

    @Autowired
    private final ModellBahnErrorFilter errorFilter;

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
                                              .collect(Collectors.toList())
                                              .toArray(new String[0]);

        http.addFilterBefore(errorFilter, LogoutFilter.class)
            .headers().frameOptions().sameOrigin()
            .and().cors()
            .and().csrf().disable()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
            .and().sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                  .sessionFixation().migrateSession()
                  .invalidSessionUrl(LOGIN_PAGE)
            //.and().oauth2ResourceServer(oauth2 -> oauth2.jwt())
            //.and().anonymous().authorities(GUEST_AUTHORITY)
            .and().authorizeRequests()
                  .antMatchers(INSECURE_PAGES).permitAll()
                  .antMatchers(INSECURE_RESOURCES).permitAll()
                  .antMatchers(INSECURE_URLS).permitAll()
                  .antMatchers(resourceEndpoints.getSwaggerUi()).permitAll()
                  .antMatchers(docConfig.getApiDocsPath()).permitAll()
                  .antMatchers(REGISTER_URLS).anonymous()
                  .antMatchers(REGISTER_PAGES).anonymous()
                  .antMatchers(LOGOUT_ENDPOINT).authenticated()
                  .antMatchers(LOGOUT_PAGE).authenticated()
                  .antMatchers(API_ENDPOINTS).hasAuthority(USER_AUTHORITY)
                  .antMatchers(HttpMethod.POST, ApiPaths.REGISTER_ENDPOINTS).anonymous()
                  .antMatchers(HttpMethod.POST, ApiPaths.USER_ENDPOINTS).hasAnyAuthority(ADMIN_AUTHORITY, USER_AUTHORITY)
                  .antMatchers(ApiPaths.MANAGEMENT_PUBLIC).hasAnyAuthority(ADMIN_AUTHORITY, USER_AUTHORITY)
                  .antMatchers(ApiPaths.MANAGEMENT_SECURED).hasAuthority(ADMIN_AUTHORITY)
                  .antMatchers(endPoints).hasAnyAuthority(ADMIN_AUTHORITY, USER_AUTHORITY)
            .and().formLogin()
                  .loginPage(LOGIN_PAGE)
                  .loginProcessingUrl(LOGIN_ENDPOINT)
                  .defaultSuccessUrl(resourceEndpoints.getHomePage())
                  .failureUrl(LOGIN_FAILURE_PAGE)
                  .permitAll()
            .and().logout()
                  .logoutUrl(LOGOUT_ENDPOINT)
                  .logoutSuccessUrl(LOGOUT_SUCCESS_PAGE)
                  .deleteCookies(SESSION_COOKIE)
                  .permitAll()
            .and().rememberMe()
                  .rememberMeParameter(REMEMBER_ME_PARAM)
                  .key(rememberMeKey)
                  .tokenValiditySeconds(rememberMeValidity * SECONDS_IN__DAY)
            ;
        // @formatter:on
    }

    public static boolean isAdmin(Authentication authentication) {
        return (authentication.getAuthorities().contains(WebSecurityConfig.ADMIN));
    }
}
