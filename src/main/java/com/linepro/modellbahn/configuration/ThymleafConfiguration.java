package com.linepro.modellbahn.configuration;

import org.apache.commons.codec.CharEncoding;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymleafConfiguration implements WebMvcConfigurer {

    @Bean
    public ITemplateResolver templateResolver() {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding(CharEncoding.UTF_8);

        return templateResolver;
    }

    @Bean
    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver, MessageSource messageSource) {

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setMessageSource(messageSource);
        templateEngine.setTemplateEngineMessageSource(messageSource);
        
        return templateEngine;
    }

    @Bean
    public ViewResolver viewResolver(ISpringTemplateEngine templateEngine) {

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding(CharEncoding.UTF_8);

        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        addPattern(registry, "index");
    }

    private void addPattern(ViewControllerRegistry registry, String pattern) {
        registry.addViewController("/" + pattern).setViewName(pattern);
        registry.addViewController("/" + pattern + ".htm*").setViewName(pattern);
    }
}
