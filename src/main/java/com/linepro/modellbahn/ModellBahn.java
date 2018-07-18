package com.linepro.modellbahn;

import java.net.URI;

import javax.inject.Inject;

import org.glassfish.grizzly.http.server.HttpServer;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.jersey.ServerBuilder;
import com.linepro.modellbahn.rest.util.ModellBahnConfiguration;
import com.linepro.modellbahn.util.DBPopulator;

public class ModellBahn implements IModellBahn {

    protected final Logger logger;
    protected final DBPopulator populator;
    protected final URI baseUri;
    
    @Inject
    public ModellBahn(ILoggerFactory loggerFactory, DBPopulator populator, @Assisted URI baseUri) {
        this.logger = loggerFactory.getLogger(getClass().getName());
        this.populator = populator;
        this.baseUri = baseUri;
    }

    @Override
    public void run() {
        try {
            logger.info(String.format("Application starting: " + baseUri.toString()));

            populator.populate();
            
            ModellBahnConfiguration configuration = new ModellBahnConfiguration();
            HttpServer server = new ServerBuilder().getServer(baseUri, configuration);

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));

            server.start();

            logger.info("Application started with WADL available at {}/application.wadl\nPress CTRL^C (SIGINT) to terminate.",
                    baseUri);

            Thread.currentThread().join();

            logger.info("Shutting server down..");
        } catch (Exception ex) {
            logger.error("Application start failed: ", ex);
        }
    }
}