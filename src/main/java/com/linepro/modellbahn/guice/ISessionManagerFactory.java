package com.linepro.modellbahn.guice;

import com.linepro.modellbahn.persistence.impl.SessionManager;

/**
 * IModellBahnFactory
 * Creates a ModellBahn instance using Guice injection.
 * @author   $Author$
 * @version  $Id$
 */
public interface ISessionManagerFactory {

    /**
     * Creates a ModellBahn instance.
     *
     * @param baseUri the base uri for the application
     * @return the application.
     */
    SessionManager create();
}
