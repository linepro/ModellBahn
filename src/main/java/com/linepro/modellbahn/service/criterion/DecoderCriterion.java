package com.linepro.modellbahn.service.criterion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.Criterion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderCriterion extends AbstractCriterion<Decoder> implements Criterion {

    private final Optional<DecoderModel> decoderModel;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> decoder) {
        List<Predicate> where = new ArrayList<>();
        if (decoderModel.isPresent()) {
            DecoderModel model = decoderModel.get();
            addCondition(criteriaBuilder, decoder, where, DBNames.DECODER_ID, model.getDecoderId());
            addCondition(criteriaBuilder, decoder, where, DBNames.BEZEICHNUNG, model.getBezeichnung());
            if (StringUtils.hasText(model.getHersteller()) || StringUtils.hasText(model.getBestellNr()) || model.getIMax() != null ||
                model.getSound() != null || model.getKonfiguration() != null || model.getStecker() != null) {
                Join<?,?> decoderTyp = decoder.join(DBNames.DECODER_TYP);
                addJoinCondition(criteriaBuilder, decoderTyp, where, DBNames.HERSTELLER, model.getHersteller());
                addCondition(criteriaBuilder, decoderTyp, where, DBNames.BESTELL_NR, model.getBestellNr());
                addCondition(criteriaBuilder, decoderTyp, where, DBNames.I_MAX, model.getIMax());
                addCondition(criteriaBuilder, decoderTyp, where, DBNames.SOUND, model.getSound());
                addCondition(criteriaBuilder, decoderTyp, where, DBNames.KONFIGURATION, model.getKonfiguration());
                addCondition(criteriaBuilder, decoderTyp, where, DBNames.STECKER, model.getStecker());
            }
            addJoinCondition(criteriaBuilder, decoder, where, DBNames.PROTOKOLL, model.getProtokoll());
            addCondition(criteriaBuilder, decoder, where, DBNames.FAHRSTUFE, model.getFahrstufe());
            addCondition(criteriaBuilder, decoder, where, DBNames.STATUS, model.getStatus());
            addCondition(criteriaBuilder, decoder, where, DBNames.DELETED, model.getDeleted());
        }
        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "decoder.noChildren";
    }
}
