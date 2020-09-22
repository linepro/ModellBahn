package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures Access-Control-Origin-Allow: "*" on all endpoint repsonses because we can't rely on the AWS Lambda anymore.
 *
 * TODO: something less brutal (maybe add the location of the requester?). Could use @CrossOrigin and allow spring to dynamically create
 */
@EnableWebMvc
@Configuration(PREFIX + "CorsConfig")
public class CorsConfig implements WebMvcConfigurer {

    private static final String ALLOWED_ORIGINS = "*";

    private static final String ALLOWED_HEADERS = "*";

    private static final String[] ALLOWED_METHODS = {
            HttpMethod.DELETE.name(),
            HttpMethod.GET.name(),
            HttpMethod.OPTIONS.name(),
            HttpMethod.PUT.name(),
            HttpMethod.POST.name()
    };

    private static final boolean ALLOW_CREDENTIALS = true;

    private static final long CORS_TIMEOUT = 3600L;

    private static final String ALL_ENDPOINTS = "/**";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping(ALL_ENDPOINTS)
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedHeaders(ALLOWED_HEADERS)
                .allowedMethods(ALLOWED_METHODS)
                .allowCredentials(ALLOW_CREDENTIALS)
                .maxAge(CORS_TIMEOUT);
    }
}
