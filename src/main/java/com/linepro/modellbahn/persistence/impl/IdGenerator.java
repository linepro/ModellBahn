package com.linepro.modellbahn.persistence.impl;

import java.util.List;

import javax.inject.Inject;

import com.linepro.modellbahn.guice.ISessionManagerFactory;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.IIdGenerator;
import com.linepro.modellbahn.persistence.ISessionManager;

public class IdGenerator implements IIdGenerator {

    protected final ISessionManagerFactory sessionManagerFactory;
    
    @Inject
    public IdGenerator(final ISessionManagerFactory sessionManagerFactory) {
        this.sessionManagerFactory = sessionManagerFactory;
    }

    @Override
    public String getNextId(String entityName) {
        Long id = 1L;
        
        ISessionManager session = getSession();

        String queryString = new StringBuffer("SELECT e.")
                .append(DBNames.NAME)
                .append(" FROM ")
                .append(entityName)
                .append(" e ORDER BY e.")
                .append(DBNames.NAME)
                .append(" DESC")
                .toString();

        @SuppressWarnings("unchecked")
        List<String> names = session.getEntityManager().createQuery(queryString.toString()).getResultList();

        if (!names.isEmpty()) {
            id = Long.parseLong(names.get(0)) + 1;
        }

        session.commit();
        
        return String.format("%05d", id);
    }

    protected ISessionManager getSession() {
        SessionManager sessionManager = sessionManagerFactory.create();
        sessionManager.begin();
        return sessionManager;
    }
}
