package com.linepro.modellbahn.service.criterion;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.Named;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.Criterion;

public class ZugCriterion extends NamedCriterion<Zug> implements Criterion {

    private final Optional<ZugModel> zugModel;

    public ZugCriterion(Optional<ZugModel> zugModel) {
        super(
            zugModel.map(Named::getName).orElse(null), 
            zugModel.map(Named::getBezeichnung).orElse(null), 
            zugModel.map(Named::getDeleted).orElse(null)
            );
        this.zugModel = zugModel;
    }

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> zug) {
        List<Predicate> where = Arrays.asList(super.getCriteria(criteriaBuilder, zug));
        if (zugModel.isPresent()) {
            ZugModel model = zugModel.get();
            if (StringUtils.hasText(model.getZugTyp())) {
                Join<?,?> zugTyp = zug.join(DBNames.ZUG_TYP);
                addJoinCondition(criteriaBuilder, zugTyp, where, DBNames.NAME, model.getZugTyp());
            }
        }
        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "zug.noChildren";
    }
}
