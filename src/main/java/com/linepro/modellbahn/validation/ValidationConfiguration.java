package com.linepro.modellbahn.validation;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(PREFIX + "Validation")
public class ValidationConfiguration {

    @Bean(PREFIX + "UniqueValidator")
    public UniqueValidator getUniqueValidator() {
        return new UniqueValidator();
    }
}
