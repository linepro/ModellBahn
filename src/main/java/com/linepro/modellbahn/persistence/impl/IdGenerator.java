package com.linepro.modellbahn.persistence.impl;

import java.util.List;

import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.IIdGenerator;
import com.linepro.modellbahn.persistence.IPersister;

public class IdGenerator implements IIdGenerator {

    protected final IPersister<?> persister;

    protected final String queryString;
    
    public IdGenerator(IPersister<?> persister) {
        this.persister = persister;

        queryString = new StringBuffer("SELECT e.")
                .append(DBNames.NAME)
                .append(" FROM ")
                .append(getPersister().getEntityName())
                .append(" e ORDER BY e.")
                .append(DBNames.NAME)
                .append(" DESC")
                .toString();
    }

    @Override
    public String getNextId() {
        Long id = 1L;
        
        getPersister().begin();

        @SuppressWarnings("unchecked")
        List<String> names = getPersister().getEntityManager().createQuery(queryString.toString()).getResultList();

        if (!names.isEmpty()) {
            id = Long.parseLong(names.get(0)) + 1;
        }

        getPersister().commit();
        
        return String.format("%05d", id);
    }

    protected IPersister<?> getPersister() {
        return persister;
    }
}
