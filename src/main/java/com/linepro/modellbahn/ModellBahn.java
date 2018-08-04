package com.linepro.modellbahn;

import java.net.URI;
import java.util.Collection;

import javax.inject.Inject;

import org.glassfish.grizzly.http.server.HttpServer;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.jersey.ServerBuilder;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.ModellBahnConfiguration;
import com.linepro.modellbahn.util.DBPopulator;
import com.linepro.modellbahn.util.StaticContentFinder;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * ModellBahn. The ModellBahn application
 * 
 * @author $Author:$
 * @version $Id:$
 */
public class ModellBahn implements IModellBahn {

    /** The logger. */
    protected final Logger logger;

    /** The populator. */
    protected final DBPopulator populator;

    /** The base uri. */
    protected final URI baseUri;

    /**
     * Instantiates a new modell bahn.
     *
     * @param loggerFactory
     *            the logger factory
     * @param populator
     *            the populator
     * @param baseUri
     *            the base uri
     */
    @Inject
    public ModellBahn(ILoggerFactory loggerFactory, DBPopulator populator, @Assisted URI baseUri,
            @Assisted Collection<String> staticRoots) {
        this.logger = loggerFactory.getLogger(getClass().getName());
        this.populator = populator;
        this.baseUri = baseUri;
        
        StaticContentFinder.get().addPaths(staticRoots);
    }

    @Override
    public void run() {
        try {
            logger.info(String.format("Application starting: " + baseUri.toString()));

            populator.populate();

            ModellBahnConfiguration configuration = new ModellBahnConfiguration();

            BeanConfig swaggerConfig = new BeanConfig();
            swaggerConfig.setVersion(ApiPaths.VERSION);
            swaggerConfig.setSchemes(new String[] { baseUri.getScheme() });
            swaggerConfig.setHost(baseUri.getHost());
            swaggerConfig.setBasePath(ApiPaths.API_ROOT);
            swaggerConfig.setResourcePackage(configuration.getPackages());
            swaggerConfig.setScan(true);

            configuration.register();

            HttpServer server = new ServerBuilder().getServer(baseUri, configuration);

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            
            server.start();

            logger.info("Application started with WADL available at {}/{}\n" +
                    "Static content served on {}{} from {}\n" +
                    "API served on {}{}\n" +
                    "Press CTRL^C (SIGINT) to terminate.",
                    baseUri, ApiPaths.APPLICATION_WADL,
                    baseUri, ApiPaths.WEB_ROOT, StaticContentFinder.get().getAbsolutePaths(),
                    baseUri, ApiPaths.API_ROOT);

            Thread.currentThread().join();

            logger.info("Shutting server down..");
        } catch (Exception ex) {
            logger.error("Application start failed: ", ex);
        }
    }
}