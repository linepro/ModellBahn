package com.linepro.modellbahn.configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationReadyListener.class);

    private ApplicationContext applicationContext;

    public void onApplicationEvent(final ApplicationReadyEvent event) {
        ApplicationContext context = event.getApplicationContext();

        if (applicationContext.equals(context)) {
            String application = context.getId();
            List<String> beanz = Stream.of(context.getBeanDefinitionNames())
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
                    .collect(Collectors.toList());

            logger.info("{} started with these beans: {}", application, beanz);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
