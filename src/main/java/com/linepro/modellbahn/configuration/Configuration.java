package com.linepro.modellbahn.configuration;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    ApplicationReadyListener.class,
    ErrorHandler.class
})
@Component(PREFIX + "Configuration")
public class Configuration {

}
