/*
 * 
 */
package com.linepro.modellbahn.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Swagger Configuration.
 */
@Configuration
//@EnableSwagger2
public class SwaggerConfig {

    private static final String TITLE = "ModellBahn";

    private static final String DESCRIPTION = "ModellBahn API";

    private static final String TERMS_OF_SERVICE = "termsOfServiceUrl";

    private static final String LICENSE = "MIT License";

    private static final String LICENSE_URL = "https://github.com/linepro/ModellBahn/LICENSE";

    /**
     * Gets the Swagger Api docket.
     * @return the docket for the API
     */
    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI()
                        .info(new Info().title(TITLE)
                        .description("Spring shop sample application")
                        .version(DESCRIPTION)
                        .license(new License().name(LICENSE).url(LICENSE_URL)))
                        .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
        }
    
    @SuppressWarnings("deprecation")
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }
}
