package com.linepro.modellbahn;

import java.net.URI;
import java.util.Collection;
import java.util.EnumSet;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletRegistration;

import org.apache.jasper.servlet.JspServlet;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
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
    
    public static final String JERSEY_SERVLET_CONTEXT_PATH = "";
    
    public static final String JSP_CLASSPATH_ATTRIBUTE = "org.apache.catalina.jsp_classpath";

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

    protected WebappContext configureJsp() {
        WebappContext context = new WebappContext("WebappContext", JERSEY_SERVLET_CONTEXT_PATH);
        
        // Initialize and register Jersey Servlet
        FilterRegistration registration = context.addFilter("ServletContainer", ServletContainer.class);
        registration.setInitParameter("javax.ws.rs.Application", ModellBahn.class.getName());
        registration.setInitParameter(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/jsp");
        // configure Jersey to bypass non-Jersey requests (static resources and jsps)
        registration.setInitParameter(ServletProperties.FILTER_STATIC_CONTENT_REGEX,
                "(/(image|js|css)/?.*)|(/.*\\.jsp)|(/WEB-INF/.*\\.jsp)|"
                + "(/WEB-INF/.*\\.jspf)|(/.*\\.html)|(/favicon\\.ico)|"
                + "(/robots\\.txt)");
        
        registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // Initialize and register JSP Servlet        
        ServletRegistration jspRegistration = context.addServlet("JSPContainer", JspServlet.class.getName());
        jspRegistration.addMapping("/*");
        
        // Set classpath for Jasper compiler based on the current classpath
        context.setAttribute(JSP_CLASSPATH_ATTRIBUTE, System.getProperty("java.class.path"));
        
        return context;
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

            configureJsp().deploy(server);

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