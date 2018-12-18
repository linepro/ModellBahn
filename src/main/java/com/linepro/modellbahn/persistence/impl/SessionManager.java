package com.linepro.modellbahn.persistence.impl;

import com.linepro.modellbahn.persistence.ISessionManager;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import java.sql.SQLException;

public class SessionManager implements ISessionManager {

    private final Logger logger;

    private final EntityManager entityManager;

    private final EntityTransaction transaction;

    @Inject
    public SessionManager(EntityManagerFactory entityManagerFactory, final ILoggerFactory logManager) {
        this.logger = logManager.getLogger(SessionManager.class.getName());
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = getEntityManager().getTransaction();
    }

    @Override
    public void begin() {
        if (!getTransaction().isActive()) {
            getTransaction().begin();
            getEntityManager().setFlushMode(FlushModeType.COMMIT);
            logger.debug("begin(" + getTransaction() + ")");
        } else {
            Exception e = new SQLException("nested transaction");
            e.fillInStackTrace();
            try {
                logger.error("begin(" + getTransaction() + "): nested transaction", e);
            } catch (Exception e1) {
            }
        }
    }

    @Override
    public void commit() {
        if (getTransaction().isActive()) {
            getTransaction().commit();
            logger.debug("commit(" + getTransaction() + ")");
        }
        getEntityManager().close();
    }

    @Override
    public void rollback() {
        if (getTransaction().isActive()) {
            getTransaction().rollback();
            logger.debug("rollback(" +  getTransaction() + ")");
        }
        getEntityManager().close();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public EntityTransaction getTransaction() {
        return transaction;
    }
}
