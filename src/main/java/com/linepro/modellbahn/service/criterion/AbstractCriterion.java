package com.linepro.modellbahn.service.criterion;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.persistence.DBNames;

public abstract class AbstractCriterion<E extends Item> {

    private static final String ANY_CHAR = "?";
    private static final String ANY_STRING = "*";

    private static final String SQL_ANY_STRING = "%";
    private static final String SQL_ANY_CHAR = "_";

    private static final String ESCAPE = "\\";

    protected List<Predicate> addCondition(CriteriaBuilder criteriaBuilder, From<?,?> root, List<Predicate> where, String columnName, String name) {
        if (StringUtils.hasText(name)) {
            if (name.contains(ANY_STRING) || name.contains(ANY_CHAR)) {
                where.add(criteriaBuilder.like(root.get(columnName), name.replaceAll(ESCAPE + ANY_STRING, SQL_ANY_STRING).replaceAll(ESCAPE + ANY_CHAR, SQL_ANY_CHAR)));
            } else {
                where.add(criteriaBuilder.equal(root.get(columnName), name));
            }
        }

        return where;
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

    protected List<Predicate> addJoinCondition(CriteriaBuilder criteriaBuilder, From<?,?> root, List<Predicate> where, String columnName, String name) {
        if (StringUtils.hasText(name)) {
            addCondition(criteriaBuilder, root.join(columnName), where, DBNames.NAME, name);
        }

        return where;
    }
}
