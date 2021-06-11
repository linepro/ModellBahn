package com.linepro.modellbahn.util.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class StackTraceFilter {

    private static final String DEFAULT_EXCLUSIONS = 
                    "java.lang.reflect.Method" + "," +
                    "ByCGLIB" + "," +
                    "BySpringCGLIB" + "," +
                    "com.zaxxer.hikari" + "," +
                    "java.lang.Thread" + "," +
                    "java.net" + "," +
                    "java.util.concurrent" + "," +
                    "javax" + "," +
                    "net.sf.cglib" + "," +
                    "org.apache.catalina" + "," +
                    "org.apache.coyote" + "," +
                    "org.apache.http" + "," +
                    "org.apache.tomcat" + "," +
                    "org.eclipse.jetty" + "," +
                    "org.h2" + "," +
                    "org.hibernate" + "," +
                    "org.springframework" + "," +
                    "sun.reflect" + "," +
                    "sun.security.ssl"
                    ;

    private static final int DEFAULT_DEPTH = 10;

    private List<String> exclusions;

    private int maxDepth;

    public StackTraceFilter(
        @Value("${com.linepro.modellbahn.stackTrace.exclude:" + DEFAULT_EXCLUSIONS +"}") String[] exclusions,
        @Value("${com.linepro.modellbahn.stackTrace.depth:" + DEFAULT_DEPTH + "}") int maxDepth) {
        this.exclusions = Arrays.asList(exclusions);
        this.maxDepth = maxDepth;
    }

    public StackTraceElement[] filterStackTrace(StackTraceElement[] raw) {
        if (raw != null) {
            return filter(raw).toArray(StackTraceElement[]::new);
        }

        return null;
    }

    public String getStackTrace(StackTraceElement[] raw) {
        if (raw != null) {
            return filter(raw).map(StackTraceElement::toString)
                              .collect(Collectors.joining("\n"));
        }

        return null;
    }

    public Stream<StackTraceElement> filter(StackTraceElement[] raw) {
        return Stream.of(raw)
                     .filter(this::accept)
                     .limit(maxDepth);
    }

    public boolean accept(StackTraceElement element) {
        for (String excluded : exclusions) {
            if (StringUtils.startsWith(element.getClassName(), excluded)) {
                return false;
            }
        }

        return true;
    }
}
