package com.linepro.modellbahn;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.jersey.SecurityRequestFilter;
import com.linepro.modellbahn.jersey.ServerBuilder;
import com.linepro.modellbahn.persistence.DBPopulator;
import com.linepro.modellbahn.persistence.IItemPersisterFactory;
import com.linepro.modellbahn.rest.ModellBahnConfiguration;

public class ModellBahn implements IModellBahn {

    protected final Logger logger;
    protected final DBPopulator test;
    protected final URI baseUri;
    
    @Inject
    public ModellBahn(ILoggerFactory loggerFactory, DBPopulator test, @Assisted URI baseUri) {
        this.logger = loggerFactory.getLogger(getClass().getName());
        this.test = test;
        this.baseUri = baseUri;
    }

    @Override
    public void run() {
        try {
            logger.info(String.format("Application starting: " + baseUri.toString()));

            test.populate();
            
            HttpServer server = new ServerBuilder().getServer(baseUri, new ModellBahnConfiguration(logger));

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));

            server.start();

            logger.info("Application started.\nStop the application using CTRL+C");

            Thread.currentThread().join();
        } catch (Exception ex) {
            logger.error("Application start failed: ", ex);
        }
    }
}