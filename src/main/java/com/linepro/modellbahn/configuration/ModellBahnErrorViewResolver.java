package com.linepro.modellbahn.configuration;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ModellBahnErrorViewResolver extends DefaultErrorViewResolver {

    private static final Map<HttpStatus,String> ERRORS = Stream.of(
                      new SimpleImmutableEntry<>(HttpStatus.BAD_REQUEST, "error/400"),
                      new SimpleImmutableEntry<>(HttpStatus.UNAUTHORIZED, "error/401"),
                      new SimpleImmutableEntry<>(HttpStatus.FORBIDDEN, "error/403"),
                      new SimpleImmutableEntry<>(HttpStatus.NOT_FOUND, "error/404"),
                      new SimpleImmutableEntry<>(HttpStatus.INTERNAL_SERVER_ERROR, "error/500")
                    )
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

    public ModellBahnErrorViewResolver(ApplicationContext applicationContext, Resources resources) {
        super(applicationContext, resources);
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        String viewName = Optional.ofNullable(ERRORS.get(status)).orElse("error");
        return new ModelAndView(viewName, model);
    }
}