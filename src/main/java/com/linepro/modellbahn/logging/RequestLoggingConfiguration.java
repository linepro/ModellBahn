package com.linepro.modellbahn.logging;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(PREFIX + "RequestLoggingConfiguration")
public class RequestLoggingConfiguration {

    @Bean(PREFIX + "RequestLoggingFilter")
    public RequestLoggingFilter requestLoggingFilter() {
        return new RequestLoggingFilter();
    }
}
