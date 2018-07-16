package com.linepro.modellbahn.persistence.impl;

import javax.inject.Inject;

import com.linepro.modellbahn.persistence.IPersisterFactory;

/**
 * StaticPersisterFactory is a bridge between Guice (used by persistence) and HK2 (used by Jersey). 
 * TODO: change Guice to HK2 at some point since they are incompatible and HK2 is forced on us by Jersey.
 * 
 * @author JohnG
 * @version $id$
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