package com.linepro.modellbahn.rest.util;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractService.
 * Basic CRUD rest service 
 * @author $Author$
 * @version $Id$
 * 
 * @param <E> the element type
 */
public abstract class AbstractService {

    protected final Logger logger;

    /** The uri info injected by Jersey. */
    @Context
    protected UriInfo uriInfo;

    /**
     * Instantiates a new abstract service.
     *
     * @param entityClass the entity class
     * @param persister the persister
     */
    public AbstractService() {
        this.logger = LoggerFactory.getILoggerFactory().getLogger(getClass().getName());
   }

    /**
     * Gets the uri info.
     *
     * @return the uri info
     */
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Info.
     *
     * @param message the message
     */
    protected void info(final String message) {
        getLogger().info(message);
    }
    
    protected void warn(final String message) {
        getLogger().warn(message);
    }

    protected ResponseBuilder accepted() {
        return Response.accepted();
    }

    protected ResponseBuilder badRequest(final String errorCode, final String userMessage) {
        return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(errorCode, userMessage));
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

    protected ResponseBuilder serverError(Exception e) {
        error("serverError", e);

        return serverError(null, e.getMessage());
    }


    protected ResponseBuilder serverError(final String errorCode, final String userMessage) {
        return Response.serverError().entity(new ErrorMessage(errorCode, userMessage));
    }

    /**
     * Error.
     *
     * @param message the message
     * @param e the e
     * @throws Exception the exception
     */
    protected void error(String message, Exception e) {
        getLogger().error(message, e);
    }
}