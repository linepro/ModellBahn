package com.linepro.modellbahn.configuration;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    ApplicationReadyListener.class,
    ErrorHandler.class
})
@Component("Configuration")
public class Configuration {

}
