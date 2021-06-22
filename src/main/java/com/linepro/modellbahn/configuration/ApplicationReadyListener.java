package com.linepro.modellbahn.configuration;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import ch.qos.logback.classic.pattern.Abbreviator;
import ch.qos.logback.classic.pattern.TargetLengthBasedClassNameAbbreviator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(PREFIX + "ApplicationReadyListener")
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware, Comparator<RequestMappingInfo> {

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
                        .filter(e -> StringUtils.isNotBlank(e.getValue()))
                        .map(e -> String.join(": " , e.getKey(), e.getValue()))
                        .collect(Collectors.toList());
    
                if (CollectionUtils.isNotEmpty(contextBeanz) && !CollectionUtils.isEqualCollection(beanz, contextBeanz)) {
                    beanz = contextBeanz;
    
                    List<String> endPoints;
    
                    try {
                        endPoints = applicationContext.getBeansOfType(RequestMappingHandlerMapping.class)
                                                      .values()
                                                      .stream()
                                                      .map(RequestMappingHandlerMapping::getHandlerMethods)
                                                      .map(Map::keySet)
                                                      .flatMap(Set::stream)
                                                      .sorted((a, b) -> compare(a, b))
                                                      .map(RequestMappingInfo::toString)
                                                      .collect(Collectors.toList());
                    } catch (Exception e) {
                        log.error("can't list end points {}: {}", e.getMessage(), e);
                        endPoints = Collections.emptyList();
                    }
    
                    log.info("{} started with {} endpoints: {} and {} beans: {}", application, endPoints.size(), endPoints, beanz.size(), beanz);
                }
            }
        }
    }

    public int compare(Collection<?> a, Collection<?> b) {
        int comparison = a.size() - b.size();

        if (comparison == 0) {
            if (a.size() > 0) {
                Object[] as = a.toArray();
                Object[] bs = b.toArray();

                for (int i = 0; i < a.size() && comparison == 0; i++) {
                    comparison = as[i].toString().compareTo(bs[i].toString());
                }
            }
        }

        return comparison;
    }

    @Override
    public int compare(RequestMappingInfo a, RequestMappingInfo b) {
        int comparison = 0;

        Set<String> aP = a.getPatternValues();
        Set<String> bP = b.getPatternValues();

        if (aP != null && bP != null) {
            comparison = compare(aP, bP);

            if (comparison == 0) {
                Set<RequestMethod> aM = a.getMethodsCondition().getMethods();
                Set<RequestMethod> bM = b.getMethodsCondition().getMethods();
    
                if (aM != null && bM != null) {
                    comparison = compare(aM, bM);
                } else if (aM == null) {
                    comparison = 1;
                } else if (bM == null) {
                    comparison = -1;
                }
            }
        } else if (aP == null) {
            comparison = 1;
        } else if (bP == null) {
            comparison = -1;
        }

        return comparison;
    }

    protected Entry<String,String> beanEntry(String beanName) {
        return new SimpleImmutableEntry<String,String>(beanName, Optional.ofNullable(beanClassName(beanName)).orElse(""));
    }

    protected String beanClassName(String beanName) {
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
