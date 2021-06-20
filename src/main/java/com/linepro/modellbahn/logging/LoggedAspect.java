package com.linepro.modellbahn.logging;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.core.pattern.NameAbbreviator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.event.Level;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Aspect that automagically wraps:
 *
 * - @Logged
 * - @Controller
 * - @Service
 *
 * with business logger logging.
 */
@Aspect
@Configuration(PREFIX + "LoggedAspect")
public class LoggedAspect {

    protected static final String LOGGED_ANNOTATION = "com.linepro.modellbahn.logging.Logged";

    protected static final String CLASS_LEVEL_POINTCUT = "within(@" + LOGGED_ANNOTATION + " *) || " +
            "within(@org.springframework.stereotype.Service *) || " +
            "within(@org.springframework.web.bind.annotation.RestController *)";

    protected static final String METHOD_LEVEL_POINTCUT = "@annotation(" + LOGGED_ANNOTATION + ")";

    protected static final Logged DEFAULT_LOGGING_POLICY = createPolicy(BusinessEvent.GENERIC, Level.INFO, false, false, false);

    protected static final Logged CONTROLLER_LOGGING_POLICY = createPolicy(BusinessEvent.GENERIC, Level.INFO, true, true, false);

    protected static final Logged SERVICE_LOGGING_POLICY = DEFAULT_LOGGING_POLICY;

    protected static final Logged NO_LOGGING_POLICY = createPolicy(BusinessEvent.GENERIC, Level.INFO, false, false, true);

    protected static final NameAbbreviator abbreviator = NameAbbreviator.getAbbreviator("2");

    /**
     * Pointcut that matches any class or interface tagged by @Logged, any @Service or @Controller.
     */
    @Pointcut(CLASS_LEVEL_POINTCUT)
    public void classLevelPointcut() {
    }

    /**
     * Pointcut that matches any method tagged by @Loggged.
     */
    @Pointcut(METHOD_LEVEL_POINTCUT)
    public void methodLevelPointcut() {
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "methodLevelPointcut() || classLevelPointcut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Throwable e) {
        Logged loggingPolicy = getLoggingPolicy(joinPoint);

        if (!loggingPolicy.ignore()) {
            BusinessLogger.logException(loggingPolicy.event(), getCallee(joinPoint.getSignature()), e, getLoggedArguments(joinPoint, loggingPolicy));
        }
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("methodLevelPointcut() || classLevelPointcut()")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Logged loggingPolicy = getLoggingPolicy(joinPoint);

        if (!loggingPolicy.ignore()) {
            BusinessLogger.logFunctionCall(loggingPolicy.level(), loggingPolicy.event(), getCallee(joinPoint.getSignature()), true, getLoggedArguments(joinPoint, loggingPolicy));
        }

        Object result = joinPoint.proceed();

        if (!loggingPolicy.ignore() && loggingPolicy.result()) {
            if (getMethod(joinPoint).getReturnType().equals(Void.TYPE)) {
                BusinessLogger.logFunctionCall(loggingPolicy.level(), loggingPolicy.event(), getCallee(joinPoint.getSignature()), false);
            } else {
                BusinessLogger.logFunctionCall(loggingPolicy.level(), loggingPolicy.event(), getCallee(joinPoint.getSignature()), false, result);
            }
        }

        return result;
    }

    Logged getLoggingPolicy(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        Logged logged = AnnotationUtils.findAnnotation(method, Logged.class);

        if (logged != null) {
            return logged;
        }

        Class<?> clazz = getClass(joinPoint);

        logged = AnnotationUtils.findAnnotation(clazz, Logged.class);

        if (logged != null) {
            return logged;
        }

        if (AnnotationUtils.findAnnotation(clazz, RestController.class) != null) {
            return CONTROLLER_LOGGING_POLICY;
        }

        if (AnnotationUtils.findAnnotation(clazz, Service.class) != null) {
            return SERVICE_LOGGING_POLICY;
        }

        return NO_LOGGING_POLICY;
    }

    String getCallee(Signature signature) {
        StringBuilder callee = new StringBuilder();
        abbreviator.abbreviate(String.join(".", signature.getDeclaringTypeName(), signature.getName()), callee);
        return callee.toString();
    }

    Class<?> getClass(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass();
    }

    Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

    Object[] getLoggedArguments(JoinPoint joinPoint, Logged loggingPolicy) {
        List<String> values = new ArrayList<>(getRequestAttributes());

        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = getMethod(joinPoint).getParameters();

        for (int i = 0; i < args.length; i++) {
            boolean ignore = parameters[i].isAnnotationPresent(Logged.class) && parameters[i].getAnnotation(Logged.class).ignore();
            boolean include = parameters[i].isAnnotationPresent(Logged.class) && !parameters[i].getAnnotation(Logged.class).ignore();

            if (include || (loggingPolicy.parameters() && !ignore)) {
                values.add(String.join("=", parameters[i].getName(), BusinessLogger.getLoggedValue(args[i]).toString()));
            }
        }

        return values.toArray(new String[0]);
    }

    List<String> getRequestAttributes() {
        List<String> attributes = new ArrayList<>();

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

            String path = request.getPathInfo();
            if (path != null) {
                attributes.add(path);
            }
            String query = request.getQueryString();
            if (query != null) {
                attributes.add(query);
            }
        } catch (Exception ignored) {
            // No current request...
        }

        return attributes;
    }

    static Logged createPolicy(BusinessEvent event, Level level, boolean parameters, boolean result, boolean ignore) {
        return new Logged() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return Logged.class;
            }

            @Override
            public BusinessEvent event() {
                return event;
            }

            @Override
            public Level level() {
                return level;
            }

            @Override
            public boolean parameters() {
                return parameters;
            }

            @Override
            public boolean result() {
                return result;
            }

            @Override
            public boolean ignore() {
                return ignore;
            }
        };
    }
}
