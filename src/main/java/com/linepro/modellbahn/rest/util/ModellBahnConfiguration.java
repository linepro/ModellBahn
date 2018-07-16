package com.linepro.modellbahn.rest.util;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.TracingConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.slf4j.Logger;

import com.linepro.modellbahn.jersey.SecurityRequestFilter;

@ApplicationPath("/modellbahn")
public class ModellBahnConfiguration extends ResourceConfig {

    private static final String[] PACKAGES = { "com.linepro.modellbahn.rest" };

    @Inject
    public ModellBahnConfiguration(Logger logger) {
        packages(PACKAGES);

        register(SecurityRequestFilter.class);

        // MVC.
        property(MvcFeature.TEMPLATE_BASE_PATH, "/");
        
        register(JspMvcFeature.class);

        // Logging.
        //register(LoggingFilter.class);

        // Tracing support.
        register(DeclarativeLinkingFeature.class);

        property(ServerProperties.TRACING, TracingConfig.ALL.name());
    }
}