package com.linepro.modellbahn.service.criterion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.linepro.modellbahn.persistence.DBNames;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class KategorieenCriteria extends AbstractCriterion {

    private final Optional<List<String>> optKategorieen;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, Root<?> kategorie) {
        List<Predicate> where = new ArrayList<>();

        if (optKategorieen.isPresent()) {
            List<String> kategorieen = optKategorieen.get();

            if (kategorieen.size() == 1) {
                addCondition(criteriaBuilder, kategorie, where, DBNames.BEZEICHNUNG, kategorieen.get(0));
            } else {
                where.add(criteriaBuilder.in(kategorie.get(DBNames.NAME)).value(kategorieen));
            }
        }

        return asArray(where);
    }

    @Override
    public String getGraphName() {
        return "kategorie.withChildren";
    }
}
