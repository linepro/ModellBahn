package com.linepro.modellbahn.service.criterion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.Criterion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderTypCriterion extends AbstractCriterion<DecoderTyp> implements Criterion {

    private final Optional<DecoderTypModel> decoderTypModel;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> decoderTyp) {
        List<Predicate> where = new ArrayList<>();
        if (decoderTypModel.isPresent()) {
            DecoderTypModel model = decoderTypModel.get();
            addJoinCondition(criteriaBuilder, decoderTyp, where, DBNames.HERSTELLER, model.getHersteller());
            addCondition(criteriaBuilder, decoderTyp, where, DBNames.BESTELL_NR, model.getBestellNr());
            addCondition(criteriaBuilder, decoderTyp, where, DBNames.BEZEICHNUNG, model.getBezeichnung());
            addCondition(criteriaBuilder, decoderTyp, where, DBNames.I_MAX, model.getIMax());
            addJoinCondition(criteriaBuilder, decoderTyp, where, DBNames.PROTOKOLL, model.getProtokoll());
            addCondition(criteriaBuilder, decoderTyp, where, DBNames.FAHRSTUFE, model.getFahrstufe());
            addCondition(criteriaBuilder, decoderTyp, where, DBNames.SOUND, model.getSound());
            addCondition(criteriaBuilder, decoderTyp, where, DBNames.KONFIGURATION, model.getKonfiguration());
            addCondition(criteriaBuilder, decoderTyp, where, DBNames.STECKER, model.getStecker());
            addCondition(criteriaBuilder, decoderTyp, where, DBNames.DELETED, model.getDeleted());
        }
        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "decoderTyp.noChildren";
    }
}
