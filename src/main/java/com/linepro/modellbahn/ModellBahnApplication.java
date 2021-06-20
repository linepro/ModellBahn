package com.linepro.modellbahn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.linepro.modellbahn.configuration.Configuration;
import com.linepro.modellbahn.configuration.OpenApiConfiguration;
import com.linepro.modellbahn.controller.Controllers;
import com.linepro.modellbahn.converter.EntityConverters;
import com.linepro.modellbahn.converter.ModelConverters;
import com.linepro.modellbahn.hateoas.Hateoas;
import com.linepro.modellbahn.i18n.Internationalization;
import com.linepro.modellbahn.io.FileIo;
import com.linepro.modellbahn.logging.Logging;
import com.linepro.modellbahn.persistence.Persistence;
import com.linepro.modellbahn.repository.lookup.Lookups;
import com.linepro.modellbahn.security.Security;
import com.linepro.modellbahn.service.impl.Services;
import com.linepro.modellbahn.util.StaticPopulator;
import com.linepro.modellbahn.util.impexp.Data;
import com.linepro.modellbahn.validation.ValidationConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({
    Configuration.class,
    Security.class,
    Logging.class,
    OpenApiConfiguration.class,

    // Persistence
    Persistence.class,
    ValidationConfiguration.class,
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
    Data.class,
    StaticPopulator.class
})
public class ModellBahnApplication {

    public static final String PREFIX = "modellBahn.";

	public static void main(String[] args) {
		SpringApplication.run(ModellBahnApplication.class, args);
	}
}

