package com.linepro.modellbahn.configuration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.linepro.modellbahn.util.exceptions.StackTraceFilter;

@Service
public class ModellbahnErrorAttributes extends DefaultErrorAttributes {

    @Autowired
    private StackTraceFilter stackTraceFilter;

    public ModellbahnErrorAttributes() {
        this(false);
    }

    public ModellbahnErrorAttributes(boolean includeException) {
        super(includeException);
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
        if (includeStackTrace) {
            Throwable error = getError(request);
            if (error != null) {
                errorAttributes.put("trace", stackTraceFilter.getStackTrace(error.getStackTrace()));
            }
        }

        return errorAttributes;
    }
}