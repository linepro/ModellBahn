package com.linepro.modellbahn.configuration;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.linepro.modellbahn.util.exceptions.StackTraceFilter;

@Service
public class ModellBahnErrorAttributes extends DefaultErrorAttributes {

    private static final ErrorAttributeOptions OPTIONS = ErrorAttributeOptions.of(
            Include.BINDING_ERRORS,
            Include.EXCEPTION,
            Include.MESSAGE,
            Include.STACK_TRACE
            );

    @Autowired
    private StackTraceFilter stackTraceFilter;

    @Autowired
    private ModellBahnErrorViewResolver resolver;

    @Autowired
    private JsonRequestFilter requestFilter;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        super.resolveException(request, response, handler, ex);

        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        Map<String, Object> attributes = getErrorAttributes(new ServletWebRequest(request), OPTIONS);

        ModelAndView mav;
        if (requestFilter.isJsonRequest(request)) {
            mav = new ModelAndView(new MappingJackson2JsonView());
            mav.setStatus(status);
            mav.addObject("status", status.value());
            mav.addObject("timestamp", ((Date) attributes.get("timestamp")).getTime());
            mav.addObject("error", attributes.get("message").toString());
            mav.addObject("message", attributes.get("exception").toString());
            mav.addObject("developerMessage", attributes.get("trace").toString());
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