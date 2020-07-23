package com.linepro.modellbahn.configuration;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.linepro.modellbahn.controller.impl.ApiPaths;

import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

@ControllerAdvice
public class ErrorHandler {

    private ResponseEntityExceptionHandler delegate = new ResponseEntityExceptionHandler() {};

    @Produces(MediaType.APPLICATION_JSON)
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) throws Exception {
        String contextPath = getRequestPath(request);
        if (StringUtils.isNotBlank(contextPath)) {
            Throwable effective = getEffective(ex);
            HttpStatus status = classify(effective);

            return ResponseEntity.status(status)
                                 .body(
                                     ErrorMessage.builder()
                                                 .timestamp(System.currentTimeMillis())
                                                 .status(status.value())
                                                 .error(status.getReasonPhrase())
                                                 .message(effective.getMessage())
                                                 .path(contextPath)
                                                 .developerMessage(getDeveloperMessage(effective))
                                                 .build()
                                                 );
                            
        }
        
        return delegate.handleException(ex, request);
    }

    private String getRequestPath(WebRequest request) {
        String requestPath = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        
        if (StringUtils.contains(requestPath, ApiPaths.API_ROOT)) {
            return requestPath.substring(requestPath.lastIndexOf(ApiPaths.API_ROOT));
        }
        
        return null;
    }

    private Throwable getEffective(Exception ex) {
        if (ex.getCause() != null) {
            return ex;   
        }

        return ex;
    }

    private HttpStatus classify(Throwable ex) {
        if (ex instanceof ConstraintViolationException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof DataIntegrityViolationException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof EntityExistsException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof EntityNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (ex instanceof IllegalArgumentException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof IllegalStateException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof NoResultException) {
            return HttpStatus.NO_CONTENT;
        } else if (ex instanceof SecurityException) {
            return HttpStatus.FORBIDDEN;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String getDeveloperMessage(Throwable ex) {
        return String.join(": ", 
                        Optional.ofNullable(ex.getMessage()).orElse(ex.getClass().getSimpleName()), 
                        Stream.of(ex.getStackTrace())
                              .filter(t -> t.getClassName().contains("linepro"))
                              .map(t -> t.toString())
                              .collect(Collectors.joining("\n"))
                        );
    }
}
