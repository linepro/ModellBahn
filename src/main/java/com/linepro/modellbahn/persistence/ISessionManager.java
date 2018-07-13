package com.linepro.modellbahn.persistence;

import javax.persistence.EntityManager;

public interface ISessionManager {

    EntityManager getEntityManager();

    void begin();

    void commit();

    void rollback();
}
