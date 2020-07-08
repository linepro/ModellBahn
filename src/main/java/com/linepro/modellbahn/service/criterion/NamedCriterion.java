package com.linepro.modellbahn.service.criterion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.model.Named;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.Criterion;

public class NamedCriterion<E extends NamedItem> extends AbstractCriterion<E> implements Criterion {

    private final String name;

    private final String bezeichnung;

    private final Boolean deleted;

    public NamedCriterion(Optional<Named> item) {
        this(
            item.map(Named::getName).orElse(null), 
            item.map(Named::getBezeichnung).orElse(null), 
            item.map(Named::getDeleted).orElse(null)
            );
    }

    public NamedCriterion(String name, String bezeichnung, Boolean deleted) {
        this.name = name;
        this.bezeichnung = bezeichnung;
        this.deleted = deleted;
    }

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> root) {
        List<Predicate> where = new ArrayList<>();
        addCondition(criteriaBuilder, root, where, DBNames.NAME, name);
        addCondition(criteriaBuilder, root, where, DBNames.BEZEICHNUNG, bezeichnung);
        addCondition(criteriaBuilder, root, where, DBNames.DELETED, deleted);
        return where.toArray(new Predicate[0]);
    }
}
