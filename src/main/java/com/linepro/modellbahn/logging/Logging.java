package com.linepro.modellbahn.logging;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    BusinessLogger.class,
    LoggedAspect.class,
    RequestLoggingConfiguration.class,
})
@Component(PREFIX + "Logging")
public class Logging {
}
