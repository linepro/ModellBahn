package com.linepro.modellbahn.rest;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;

import com.linepro.modellbahn.jersey.SecurityRequestFilter;

@ApplicationPath("/modellbahn")
public class ModellBahnConfiguration extends ResourceConfig {

    private static final String[] PACKAGES = { "com.linepro.modellbahn.rest" };

    @Inject
    public ModellBahnConfiguration(Logger logger) {
        packages(PACKAGES);

        // Register my custom provider - not needed if it's in my.package.
        register(SecurityRequestFilter.class);

        // Register an instance of LoggingFilter.
        //register(new LogFilter(LOGGER, true));
        
        // Enable Tracing support.
        property(ServerProperties.TRACING, "ALL");
    }
}