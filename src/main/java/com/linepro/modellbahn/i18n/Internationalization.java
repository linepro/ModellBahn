package com.linepro.modellbahn.i18n;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.validation.MessageInterpolator;

import org.apache.commons.codec.CharEncoding;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.LocaleContextMessageInterpolator;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.i18n.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Import({
    MessageTranslatorImpl.class
})
@Component(PREFIX + "Internationalization")
public class Internationalization {

    protected static final String APPLICATION_MESSAGES = "ApplicationMessages";

    protected static final String DEFAULT_ENCODING = CharEncoding.UTF_8;

    protected static final int CACHE_TIMEOUT = 3600;

    protected static final String MESSAGES = "classpath:" + APPLICATION_MESSAGES;

    protected static final Locale DEFAULT_LOCALE = Locale.GERMAN;

    protected static final List<Locale> SUPPORTED_LOCALES = Arrays.asList(Locale.ENGLISH, DEFAULT_LOCALE);

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setSupportedLocales(SUPPORTED_LOCALES);
        resolver.setDefaultLocale(DEFAULT_LOCALE);
        return resolver;
    }

    @Bean
    public LocaleContextResolver localeContextResolver() {
        AcceptHeaderLocaleContextResolver resolver = new AcceptHeaderLocaleContextResolver();
        resolver.setSupportedLocales(SUPPORTED_LOCALES);
        resolver.setDefaultLocale(DEFAULT_LOCALE);
        return resolver;
    }

    @Bean
    public javax.validation.Validator validatorFactory() {
        LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();
        validatorFactory.setValidationMessageSource(messageSource());
        validatorFactory.setMessageInterpolator(messageInterpolator());
        return validatorFactory;
    }

    @Bean
    public MessageInterpolator messageInterpolator() {
        ResourceBundleMessageInterpolator defaultInterpolator = new ResourceBundleMessageInterpolator(resourceBundleLocator());

        return new LocaleContextMessageInterpolator(defaultInterpolator);
    }

    @Bean
    public ResourceBundleLocator resourceBundleLocator() {
        return new MessageSourceResourceBundleLocator(messageSource());
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGES);
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);
        messageSource.setCacheSeconds(CACHE_TIMEOUT);

        return messageSource;
    }
}
