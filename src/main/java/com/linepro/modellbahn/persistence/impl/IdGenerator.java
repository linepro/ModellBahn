package com.linepro.modellbahn.persistence.impl;

import java.util.List;

import javax.inject.Inject;

import com.linepro.modellbahn.guice.ISessionManagerFactory;
import com.linepro.modellbahn.persistence.IIdGenerator;
import com.linepro.modellbahn.persistence.ISessionManager;

public class IdGenerator implements IIdGenerator {

    private final ISessionManagerFactory sessionManagerFactory;
    
    @Inject
    public IdGenerator(final ISessionManagerFactory sessionManagerFactory) {
        this.sessionManagerFactory = sessionManagerFactory;
    }

    @Override
    public String getNextId(String entityName, String keyName) {
        long id = 1L;
        
        ISessionManager session = getSession();

        String queryString = "SELECT e."
            + keyName
            + " FROM "
            + entityName
            + " e ORDER BY e."
            + keyName
            + " DESC";

        @SuppressWarnings("unchecked")
        List<String> names = session.getEntityManager().createQuery(queryString).getResultList();

        if (!names.isEmpty()) {
            id = Long.parseLong(names.get(0)) + 1;
        }

        session.commit();
        
        return String.format("%05d", id);
    }

    private ISessionManager getSession() {
        SessionManager sessionManager = sessionManagerFactory.create();
        sessionManager.begin();
        return sessionManager;
    }
}
