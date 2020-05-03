package com.linepro.modellbahn.logging;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;
import org.apache.logging.log4j.status.StatusLogger;

@Plugin(name = "bootstrap", category = StrLookup.CATEGORY)
public class BootstrapLookup implements StrLookup {

    private static final Logger logger = StatusLogger.getLogger();
    private static final Marker LOOKUP = MarkerManager.getMarker("LOOKUP");

    @Override
    public String lookup(String key) {
        String value = getString(key, "/bootstrap.properties");

        if (value == null) {
            value = getString(key, "/application.properties");
        }

        return value;
    }

    private String getString(String key, String file) {
        try (InputStream input = BootstrapLookup.class.getResourceAsStream(file)) {
            if (input != null) {
                Properties prop = new Properties();

                prop.load(input);

                return prop.getProperty(key);
            } else if (logger.isTraceEnabled()) {
                logger.warn(LOOKUP, file + " not found looking up " + key);
            }
        } catch (Exception e) {
            logger.error(LOOKUP, "Error looking up " + key + " in " + file, e);
        }

        return null;
    }

    @Override
    public String lookup(LogEvent event, String key) {
        return this.lookup(key);
    }
}
