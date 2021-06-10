package com.linepro.modellbahn.configuration;

import org.apache.commons.codec.CharEncoding;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
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
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

    @Bean
    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver, MessageSource messageSource, SpringSecurityDialect securityDialect) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(securityDialect);
        templateEngine.setMessageSource(messageSource);
        templateEngine.setTemplateEngineMessageSource(messageSource);
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ViewResolver viewResolver(ISpringTemplateEngine templateEngine) {

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding(CharEncoding.UTF_8);

        return viewResolver;
    }
}
