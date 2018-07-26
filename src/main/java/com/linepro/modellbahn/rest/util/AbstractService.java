package com.linepro.modellbahn.rest.util;

import javax.ws.rs.core.Context;
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
    protected void info(String message) {
        getLogger().info(message);
    }
    
    protected void warn(String message) {
        getLogger().warn(message);
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