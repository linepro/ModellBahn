package com.linepro.modellbahn.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.UriInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 */
@Slf4j
public abstract class AbstractController {

    /** The uri info injected by Jersey. */
    @Context
    protected UriInfo uriInfo;

    @Context
    protected HttpServletRequest request;

    /**
     * Instantiates a new abstract service.
     */
    protected AbstractController() {
    }

    protected UriBuilder getUriInfo() {
        return ServletUriComponentsBuilder.fromCurrentRequest();
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected void debug(final String message) {
        log.debug(message);
    }

    protected void error(final String message) {
        log.error(message);
    }

    protected void error(String message, Exception e) {
        log.error(message, e);
    }

    private void info(final String message) {
        log.info(message);
    }

    protected void warn(final String message) {
        log.warn(message);
    }

    protected void logDelete(Object message) {
        info("DELETE : " + message);
    }

    protected void logGet(Object message) {
        info("GET : " + message);
    }

    protected void logPost(Object message) {
        info("POST : " + message);
    }

    protected void logPut(Object message) {
        info("PUT : " + message);
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
        error(Status.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);

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
