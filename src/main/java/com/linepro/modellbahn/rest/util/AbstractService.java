package com.linepro.modellbahn.rest.util;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;

/**
 * AbstractService.
 * Basic CRUD rest service 
 * @author $Author$
 * @version $Id$
 */
public abstract class AbstractService {

    /** The uri info injected by Jersey. */
    @Context
    protected UriInfo uriInfo;
    
    @Context
    protected HttpServletRequest request;
    
    /**
     * Instantiates a new abstract service.
     */
    protected AbstractService() {
    }

    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected abstract Logger getLogger();

    protected void debug(final String message) {
        getLogger().debug(message);
    }

    protected void error(final String message) {
        getLogger().error(message);
    }

    protected void error(String message, Exception e) {
        getLogger().error(message, e);
    }

    private void info(final String message) {
        getLogger().info(message);
    }
    
    protected void warn(final String message) {
        getLogger().warn(message);
    }

    protected void logDelete(String message) {
        info("DELETE : " + message);
    }

    protected void logGet(String message) {
        info("GET : " + message);
    }

    protected void logPost(String message) {
        info("POST : " + message);
    }

    protected void logPut(String message) {
        info("PUT : " + message);
    }

    protected ResponseBuilder accepted() {
        return Response.accepted();
    }

    protected ResponseBuilder badRequest(final String userMessage) {
        return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(Status.BAD_REQUEST.getStatusCode(), userMessage));
    }

    protected ResponseBuilder created() {
        return Response.status(Status.CREATED);
    }

    protected ResponseBuilder noContent() {
        return Response.noContent();
    }

    protected ResponseBuilder notFound() {
        return Response.status(Status.NOT_FOUND);
    }

    protected ResponseBuilder notModified() {
        return Response.status(Status.NOT_MODIFIED);
    }

    protected ResponseBuilder ok() {
        return Response.ok();
    }

    protected ResponseBuilder ok(Object entity) {
        return Response.ok(entity);
    }

    protected ResponseBuilder serverError(Exception e) {
        error(Status.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);

        return serverError(Status.INTERNAL_SERVER_ERROR, Status.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
    }

    protected ResponseBuilder serverError(final StatusType errorCode, final String userMessage) {
        return serverError(errorCode, userMessage, null);
    }

    protected ResponseBuilder serverError(final StatusType errorCode, final String userMessage, final String developerMessage) {
        return Response.status(errorCode).entity(new ErrorMessage(errorCode.getStatusCode(), userMessage, developerMessage));
    }

    protected String getMessage(String messageCode, Object... args) {
        Locale locale = (request != null) ? request.getLocale() : Locale.GERMAN;

        ResourceBundle bundle = ResourceBundle.getBundle("ValidationMessages", locale);

        String format = bundle.getString(messageCode);

        return String.format(format, args);
    }
}