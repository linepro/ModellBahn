package com.linepro.modellbahn.service.impl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    protected void logDelete(Object...args) {
        log.info("DELETE : " + message(args));
    }

    protected void logGet(Object...args) {
        log.info("GET : " + message(args));
    }

    protected void logPost(Object...args) {
        log.info("POST : " + message(args));
    }

    protected void logPut(Object...args) {
        log.info("PUT : " + message(args));
    }
    
    protected String message(Object...args) {
        return Stream.of(args).map(a -> a.toString()).collect(Collectors.joining(","));
    }
}
