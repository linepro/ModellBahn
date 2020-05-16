package com.linepro.modellbahn.logging;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    BusinessLogger.class,
    LoggedAspect.class,
    RequestLoggingConfiguration.class,
})
@Component
public class Logging {
}
