/*
 * 
 */
package com.linepro.modellbahn.configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linepro.modellbahn.controller.base.ApiPaths;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String TITLE = "ModellBahn";

    private static final String DESCRIPTION = "ModellBahn API";

    private static final String CONTACT_NAME = "";
    private static final String CONTACT_URL = "";
    private static final String CONTACT_EMAIL = "";
    
    private static final Contact CONTACT = new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);

    private static final String TERMS_OF_SERVICE = "termsOfServiceUrl";

    private static final String LICENSE = "MIT License";

    private static final String LICENSE_URL = "https://github.com/linepro/ModellBahn/LICENSE";

    @SuppressWarnings("rawtypes")
    private static final Collection<VendorExtension> EXTENSIONS = Collections.emptyList();
    
    private static final ApiInfo API_INFO = new ApiInfo(
            TITLE,
            DESCRIPTION,
            ApiPaths.VERSION,
            TERMS_OF_SERVICE,
            CONTACT,
            LICENSE,
            LICENSE_URL,
            EXTENSIONS);

    private static final List<? extends SecurityScheme> SECURITY_SCHEMES = Arrays.asList(
            new OAuth("name", Collections.singletonList(new AuthorizationScope("scopes","")), Collections.singletonList(new GrantType("grantTypes"))),
            new BasicAuth("name"),
            new ApiKey("name", "keyname", "passAs"));

    /**
     * Gets the Swagger Api docket.
     * @return the docket for the API
     */
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.linepro.modellbahn"))
                    .paths(PathSelectors.any())
                    .build()
                .apiInfo(API_INFO)
                .enable(true)
                .enableUrlTemplating(true)
                .forCodeGeneration(true)
                .pathMapping(ApiPaths.SWAGGER_ROOT)
                .securitySchemes(SECURITY_SCHEMES)
                .useDefaultResponseMessages(true);
    }
}
