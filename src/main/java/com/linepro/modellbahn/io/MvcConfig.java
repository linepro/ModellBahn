package com.linepro.modellbahn.io;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    
    @Bean(name = "BaseURL")
    public String getBaseURL(RepositoryRestConfiguration configuration) {
        return configuration.getBasePath().toString();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/webjars/**","/resources/**")
          .addResourceLocations("/webjars/","/resources/","file:/opt/files/","classpath:/other-resources/")
          .setCachePeriod(3600)
          .resourceChain(true)
          .addResolver(new EncodedResourceResolver())
          .addResolver(new PathResourceResolver()); 
    }
}