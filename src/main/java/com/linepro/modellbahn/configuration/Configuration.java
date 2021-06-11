package com.linepro.modellbahn.configuration;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.util.exceptions.StackTraceFilter;

@Import({
    ApplicationReadyListener.class,
    ErrorHandler.class,
    ErrorViewResolver.class,
    MvcConfig.class,
    ModellbahnErrorAttributes.class,
    StackTraceFilter.class,
    ThymleafConfiguration.class
})
@Component(PREFIX + "Configuration")
public class Configuration {
}
