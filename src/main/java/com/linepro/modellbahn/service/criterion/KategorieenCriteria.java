package com.linepro.modellbahn.service.criterion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.Criterion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KategorieenCriteria implements Criterion {

    private final Optional<List<String>> kategorieen;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> kategorie) {
        List<Predicate> where = new ArrayList<>();
        if (kategorieen.isPresent()) {
            kategorie.get(DBNames.NAME).in(kategorieen.get());
        }
        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "kategorie.withChildren";
    }
}
