package com.linepro.modellbahn.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.slf4j.event.Level;

/**
 * An annotation that:
 *
 * - binds the business logger to a class or interface. e.g.
 * <pre>
 * @Logged
 * public class BusinessLoggerTarget {
 * }
 * </pre>
 *
 * - modifies the logging visibility of a method. e.g.
 * <pre>
 * @Logged
 * public class BusinessLoggerTarget {
 *   @Logged(ignored=true)
 *   public void pleaseDontLogThis();
 * }
 * </pre>
 *
 * - modifies the logging visibility of a method parameter. e.g.
 * <pre>
 * @Logged
 * public class BusinessLoggerTarget {
 *   public void method(String logged, @Logged(ignore=true) String notLogged);
 * }
 * </pre>
 *
 * Things to be aware of:
 *
 * - <em>Annotated classes cannot have any private methods if you want to use spring injection (in particular @Autowired)</em>.
 *   This is because CGLIB proxies will be used which create a new dynamic subclass that is not controlled by spring); if no
 *   private methods are declared a JDK proxy is used to wrap the spring controlled object (and thus injection works).
 *
 * - If the annotation is applied to the class and the method, <em>the most specific applies</em>.
 *
 * - Parameter annotations only examine the ignore value.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE})
public @interface Logged {
    /**
     * The business event that should be used
     * @return the business event
     */
    BusinessEvent event() default BusinessEvent.GENERIC;

    /**
     * The level for logging
     * @return the slf4j log level for the event
     */
    Level level() default Level.INFO;

    /**
     * controls whether the method parameters (if any) should be logged
     * @return true log the parameters, false do not (can still be forced by @LoggedParameter)
     */
    boolean parameters() default false;

    /**
     * controls whether the exit from the method (with the result if any) should be logged
     * @return true log the exit, false do not
     */
    boolean result() default false;

    /**
     * allows methods to be ignored for logging when a class is marked for logging (or a class when a global pointcut is applied)
     * @return true do not log, false log
     */
    boolean ignore() default false;
}