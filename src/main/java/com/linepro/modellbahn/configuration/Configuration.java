package com.linepro.modellbahn.configuration;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    ApplicationReadyListener.class,
    ErrorHandler.class
})
@Component
public class Configuration {

}
