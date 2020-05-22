/*
 * 
 */
package com.linepro.modellbahn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Open Api Configuration.
 */
@Configuration
public class OpenApiConfiguration {

    private static final String TITLE = "ModellBahn API";

    private static final String DESCRIPTION = "ModellBahn datastore";

    private static final String LICENSE = "MIT License";

    private static final String LICENSE_URL = "https://github.com/linepro/ModellBahn/LICENSE";

    private static final String VERSION = "1.0.0";

    private static final String DOCUMENTATION_URL = "https://github.com/linepro/ModellBahn/docs";

    /**
     * Gets the Swagger Api docket.
     * @return the docket for the API
     */
    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI()
                        .info(new Info().title(TITLE)
                        .description("Spring shop sample application")
                        .version(VERSION)
                        .license(new License().name(LICENSE).url(LICENSE_URL)))
                        .externalDocs(new ExternalDocumentation()
                        .description(DESCRIPTION)
                        .url(DOCUMENTATION_URL));
        }
}
