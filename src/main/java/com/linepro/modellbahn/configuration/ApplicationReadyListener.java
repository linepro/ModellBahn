package com.linepro.modellbahn.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import ch.qos.logback.classic.pattern.Abbreviator;
import ch.qos.logback.classic.pattern.TargetLengthBasedClassNameAbbreviator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

    private final Abbreviator abbreviator = new TargetLengthBasedClassNameAbbreviator(30);

    private ApplicationContext applicationContext;

    private static List<String> beanz = new ArrayList<>();
    
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        ApplicationContext context = event.getApplicationContext();

        if (applicationContext.equals(context)) {
            String application = context.getId();
            
            List<String> contextBeanz = Stream.of(context.getBeanDefinitionNames())
                    .map(b -> {
                        try {
                            return AopUtils.getTargetClass(context.getBean(b)).getName();       
                        } catch(Throwable e) {
                            return "";
                        }
                    })
                    .filter(b -> b.startsWith("com.linepro"))
                    .map(b -> b.substring(0, b.indexOf('$') > 0 ? b.indexOf('$') : b.length()))
                    .sorted()
                    .map(n -> abbreviator.abbreviate(n))
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
