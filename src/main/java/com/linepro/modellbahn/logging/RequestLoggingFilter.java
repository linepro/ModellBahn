package com.linepro.modellbahn.logging;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

/**
 * Implementation of CommonsRequestLoggingFilter that uses slf4j not JUL.
 */
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    public RequestLoggingFilter() {
        setIncludeClientInfo(true);
        setIncludeQueryString(true);
        setIncludePayload(true);
        setIncludeHeaders(false);
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isInfoEnabled();
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }
}

