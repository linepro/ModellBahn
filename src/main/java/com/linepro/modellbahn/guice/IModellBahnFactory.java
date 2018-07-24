package com.linepro.modellbahn.guice;

import java.net.URI;

import com.linepro.modellbahn.ModellBahn;

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
    ModellBahn create(URI baseUri);
}
