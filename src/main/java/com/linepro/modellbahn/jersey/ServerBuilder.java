package com.linepro.modellbahn.jersey;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * ServerBuilder.
 * Factory to create an HttpServer instance.
 * @author   $Author$
 * @version  $Id$
 */
public class ServerBuilder {

    /**
     * creates the server.
     *
     * @param baseUri the base uri for the application
     * @param configuration the application configuration
     * @return the http server
     */
    public HttpServer getServer(URI baseUri, ResourceConfig configuration) {
        SSLEngineConfigurator sslEngineConfigurator = null;

        boolean secure = false;

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri,
                configuration,
                secure,
                sslEngineConfigurator,
                false);

        return server;
    }
}
