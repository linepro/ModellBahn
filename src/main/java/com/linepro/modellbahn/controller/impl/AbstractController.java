package com.linepro.modellbahn.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

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

    protected void logDelete(Object...message) {
        info("DELETE : " + message);
    }

    protected void logGet(Object...message) {
        info("GET : " + message);
    }

    protected void logPost(Object...message) {
        info("POST : " + message);
    }

    protected void logPut(Object...message) {
        info("PUT : " + message);
    }
}
