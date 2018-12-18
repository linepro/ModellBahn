package com.linepro.modellbahn;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.linepro.modellbahn.guice.IModellBahnFactory;
import com.linepro.modellbahn.guice.ModellBahnModule;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.net.InetAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Main.
 * The ModellBahn launcher 
 * @author  $Author:$
 * @version $Id:$
 */
public class Main {
    
    private static final String STATIC_PATH = "./webapp";

    private static final String STORE_PATH = STATIC_PATH;

    private static final String WEB_APP_ROOT = "ModellBahn";

    /** The Constant PROTOCOL. */
    private static final String HTTP = "http";

    private static final String HTTPS = "https";

    /** The Constant PORT. */
    private static final String PORT = "8086";

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption(Option.builder("p").longOpt("port").argName("port").hasArg().desc("port number - default " + PORT).build());
        options.addOption(Option.builder("k").longOpt("keystore").argName("keystore").hasArg().desc("keystore location").build());
        options.addOption(Option.builder("y").longOpt("keypwd").argName("password").hasArg().desc("keystore password").build());
        options.addOption(Option.builder("w").longOpt("webroot").argName("webroot").hasArg().desc("root url for application will be served as ${webroot}/api and ${webroot}/web - default " + WEB_APP_ROOT).build());
        options.addOption(Option.builder("c").longOpt("content").argName("staticPath").hasArg().desc("comma separated list of folders for web content - default " + STATIC_PATH).build());
        options.addOption(Option.builder("s").longOpt("store").argName("store").hasArg().desc("file store location - default " + STORE_PATH).build());

        try {
            CommandLine commandLine = new DefaultParser().parse(options, args);

            int port = Integer.valueOf(commandLine.getOptionValue("p", PORT));
            String keystore = commandLine.getOptionValue("k");
            String keypwd = commandLine.getOptionValue("y");
            String webRoot = commandLine.getOptionValue("w", WEB_APP_ROOT);
            String staticPaths = commandLine.getOptionValue("c", STATIC_PATH);

            final List<String> staticRoots = Arrays.asList(staticPaths.split(","));

            String storePath = commandLine.getOptionValue("s", staticRoots.get(0));

            // TODO: proper SSL validation
            String protocol = (keystore == null || keypwd == null) ? HTTP : HTTPS;

            Injector injector = Guice.createInjector(new ModellBahnModule());

            StringBuilder url = new StringBuilder(protocol)
                    .append("://")
                    .append(InetAddress.getLocalHost().getCanonicalHostName())
                    .append(":")
                    .append(port)
                    .append("/")
                    .append(webRoot);

            URI baseUri = URI.create(url.toString());

            System.out.println("Starting " + baseUri.toString());

            // TODO: SSL configuration
            injector.getInstance(IModellBahnFactory.class)
                    .create(baseUri, staticRoots, storePath)
                    .run();
        } catch (ParseException e) {
            System.err.println( e.getMessage() );

            new HelpFormatter().printHelp( "ModellBahn", options, true );
        } catch (Exception e) {
            System.err.println("Start failed" + e.getMessage());

            e.printStackTrace();

            System.exit(-1);
        }
    }
}