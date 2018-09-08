package com.linepro.modellbahn.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * ISessionManager.
 * Simplified session manager.
 * @author   $Author$
 * @version  $Id$
 */
public interface ISessionManager {

    /**
     * Gets the entity manager.
     *
     * @return the entity manager
     */
    EntityManager getEntityManager();

    /**
     * Begins a transaction.
     */
    void begin();

    /**
     * Commits a transaction.
     */
    void commit();

    /**
     * Rolls back a transaction.
     */
    void rollback();

    EntityTransaction getTransaction();
}
