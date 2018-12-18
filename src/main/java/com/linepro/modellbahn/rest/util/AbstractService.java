package com.linepro.modellbahn.rest.util;

import com.linepro.modellbahn.model.IDescribedEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractService.
 * Basic CRUD rest service 
 * @author $Author$
 * @version $Id$
 */
public abstract class AbstractService {

    private final Logger logger;

    /** The uri info injected by Jersey. */
    @Context
    protected UriInfo uriInfo;

    /**
     * Instantiates a new abstract service.
     */
    protected AbstractService() {
        this.logger = LoggerFactory.getILoggerFactory().getLogger(getClass().getName());
   }

    UriInfo getUriInfo() {
        return uriInfo;
    }

    protected Logger getLogger() {
        return logger;
    }

    protected List<DescribedEnumWrapper> getEnumList(IDescribedEnum[] values) {
        List<DescribedEnumWrapper> result = new ArrayList<>(values.length);
        
        for (IDescribedEnum value : values) {
            result.add(new DescribedEnumWrapper(value));
        }
        
        return result;
    }

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

    protected ResponseBuilder ok(Object entity) {
        return Response.ok(entity);
    }

    protected ResponseBuilder serverError(Exception e) {
        error("serverError", e);

        return serverError(null, e.getMessage());
    }


    protected ResponseBuilder serverError(final String errorCode, final String userMessage) {
        return Response.serverError().entity(new ErrorMessage(errorCode, userMessage));
    }
}