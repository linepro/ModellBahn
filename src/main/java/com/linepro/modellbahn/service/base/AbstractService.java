package com.linepro.modellbahn.service.base;

import lombok.extern.slf4j.Slf4j;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 */
@Slf4j
public abstract class AbstractService {

    /**
     * Instantiates a new abstract service.
     */
    protected AbstractService() {
    }

    protected void logDelete(Object message) {
        log.info("DELETE : " + message);
    }

    protected void logGet(Object message) {
        log.info("GET : " + message);
    }

    protected void logPost(Object message) {
        log.info("POST : " + message);
    }

    protected void logPut(Object message) {
        log.info("PUT : " + message);
    }
}
