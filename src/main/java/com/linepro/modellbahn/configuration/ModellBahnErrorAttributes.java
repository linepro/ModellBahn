package com.linepro.modellbahn.configuration;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.common.net.HttpHeaders;
import com.linepro.modellbahn.util.exceptions.StackTraceFilter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModellBahnErrorAttributes extends DefaultErrorAttributes {

    private static final ErrorAttributeOptions OPTIONS = ErrorAttributeOptions.of(
                    Include.BINDING_ERRORS, 
                    Include.EXCEPTION, 
                    Include.MESSAGE, 
                    Include.STACK_TRACE
                    );

    private final StackTraceFilter stackTraceFilter;

    private final ModellBahnErrorViewResolver resolver;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        super.resolveException(request, response, handler, ex);

        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        Map<String, Object> attributes = getErrorAttributes(new ServletWebRequest(request), OPTIONS);

        ModelAndView mav;
        if (StringUtils.contains(request.getHeader(HttpHeaders.ACCEPT), MediaType.APPLICATION_JSON_VALUE)) {
            mav = new ModelAndView(new MappingJackson2JsonView());
            mav.setStatus(status);
            mav.addObject(attributes);
        } else {
            mav = resolver.resolveErrorView(request, status, attributes);
        }
        
        return mav;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);
        if (options.isIncluded(Include.STACK_TRACE)) {
            Throwable error = getError(request);
            if (error != null) {
                StackTraceElement[] trace = (error.getCause() != null ? error.getCause() : error).getStackTrace();
                errorAttributes.put("trace", stackTraceFilter.getStackTrace(trace));
            }
        }

        return errorAttributes;
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        return super.getError(webRequest);
    }
}