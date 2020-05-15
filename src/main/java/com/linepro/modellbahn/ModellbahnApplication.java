package com.linepro.modellbahn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import com.linepro.modellbahn.configuration.MvcConfig;
import com.linepro.modellbahn.controller.Controllers;
import com.linepro.modellbahn.controller.impl.FileServiceImpl;
import com.linepro.modellbahn.controller.impl.FileUploadHandlerImpl;
import com.linepro.modellbahn.controller.user.UserController;
import com.linepro.modellbahn.converter.entity.EntityConverters;
import com.linepro.modellbahn.converter.model.ModelConverters;
import com.linepro.modellbahn.hateoas.Hateoas;
import com.linepro.modellbahn.i18n.MessageTranslatorImpl;
import com.linepro.modellbahn.i18n.RequestLocaleFilter;
import com.linepro.modellbahn.logging.BusinessLogger;
import com.linepro.modellbahn.logging.LoggedAspect;
import com.linepro.modellbahn.logging.RequestLoggingConfiguration;
import com.linepro.modellbahn.security.AuthorizationServerConfig;
import com.linepro.modellbahn.security.CorsConfig;
import com.linepro.modellbahn.security.CustomAccessTokenConverter;
import com.linepro.modellbahn.security.OAuth2ResourceServerConfig;
import com.linepro.modellbahn.security.WebSecurityConfig;
import com.linepro.modellbahn.service.impl.Services;
import com.linepro.modellbahn.swagger.SwaggerConfig;
import com.linepro.modellbahn.util.ErrorHandler;
import com.linepro.modellbahn.util.FileFinderImpl;
import com.linepro.modellbahn.util.FileStoreImpl;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@EnableJpaRepositories("com.linepro.modellbahn.repository") 
@EntityScan( basePackages = {"com.linepro.modellbahn.entity"} )
//@ComponentScan
@Import({
    // Logging
    BusinessLogger.class,
    LoggedAspect.class,
    RequestLoggingConfiguration.class,
    RequestLocaleFilter.class,

   // @Configuration
    ErrorHandler.class,
    MvcConfig.class,
    SwaggerConfig.class,

    // Utils
    FileFinderImpl.class,
    FileServiceImpl.class,
    FileStoreImpl.class,
    FileUploadHandlerImpl.class,
    MessageTranslatorImpl.class,

    // Security
    AuthorizationServerConfig.class,
    CorsConfig.class,
    CustomAccessTokenConverter.class,
    OAuth2ResourceServerConfig.class,
    UserController.class,
    WebSecurityConfig.class,

    EntityConverters.class,
    ModelConverters.class,
    
    // Hateoas Processors
    Hateoas.class,

    // @Service
    Services.class,
    
    // @RestController
    Controllers.class,
    
    // @Component
})
public class ModellbahnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModellbahnApplication.class, args);
	}
}

