package com.linepro.modellbahn.persistence.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

@SuppressWarnings("serial")
abstract class AbstractIdGenerator implements ValueGenerator<String>, Serializable {

	private final String query;

    public AbstractIdGenerator(String entityName, String fieldName) {
    	this.query = "SELECT e."
                + fieldName
                + " FROM "
                + entityName
                + " e ORDER BY e."
                + fieldName
                + " DESC";
    }
    
    public String generateValue(Session session, Object owner) {
    	Long id = 1L;

        @SuppressWarnings("unchecked")
		List<String> names = session.createQuery(query).getResultList();

        if (!names.isEmpty()) {
            id = Long.parseLong(names.get(0)) + 1;
        }

        return String.format("%05d", id);
    }
}