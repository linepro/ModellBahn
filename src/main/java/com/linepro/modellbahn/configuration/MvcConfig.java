package com.linepro.modellbahn.configuration;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.linepro.modellbahn.io.ResourceEndpoints;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration(PREFIX + "MvcConfig")
@EnableWebMvc
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private final ResourceEndpoints resourceEndpoints;

    @Value("${spring.mvc.resource.cache.timeout:60}")
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
                             log.info(e.getKey() + "=" + e.getValue());

                             registry.addResourceHandler(e.getKey())
                                     .addResourceLocations(e.getValue().toArray(new String[0]))
                                     .setCachePeriod(cacheTimeout);
                         });
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
