package com.linepro.modellbahn.persistence;

import javax.persistence.Query;

public interface IKey {
    void addCriteria(Query query) throws Exception;
}
