package com.linepro.modellbahn.service.criterion;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Criterion {

    default Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, Root<?> root) {
        return new Predicate[0];
    }

    default String getGraphName() {
        return null;
    }
}
