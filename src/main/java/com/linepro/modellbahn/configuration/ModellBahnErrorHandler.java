package com.linepro.modellbahn.configuration;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.linepro.modellbahn.i18n.MessageTranslator;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;
import com.linepro.modellbahn.util.exceptions.StackTraceFilter;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ModellBahnErrorHandler extends ResponseEntityExceptionHandler {

    private final MessageTranslator messageTranslator;

    private final StackTraceFilter stackTraceFilter;

    @ExceptionHandler({ 
        ConstraintViolationException.class,
        DataIntegrityViolationException.class, 
        IllegalArgumentException.class, 
        IllegalStateException.class, 
        JsonMappingException.class, 
        JsonParseException.class,
        HttpMediaTypeException.class})
    public ResponseEntity<Object> badRequest(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex, null, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ EntityExistsException.class })
    public ResponseEntity<Object> conflict(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex, null, null, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ ModellBahnException.class })
    public ResponseEntity<Object> modellBahn(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex, null, null, ((ModellBahnException) ex).getStatus(), request);
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Object> notFound(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ NoResultException.class })
    public ResponseEntity<Object> noContent(Exception ex, WebRequest request) throws Exception {
        return  handleExceptionInternal(ex, null, null, HttpStatus.NO_CONTENT, request);
    }

    @ExceptionHandler({ 
        SecurityException.class, 
        HttpSessionRequiredException.class 
        })
    public ResponseEntity<Object> forbidden(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex, null, null, HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> others(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex, null, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable effective = ex.getCause() != null ? ex.getCause() : ex;

        return ResponseEntity.status(status)
                             .contentType(MediaType.APPLICATION_JSON)
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