package com.linepro.modellbahn.jersey;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerBuilder {

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
