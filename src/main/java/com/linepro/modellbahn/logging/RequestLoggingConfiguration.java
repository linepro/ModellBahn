package com.linepro.modellbahn.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("RequestLoggingConfiguration")
public class RequestLoggingConfiguration {

    @Bean("RequestLoggingFilter")
    public RequestLoggingFilter requestLoggingFilter() {
        return new RequestLoggingFilter();
    }
}
