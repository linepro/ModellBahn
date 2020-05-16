package com.linepro.modellbahn.configuration;

import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = { Exception.class})
    protected ResponseEntity<?> handleConflict(Exception ex, WebRequest request) {

        String bodyOfResponse = "This should be application specific";

        if (ex instanceof PersistenceException) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                return badRequest(((ConstraintViolationException) ex.getCause()).getConstraintViolations().stream()
                            .map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")));
            }
        } else if (ex instanceof EntityExistsException) {
            return badRequest(ex.getMessage());
        } else if (ex instanceof EntityNotFoundException) {
            return badRequest(ex.getMessage());
        } else if (ex instanceof NonUniqueResultException) {
            return badRequest(ex.getMessage());
        } else if (ex instanceof NoResultException) {
            return badRequest(ex.getMessage());
        }

        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    
    protected ResponseEntity<?> accepted(Object entity) {
        return ResponseEntity.accepted().body(entity);
    }

    protected ResponseEntity<?> badRequest(final String userMessage) {
        return ResponseEntity.badRequest().body(new ErrorMessage(Status.BAD_REQUEST.getStatusCode(), userMessage));
    }

    protected ResponseEntity<?> created(Object entity) {
        return ResponseEntity.created(null).body(entity);
    }

    protected ResponseEntity<?> noContent() {
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> notFound() {
        return ResponseEntity.notFound().build();
    }

    protected ResponseEntity<?> notModified() {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    protected ResponseEntity<?> ok() {
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity<?> ok(Object entity) {
        return ResponseEntity.ok().body(entity);
    }

    protected ResponseEntity<?> serverError(Exception e) {
        log.error(Status.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);

        return serverError(Status.INTERNAL_SERVER_ERROR, Status.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage());
    }

    protected ResponseEntity<?> serverError(final StatusType errorCode, final String userMessage) {
        return serverError(errorCode, userMessage, null);
    }

    protected ResponseEntity<?> serverError(final StatusType errorCode, final String userMessage,
            final String developerMessage) {
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(new ErrorMessage(errorCode.getStatusCode(), userMessage, developerMessage));
    }

    protected ResponseEntity<?> getResponse(BodyBuilder builder) {
        return builder.build();
    }

}
