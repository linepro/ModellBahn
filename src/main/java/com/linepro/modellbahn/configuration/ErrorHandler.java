package com.linepro.modellbahn.configuration;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.i18n.MessageTranslator;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;

import lombok.RequiredArgsConstructor;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorHandler {

    private ResponseEntityExceptionHandler delegate = new ResponseEntityExceptionHandler() {};

    private final MessageTranslator messageTranslator;

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
                                                 .message(message(effective))
                                                 .path(contextPath)
                                                 .developerMessage(developerMessage(effective))
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
        } else if (ex instanceof ModellBahnException) {
            return ((ModellBahnException) ex).getStatus();
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
        } else if (ex instanceof JsonParseException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotReadableException) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String message(Throwable ex) {
        if (ex instanceof ConstraintViolationException) {
            return ((ConstraintViolationException) ex).getConstraintViolations().stream().map(v -> v.getMessage()).collect(Collectors.joining(",\n"));
        } else if (ex instanceof ModellBahnException) {
            return messageTranslator.getMessage(ex.getMessage(), ((ModellBahnException) ex).getValues());
        } 

        String message = ex.getMessage();
        int nested = message.indexOf("nested exception");
        
        if (nested > 0) {
            return message.substring(0, nested).trim();
        }

        return message;
    }

    private String developerMessage(Throwable ex) {
        return String.join(": ", 
                        Optional.ofNullable(ex.getMessage()).orElse(ex.getClass().getSimpleName()), 
                        Stream.of(ex.getStackTrace())
                              .filter(t -> t.getClassName().contains("linepro"))
                              .map(t -> t.toString())
                              .collect(Collectors.joining("\n"))
                        );
    }
}
