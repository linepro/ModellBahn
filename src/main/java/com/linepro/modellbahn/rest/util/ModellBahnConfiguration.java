package com.linepro.modellbahn.rest.util;

import java.net.URI;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.stream.JsonGenerator;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletRegistration;
import javax.ws.rs.ApplicationPath;

import org.apache.jasper.servlet.JspServlet;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.linepro.modellbahn.ModellBahn;
import com.linepro.modellbahn.jersey.LoggingFilter;
import com.linepro.modellbahn.jersey.SecurityRequestFilter;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * ModellBahnConfiguration.
 * Jersey configuration for the ModellBahn application 
 * @author  $Author:$
 * @version $Id:$
 */
@ApplicationPath(ApiPaths.SEPARATOR)
public class ModellBahnConfiguration extends ResourceConfig {

    private static final String JERSEY_SERVLET_CONTEXT_PATH = "";

    private static final String JSP_CLASSPATH_ATTRIBUTE = "org.apache.catalina.jsp_classpath";

    private static final String PACKAGES = "com.linepro.modellbahn.rest";

    /**
     * Instantiates a new modell bahn configuration.
     */
    @Inject
    public ModellBahnConfiguration() {
    }
    
    public void register(URI baseUri) {
        packages(PACKAGES);
        
        property(JsonGenerator.PRETTY_PRINTING, true);

        // MVC.
        //property(MvcFeature.TEMPLATE_BASE_PATH, "/jsp");
        property(ServerProperties.MONITORING_ENABLED, true);
        property(ServerProperties.MONITORING_STATISTICS_ENABLED, true);
        property(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);

        property(ServerProperties.TRACING, "ON_DEMAND");
        property(ServerProperties.TRACING_THRESHOLD, "SUMMARY");

        /*
        property(ServletProperties.FILTER_CONTEXT_PATH, "/ModellBahn"); 
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, "//web//**//*");

        property(ClassNamesResourceConfig.PROPERTY_CLASSNAMES, "");
        property(ClasspathResourceConfig.PROPERTY_CLASSPATH, "");
        property(FeaturesAndProperties.FEATURE_DISABLE_XML_SECURITY, "");
        property(FeaturesAndProperties.FEATURE_FORMATTED, "");
        property(FeaturesAndProperties.FEATURE_PRE_1_4_PROVIDER_PRECEDENCE, "");
        property(FeaturesAndProperties.FEATURE_XMLROOTELEMENT_PROCESSING, "");
        property(JSONMarshaller.FORMATTED, "");
        property(LoggingFilter.FEATURE_LOGGING_DISABLE_ENTITY, "");
        property(PackagesResourceConfig.PROPERTY_PACKAGES, "");
        property(ResourceConfig.FEATURE_CANONICALIZE_URI_PATH, "");
        property(ResourceConfig.FEATURE_DISABLE_WADL, "");
        property(ResourceConfig.FEATURE_IMPLICIT_VIEWABLES, "");
        property(ResourceConfig.FEATURE_MATCH_MATRIX_PARAMS, "");
        property(ResourceConfig.FEATURE_NORMALIZE_URI, "");
        property(ResourceConfig.FEATURE_REDIRECT, "");
        property(ResourceConfig.FEATURE_TRACE, "");
        property(ResourceConfig.FEATURE_TRACE_PER_REQUEST, "");
        property(ResourceConfig.PROPERTY_CONTAINER_NOTIFIER, "");
        property(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, "");
        property(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, "");
        property(ResourceConfig.PROPERTY_DEFAULT_RESOURCE_COMPONENT_PROVIDER_FACTORY_CLASS, "");
        property(ResourceConfig.PROPERTY_LANGUAGE_MAPPINGS, "");
        property(ResourceConfig.PROPERTY_MEDIA_TYPE_MAPPINGS, "");
        property(ResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES, "");
        property(ResourceConfig.PROPERTY_WADL_GENERATOR_CONFIG, "");
        property(ServletProperties.APPLICATION_CONFIG_CLASS, "");
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, true);
        property(ServletProperties.GLASSFISH_DEFAULT_ERROR_PAGE_RESPONSE, "");
        property(ServletProperties.PROPERTY_FILTER_CONTEXT_PATH, "");
        property(ServletProperties.PROPERTY_WEB_PAGE_CONTENT_REGEX, "");
        property(ServletProperties.RESOURCE_CONFIG_CLASS, "");
        property(WebComponent.APPLICATION_CONFIG_CLASS, "");
        property(WebComponent.RESOURCE_CONFIG_CLASS, "");
        */

        register(LoggingFilter.class);
        register(SecurityRequestFilter.class);

        //register(MvcFeature.class);
        register(JspMvcFeature.class);

        // Multipart
        register(MultiPartFeature.class);

        configureSwagger(baseUri);
        
        new ObjectMapper()
                //.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        // Logging.
        //register(new LoggingFeature(getLogger(), Level.FINEST, LoggingFeature.Verbosity.PAYLOAD_ANY, 8192));

        // Tracing support.
        //register(DeclarativeLinkingFeature.class);
    }

    protected void configureSwagger(URI baseUri) {
        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setTitle("Auto-scaling API");
        swaggerConfig.setDescription("ModellBahn Application");
        swaggerConfig.setVersion(ApiPaths.VERSION);
        swaggerConfig.setSchemes(new String[] { baseUri.getScheme() });
        swaggerConfig.setHost(baseUri.getHost());
        swaggerConfig.setBasePath(ApiPaths.SWAGGER_ROOT);
        swaggerConfig.setResourcePackage(getPackages());
        swaggerConfig.setLicenseUrl("https://github.com/linepro/ModellBahn/LICENSE");
        swaggerConfig.setScan(true);
        //swaggerConfig.setContact("https://hortonworks.com/contact-sales/");
        swaggerConfig.setPrettyPrint(true);

        SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, swaggerConfig);

        //register(SwaggerListingResource.class);
        register(SwaggerSerializers.class);
    }

    public WebappContext configureJsp(HttpServer server) {
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

        context.deploy(server);

        return context;
    }

    /**
     *
     *
     * Gets the logger.
     *
     * @return the logger
     */
    protected Logger getLogger() {
        Logger logger = java.util.logging.Logger.getLogger("ModellBahn");
        logger.setLevel(Level.FINEST);

        //SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        return logger;
    }

    public String getPackages() {
        return PACKAGES;
    }
}