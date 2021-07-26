package com.linepro.modellbahn.configuration;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.linepro.modellbahn.i18n.MessageTranslator;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;
import com.linepro.modellbahn.util.exceptions.StackTraceFilter;

import lombok.RequiredArgsConstructor;

@ControllerAdvice(basePackages = {"com.linepro.modellbahn.controller"})
@RequiredArgsConstructor
public class ModellBahnErrorHandler {

    private final MessageTranslator messageTranslator;

    private final StackTraceFilter stackTraceFilter;

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler({ 
        ConstraintViolationException.class, 
        org.hibernate.exception.ConstraintViolationException.class,
        DataIntegrityViolationException.class, 
        IllegalArgumentException.class, 
        IllegalStateException.class, 
        JsonMappingException.class, 
        JsonParseException.class,
        HttpMessageNotReadableException.class,
        HttpMediaTypeException.class,
        MissingServletRequestPartException.class,
        HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> badRequest(Exception ex, WebRequest request) throws Exception {
        return handle(ex, request, HttpStatus.BAD_REQUEST);
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler({ EntityExistsException.class })
    public ResponseEntity<Object> conflict(Exception ex, WebRequest request) throws Exception {
        return handle(ex, request, HttpStatus.CONFLICT);
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler({ ModellBahnException.class })
    public ResponseEntity<Object> modellBahn(Exception ex, WebRequest request) throws Exception {
        return handle(ex, request, ((ModellBahnException) ex).getStatus());
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Object> notFound(Exception ex, WebRequest request) throws Exception {
        return handle(ex, request, HttpStatus.NOT_FOUND);
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler({ NoResultException.class })
    public ResponseEntity<Object> noContent(Exception ex, WebRequest request) throws Exception {
        return  handle(ex, request, HttpStatus.NO_CONTENT);
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler({ SecurityException.class, HttpSessionRequiredException.class })
    public ResponseEntity<Object> forbidden(Exception ex, WebRequest request) throws Exception {
        return handle(ex, request, HttpStatus.FORBIDDEN);
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> others(Exception ex, WebRequest request) throws Exception {
        return handle(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<Object> handle(Exception ex, WebRequest request, HttpStatus status) throws Exception {
        Throwable effective = ex.getCause() != null ? ex.getCause() : ex;

        return ResponseEntity.status(status)
                             .body(
                                 UserMessage.builder()
                                             .timestamp(System.currentTimeMillis())
                                             .status(status.value())
                                             .error(status.getReasonPhrase())
                                             .message(message(effective))
                                             .path(request.getContextPath())
                                             .developerMessage(developerMessage(effective))
                                             .build()
                                             );

    }

    protected String message(Throwable ex) {
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
                        stackTraceFilter.getStackTrace(ex.getStackTrace())
                        );
    }
}
