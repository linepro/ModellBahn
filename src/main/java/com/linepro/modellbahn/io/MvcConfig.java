package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration(PREFIX + "MvcConfig")
@EnableWebMvc
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private final ResourceEndpoints resourceEndpoints;

    @Value("${spring.mvc.resource.cache.timeout:3600}")
    private int cacheTimeout;

    @Bean(PREFIX + "BaseURL")
    public String getBaseURL(RepositoryRestConfiguration configuration) {
        return configuration.getBasePath().toString();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        resourceEndpoints.getEndPoints()
                         .entrySet()
                          .forEach(e -> {
                              log.debug(e.getKey() + "=" + e.getValue());

                              registry.addResourceHandler(e.getKey())
                                      .addResourceLocations(e.getValue())
                                      .setCachePeriod(cacheTimeout);
                             });
    }
}
