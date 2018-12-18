package com.linepro.modellbahn.guice;

import com.linepro.modellbahn.ModellBahn;

import java.net.URI;
import java.util.Collection;

/**
 * IModellBahnFactory
 * Creates a ModellBahn instance using Guice injection.
 * @author   $Author$
 * @version  $Id$
 */
public interface IModellBahnFactory {

    /**
     * Creates a ModellBahn instance.
     *
     * @param baseUri the base uri for the application
     * @return the application.
     */
    ModellBahn create(URI baseUri, Collection<String> staticRoots, String storeRoot);
}
