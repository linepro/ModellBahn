package com.linepro.modellbahn.io;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import net.logstash.logback.encoder.org.apache.commons.lang3.ArrayUtils;

@Configuration(PREFIX + "MvcConfig")
@EnableWebMvc
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private final FileStore fileStore;

    @Value("${spring.mvc.resource.cache.timeout:3600}")
    private int cacheTimeout;

    public static final String[] RESOURCE_ENDPOINTS = {
                    "/webapp/**", "/webjars/**", "/static/**", "/resources/**"
    };

    public static final String[] RESOURCE_LOCATIONS = {
                    "file:webapp/", "file:webjars/", "file:static/", "file:resources/", "file:",
                    "classpath:webapp/", "classpath:webjars/", "classpath:static/", "classpath:resources/"
    };

    @Bean(PREFIX + "BaseURL")
    public String getBaseURL(RepositoryRestConfiguration configuration) {
        return configuration.getBasePath().toString();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCE_ENDPOINTS)
                .addResourceLocations(getResourceLocations());
    }

    private String[] getResourceLocations() {
        String fileStoreLoc = "file:" + FilenameUtils.separatorsToUnix(fileStore.fileStoreRoot().toString());

        return ArrayUtils.toStringArray(RESOURCE_LOCATIONS, fileStoreLoc);
    }
}
