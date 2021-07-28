package com.linepro.modellbahn.configuration;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.linepro.modellbahn.util.exceptions.StackTraceFilter;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class ModellBahnErrorAttributes extends DefaultErrorAttributes {

    private final StackTraceFilter stackTraceFilter;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);

        if (options.isIncluded(Include.STACK_TRACE)) {
            addStackTrace(errorAttributes, getError(request));
        }

        return errorAttributes;
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        Throwable exception = super.getError(webRequest);
        if (exception != null) {
            return exception.getCause() != null ? exception.getCause() : exception;
        }
        return exception;
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        if (error != null) {
            errorAttributes.put("trace", stackTraceFilter.getStackTrace(error.getStackTrace()));
        }
    }
}