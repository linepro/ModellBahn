/*
 * 
 */
package com.linepro.modellbahn.configuration;

/**
 * Open Api Configuration.
 */
import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Arrays;

import org.springdoc.core.Constants;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springdoc.core.SwaggerUiOAuthProperties;
import org.springdoc.webmvc.ui.SwaggerIndexPageTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import lombok.Getter;

@Getter
@Configuration(PREFIX + "OpenApiConfiguration")
public class OpenApiConfiguration {

    @Value("${spring.mvc.servlet.path:ModellBahn}")
    private String servletPath;

    @Value("${springdoc.api-docs.path:/v3/api-docs}")
    private String path;

    @Value("${springdoc.use-management-port:false}")
    private boolean useManagementPort;
    
    @Value("${management.server.port:8087}")
    private int managementPort;

    protected static final class IndexPageTransformer extends SwaggerIndexPageTransformer {
        private OpenApiConfiguration config;

        protected IndexPageTransformer(SwaggerUiConfigProperties uiConfig, SwaggerUiOAuthProperties oAuthProperties, ObjectMapper objectMapper, OpenApiConfiguration config) {
            super(uiConfig, oAuthProperties, objectMapper);

            swaggerUiConfig.setDisableSwaggerDefaultUrl(true);

            this.config = config;
        }

        @Override
        protected String overwriteSwaggerDefaultUrl(String html) {
            UriComponentsBuilder uri = ServletUriComponentsBuilder.fromCurrentContextPath();
            
            if (config.isUseManagementPort()) {
                uri.port(config.getManagementPort());
            }

            uri.path(config.getServletPath());
            uri.path(config.getPath());
            uri.path(".json");

            return html.replace(Constants.SWAGGER_UI_DEFAULT_URL, uri.toUriString());
        }
    }

    private static final String CONTACT_NAME = "John Goff";

    private static final String EMAIL = "lineprocs@gmail.com";

    private static final String TITLE = "ModellBahn API";

    private static final String DESCRIPTION = "ModellBahn datastore";

    private static final String LICENSE = "MIT License";

    private static final String LICENSE_URL = "http://www.opensource.org/licenses/mit-license.php";

    private static final String VERSION = "1.0.0";

    private static final String DOCUMENTATION_URL = "https://github.com/linepro/ModellBahn/docs";

    /**
     * Gets the Swagger Api docket.
     * @return the docket for the API
     */
    @Bean(PREFIX + "OpenAPI")
    public OpenAPI publicApi() {
        return new OpenAPI()
            .info(
                 new Info().title(TITLE)
                           .contact(
                               new Contact().email(EMAIL)
                                            .name(CONTACT_NAME)
                               )
                           .description(DESCRIPTION)
                           .version(VERSION)
                           .license(
                               new License().name(LICENSE)
                                            .url(LICENSE_URL)
                               )
                 )
            .externalDocs(
                new ExternalDocumentation().description(DESCRIPTION)
                                           .url(DOCUMENTATION_URL)
                )
            .components(
                new Components().addSecuritySchemes(
                    "BasicAuth",
                    new SecurityScheme().type(Type.HTTP)
                                        .scheme("Basic")
                                        .in(In.HEADER)
                                        .name(HttpHeaders.AUTHORIZATION)
                                        .description("Basic Authentication")
                                        )
                )
            .addSecurityItem(
                new SecurityRequirement().addList("BasicAuth", Arrays.asList("read", "write"))
            );
        }

    @Bean(PREFIX + "SwaggerIndexPageTransformer")
    public SwaggerIndexPageTransformer getSwaggerIndexPageTransformer(SwaggerUiConfigProperties swaggerUiConfig, SwaggerUiOAuthProperties swaggerUiOAuthProperties, ObjectMapper objectMapper) {
        return new IndexPageTransformer(swaggerUiConfig, swaggerUiOAuthProperties, objectMapper, this);
    }
}
