package com.linepro.modellbahn.configuration;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import ch.qos.logback.classic.pattern.Abbreviator;
import ch.qos.logback.classic.pattern.TargetLengthBasedClassNameAbbreviator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(PREFIX + "ApplicationReadyListener")
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

    private final Abbreviator abbreviator = new TargetLengthBasedClassNameAbbreviator(30);

    private ApplicationContext applicationContext;

    private static List<String> beanz = new ArrayList<>();

    public void onApplicationEvent(final ApplicationReadyEvent event) {
        if (log.isDebugEnabled()) {
            ApplicationContext context = event.getApplicationContext();
    
            if (applicationContext.equals(context)) {
                String application = context.getApplicationName();
                List<String> contextBeanz = Stream.of(context.getBeanDefinitionNames())
                        .map(this::beanEntry)
                        .sorted(Comparator.comparing(Entry::getKey))
                        .filter(e -> StringUtils.hasText(e.getValue()))
                        .map(e -> String.join(": " , e.getKey(), e.getValue()))
                        .collect(Collectors.toList());
    
                if (CollectionUtils.isNotEmpty(contextBeanz) && !CollectionUtils.isEqualCollection(beanz, contextBeanz)) {
                    beanz = contextBeanz;
    
                    List<String> endPoints;
    
                    try {
                        endPoints = applicationContext.getBeansOfType(RequestMappingHandlerMapping.class)
                                                      .values()
                                                      .stream()
                                                      .flatMap(r -> r.getHandlerMethods().keySet().stream().map(h -> h.toString()))
                                                      .collect(Collectors.toList());
                    } catch (Exception e) {
                        endPoints = Collections.emptyList();
                    }
    
                    log.info("{} started with {} endpoints: {} and {} beans: {}", application, endPoints.size(), endPoints, beanz.size(), beanz);
                }
            }
        }
    }

    private Entry<String,String> beanEntry(String beanName) {
        return new SimpleImmutableEntry<String,String>(beanName, Optional.ofNullable(beanClassName(beanName)).orElse(""));
    }

    private String beanClassName(String beanName) {
        try {
            String className = AopUtils.getTargetClass(applicationContext.getBean(beanName)).getName();

            if (className == null || !className.contains("com.linepro")) {
                return null;
            }

            return abbreviator.abbreviate(className.substring(0, className.indexOf('$') > 0 ? className.indexOf('$') : className.length()));
        } catch(Throwable e) {
        }

        return null;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
