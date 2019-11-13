/*
 * 
 */
package com.linepro.modellbahn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
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
                .apiInfo(apiEndPointsInfo());
    }
    
    /**
     * gets the (mostly ficticious) API end points info.
     * @return the api info
     */
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("ModellBahn REST API")
                .description("ModellBahn REST API")
                .license("MIT")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
