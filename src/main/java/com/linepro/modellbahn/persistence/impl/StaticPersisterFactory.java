package com.linepro.modellbahn.persistence.impl;

import com.linepro.modellbahn.persistence.IPersisterFactory;

import javax.inject.Inject;

/**
 * StaticPersisterFactory is a bridge between Guice (used by persistence) and HK2 (used by Jersey). 
 * TODO: change Guice to HK2 at some point since they are incompatible and HK2 is forced on us by Jersey.
 * 
 * @author  $Author:$
 * @version $Id:$
 */
public class StaticPersisterFactory {

    @Inject
    protected static IPersisterFactory factory;

    /**
     * Gets the persister factory
     *
     * @return the iPersister factory
     */
    public static IPersisterFactory get() {
        return factory;
    }
}