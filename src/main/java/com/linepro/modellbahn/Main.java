package com.linepro.modellbahn;

import java.net.InetAddress;
import java.net.URI;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.linepro.modellbahn.guice.IModellBahnFactory;
import com.linepro.modellbahn.guice.ModellBahnModule;

/**
 * Main.
 * The ModellBahn launcher 
 * @author  $Author:$
 * @version $Id:$
 */
public class Main {
    
    /** The Constant PROTOCOL. */
    protected static final String PROTOCOL = "http://";

    /** The Constant PORT. */
    protected static final int PORT = 8086;

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new ModellBahnModule());

            StringBuilder url = new StringBuilder(PROTOCOL)
                    .append(InetAddress.getLocalHost().getCanonicalHostName())
                    .append(":")
                    .append(PORT)
                    .append("/modellbahn");

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