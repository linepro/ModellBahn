package com.linepro.modellbahn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import com.linepro.modellbahn.configuration.Configuration;
import com.linepro.modellbahn.controller.Controllers;
import com.linepro.modellbahn.converter.EntityConverters;
import com.linepro.modellbahn.converter.ModelConverters;
import com.linepro.modellbahn.hateoas.Hateoas;
import com.linepro.modellbahn.i18n.Internationalization;
import com.linepro.modellbahn.io.FileIo;
import com.linepro.modellbahn.logging.Logging;
import com.linepro.modellbahn.repository.lookup.Lookups;
import com.linepro.modellbahn.security.Security;
import com.linepro.modellbahn.service.impl.Services;
import com.linepro.modellbahn.swagger.SwaggerConfig;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@EnableJpaRepositories("com.linepro.modellbahn.repository") 
@EntityScan( basePackages = {"com.linepro.modellbahn.entity"} )
@Import({
    Configuration.class,
    Security.class,
    Logging.class,
    SwaggerConfig.class,

    // Controllers
    Controllers.class,
    // Services
    Services.class,
    // Hateoas Processors
    Hateoas.class,
    // Converters
    Lookups.class,
    EntityConverters.class,
    ModelConverters.class,

    // Utils
    FileIo.class,
    Internationalization.class,
})
public class ModellbahnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModellbahnApplication.class, args);
	}
}

