package com.linepro.modellbahn;

import java.net.InetAddress;
import java.net.URI;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.linepro.modellbahn.guice.IModellBahnFactory;
import com.linepro.modellbahn.guice.ModellBahnModule;

public class Main {
    protected static final String PROTOCOL = "http://";

    protected static final int PORT = 8086;

    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new ModellBahnModule());

            StringBuilder url = new StringBuilder(PROTOCOL)
                    .append(InetAddress.getLocalHost().getCanonicalHostName())
                    .append(":")
                    .append(PORT);

            URI baseUri = URI.create(url.toString());

            System.out.println("Starting " + baseUri.toString());

            injector.getInstance(IModellBahnFactory.class)
                    .create(baseUri)
                    .run();
        } catch (Exception e) {
            System.err.println("Start failed");

            e.printStackTrace();

            System.exit(-1);
        }
    }
}