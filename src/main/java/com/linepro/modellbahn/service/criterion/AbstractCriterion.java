package com.linepro.modellbahn.service.criterion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.util.StringUtils;

public abstract class AbstractCriterion implements Criterion {

    private static final Predicate[] PREDICATES = new Predicate[0];
    private static final String ANY_CHAR = "?";
    private static final String ANY_STRING = "*";

    private static final String SQL_ANY_STRING = "%";
    private static final String SQL_ANY_CHAR = "_";

    private static final String ESCAPE = "\\";

    protected List<Predicate> addCondition(CriteriaBuilder criteriaBuilder, From<?,?> root, List<Predicate> where, String columnName, String name) {
        if (StringUtils.hasText(name)) {
            if (hasWildcard(name)) {
                where.add(criteriaBuilder.like(root.<String>get(columnName), resolveWildcards(name)));
            } else {
                where.add(criteriaBuilder.equal(root.get(columnName), name));
            }
        }

        return where;
    }

    protected String resolveWildcards(String name) {
        return name.replaceAll(ESCAPE + ANY_STRING, SQL_ANY_STRING).replaceAll(ESCAPE + ANY_CHAR, SQL_ANY_CHAR);
    }

    protected boolean hasWildcard(String name) {
        return name.contains(ANY_STRING) || name.contains(ANY_CHAR);
    }

    protected List<Predicate> addCondition(CriteriaBuilder criteriaBuilder, From<?,?> root, List<Predicate> where, String columnName, Number number) {
        if (number != null) {
            where.add(criteriaBuilder.equal(root.get(columnName), number));
        }

        return where;
    }

    protected List<Predicate> addCondition(CriteriaBuilder criteriaBuilder, From<?,?> root, List<Predicate> where, String columnName, LocalDate date) {
        if (date != null) {
            where.add(criteriaBuilder.equal(root.get(columnName), date));
        }

        return where;
    }

    protected List<Predicate> addCondition(CriteriaBuilder criteriaBuilder, From<?,?> root, List<Predicate> where, String columnName, Boolean truth) {
        if (truth != null) {
            where.add(criteriaBuilder.equal(root.get(columnName), truth));
        }

        return where;
    }

    protected List<Predicate> addCondition(CriteriaBuilder criteriaBuilder, From<?,?> root, List<Predicate> where, String columnName, Enum<?> value) {
        if (value != null) {
            where.add(criteriaBuilder.equal(root.get(columnName), value));
        }

        return where;
    }

    protected List<Predicate> addJoinCondition(CriteriaBuilder criteriaBuilder, From<?,?> root, List<Predicate> where, String relationName, String columnName, String value) {
        if (StringUtils.hasText(value)) {
            Join<?, ?> join = root.join(relationName);
            List<Predicate> on = new ArrayList<>();
            addCondition(criteriaBuilder, join, on, columnName, value);
            join.on(asArray(on));
        }

        return where;
    }

    protected Predicate[] asArray(List<Predicate> on) {
        return on.toArray(PREDICATES);
    }
}
