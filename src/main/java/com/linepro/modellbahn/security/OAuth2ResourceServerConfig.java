package com.linepro.modellbahn.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.util.StringUtils;

/**
 * Configures OAuth2 authentication for incoming requests using JWT Bearer tokens and validates the JWT signatures using
 * any JSON Web Key (jwk) Set specified by the jwkSetUris (See <a href="https://tools.ietf.org/html/rfc7517">https://tools.ietf.org/html/rfc7517</a>).
 * If jwkSetUris is null OAuth security is disabled.
 * <p>
 * Default scopes are set on all end points, but can be overridden by specifying more specific ones by annotation or
 * additional configure(HttpSecurity http) bean instances.
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(OAuth2ResourceServerConfig.class);

    public static final String WILDCARD_PATH = "/**";

    /**
     * The default OAuth2 scopes permitted to access this server
     */
    public static final String ALLOWED_SCOPES = "#oauth2.hasAnyScope('Backend/admin', 'Backend/server', 'Backend/user', 'aws.cognito.signin.user.admin')";

    /**
     * UUID for an internal server sub
     */
    public static final String INTERNAL_SERVER = "00000000-0000-0000-0000-000000000000";

    /**
     * A set of uris to fetch jwkSets from
     */
    @Value("${com.linepro.modellbahn.jwkSetUris}")
    private String jwkSetUris;

    @Value("${CLOUD_CONFIG_PREFIX}")
    private String configRoot;

    /**
     * Constructor for autowiring
     */
    public OAuth2ResourceServerConfig() {
    }

    /**
     * Constructor for testing
     *
     * @param jwkSetUris A set of uris to fetch jwkSets from
     */
    protected OAuth2ResourceServerConfig(String jwkSetUris) {
        this.jwkSetUris = jwkSetUris;
    }

    /**
     * Configures resource server security with JWT OAuth2
     *
     * @param config
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        if (isSecured()) {
            config.tokenServices(tokenServices());
        }
    }

    /**
     * Configure endpoint security
     *
     * @param http the current HttpSecurity context
     * @throws Exception for any configuration failure
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (isSecured()) {
            http
                // Allow unsecured cors OPTION checks
                .cors()
            .and()
                // Require JWT with correct scopes for everything else
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, WILDCARD_PATH).access(ALLOWED_SCOPES)
                    .antMatchers(HttpMethod.POST, WILDCARD_PATH).access(ALLOWED_SCOPES)
                    .antMatchers(HttpMethod.PATCH, WILDCARD_PATH).access(ALLOWED_SCOPES)
                    .antMatchers(HttpMethod.PUT, WILDCARD_PATH).access(ALLOWED_SCOPES)
                    .antMatchers(HttpMethod.DELETE, WILDCARD_PATH).access(ALLOWED_SCOPES)
            .and()
                // Return a 403 for failures
                .exceptionHandling()
            .and()
                // We don't need CSRF for JWT based authentication
                .csrf().disable();
        } else {
            logger.warn("Security disabled: jwkSetUris not specified");

            http
                // Allow unsecured cors OPTION checks
                .cors()
            .and()
                // And don't check anything else ...
                .authorizeRequests().anyRequest().permitAll()
            .and()
                // Return a 403 for failures
                .exceptionHandling();
        }
    }

    /**
     * Gets the security status
     *
     * @return true if application is to be secured, otherwise false
     */
    protected boolean isSecured() {
        return !StringUtils.isEmpty(jwkSetUris);
    }

    /**
     * Configures JWT token processing
     *
     * @return the token services
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    /**
     * Creates a JwtToken store that uses jwkSets to validate the signatures.
     *
     * @return the token store
     */
    @Bean
    public TokenStore tokenStore() {
        // Need to translate username into user_name because AWS does it weirdly.
        CustomAccessTokenConverter tokenConverter = new CustomAccessTokenConverter();

        if (isSecured()) {
            return new JwkTokenStore(Arrays.asList(jwkSetUris.split(",")), tokenConverter, null);
        } else {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setVerifier(new DummyVerifier());
            converter.setAccessTokenConverter(tokenConverter);
            return new JwtTokenStore(converter);
        }
    }
}