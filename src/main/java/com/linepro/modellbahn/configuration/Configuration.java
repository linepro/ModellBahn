package com.linepro.modellbahn.configuration;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.util.exceptions.StackTraceFilter;

@Import({
    ApplicationReadyListener.class,
    ModellbahnErrorAttributes.class,
    ModellBahnErrorHandler.class,
    ModellBahnErrorFilter.class,
    ModellBahnErrorViewResolver.class,
    MvcConfig.class,
    StackTraceFilter.class,
    ThymleafConfiguration.class
})
@Component(PREFIX + "Configuration")
public class Configuration {
}
