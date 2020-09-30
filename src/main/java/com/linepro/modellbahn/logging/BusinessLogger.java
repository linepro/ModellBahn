package com.linepro.modellbahn.logging;

/**
 * Formalized business logging on a common root logger so that we can filter it to the composite log.
 */
import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

@Component(PREFIX + "BusinessLogger")
public class BusinessLogger {

    /**
     * logs a message in the appropriate logger
     * @param level the log level of the message
     * @param event the event logger required
     * @param message the message. NBB the {} parameter placeholders are added as required if not specified them in the message
     * @param values the values to be logged
     */
    public static void logEvent(Level level, BusinessEvent event, String message, Object... values) {
        String format = appendPlaceholders(message, values);

        for (int i = 0; i < values.length; i++) {
            values[i] = getLoggedValue(values[i]);
        }

        switch (level)
        {
        case WARN:
            getLogger(event).warn(format, values);
            break;

        case DEBUG:
            getLogger(event).debug(format, values);
            break;

        case ERROR:
            getLogger(event).error(format, values);
            break;

        case TRACE:
            getLogger(event).trace(format, values);
            break;

        default:
            getLogger(event).info(format, values);
            break;
        }
    }

    /**
     * logs a function call to the business log
     * @param level the level for the log @See org.slf4j.event.Level
     * @param event the event being logged @See BusinessEvent
     * @param callee the canonical name of the function being called (i.e. full class name.function name}
     * @param enter if true log the start of a call, if false the end
     * @param values the function call parameters
     */
    public static void logFunctionCall(Level level, BusinessEvent event, String callee, boolean enter, Object... values) {
        Object[] params = new Object[values.length+2];
        params[0] = enter ? "Enter" : "Exit";
        params[1] = callee;
        System.arraycopy(values, 0, params, 2, values.length);

        logEvent(level, event, "", params);
    }

    /**
     * log an exception to the business log
     * @param event the event being logged @See BusinessEvent
     * @param callee the canonical name of the function being called (i.e. full class name.function name}
     * @param throwable the exception thrown
     * @param values the function call parameters
     */
    public static void logException(BusinessEvent event, String callee, Throwable throwable, Object... values) {
        Object[] params = new Object[values.length+2];
        params[0] = callee;
        params[1] = throwable.getMessage();
        System.arraycopy(values, 0, params, 2, values.length);
        logEvent(Level.ERROR, event, "Error calling", params);
    }

    /**
     * get the correct logger for the business event
     * @param event the event being logged @See BusinessEvent
     * @return the logger
     */
    static Logger getLogger(BusinessEvent event) {
        return LoggerFactory.getLogger(event.getLogCategory());
    }

    /**
     * Adds {} placeholders to the end of the message to match the number of values
     * @param message the message
     * @param values the values
     * @return the message with additional {} placeholders as required
     */
    static String appendPlaceholders(String message, Object[] values) {
        StringBuilder messageArgs = new StringBuilder(message);

        int placeholders = 0;

        for (int index = messageArgs.indexOf("{}"); 0 <= index; index = messageArgs.indexOf("{}", index+1)) {
            placeholders++;
        }

        for (int i = placeholders; i < values.length; i++) messageArgs.append(" {}");

        return messageArgs.toString().trim();
    }

    /**
     * gets a value to be logged
     * @param value the value to be logged
     * @return the value (if null, a primitive or if value directly implements toString) or reflection toString of value
     */
    static Object getLoggedValue(Object value) {
        if (value == null) {
            return value;
        }

        Class<?> type = value.getClass();

        if (ClassUtils.isPrimitiveOrWrapper(type)) {
            return value;
        }

        try {
            if (type.getDeclaredMethod("toString") != null) {
                return value;
            }
        } catch (Exception ignored) {
        }

        return ToStringBuilder.reflectionToString(value, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
