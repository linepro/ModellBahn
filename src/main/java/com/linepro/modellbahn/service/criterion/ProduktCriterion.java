package com.linepro.modellbahn.service.criterion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.Criterion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProduktCriterion extends AbstractCriterion<Produkt> implements Criterion {

    private final Optional<ProduktModel> produktModel;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> produkt) {
        List<Predicate> where = new ArrayList<>();
        if (produktModel.isPresent()) {
            ProduktModel model = produktModel.get();
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.HERSTELLER, model.getHersteller());
            addCondition(criteriaBuilder, produkt, where, DBNames.BESTELL_NR, model.getBestellNr());
            addCondition(criteriaBuilder, produkt, where, DBNames.BEZEICHNUNG, model.getBezeichnung());
            if (StringUtils.hasText(model.getKategorie()) || StringUtils.hasText(model.getUnterKategorie())) {
                Join<?,?> unterkategorie = produkt.join(DBNames.UNTER_KATEGORIE);
                if (StringUtils.hasText(model.getKategorie())) {
                    addJoinCondition(criteriaBuilder, unterkategorie, where, DBNames.KATEGORIE, model.getKategorie());
                }
                addCondition(criteriaBuilder, unterkategorie, where, DBNames.NAME, model.getUnterKategorie());
            }
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.MASSSTAB, model.getMassstab());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.SPURWEITE, model.getSpurweite());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.EPOCH, model.getEpoch());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.BAHNVERWALTUNG, model.getBahnverwaltung());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.GATTUNG, model.getGattung());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.ACHSFOLG, model.getAchsfolg());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.SONDERMODELL, model.getSondermodell());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.AUFBAU, model.getAufbau());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.LICHT, model.getLicht());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.VORBILD, model.getGattung());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.KUPPLUNG, model.getKupplung());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.STEUERUNG, model.getSteuerung());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.DECODER_TYP, model.getDecoderTypHersteller());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.DECODER_TYP, model.getDecoderTypBestellNr());
            addJoinCondition(criteriaBuilder, produkt, where, DBNames.MOTOR_TYP, model.getMotorTyp());
            addCondition(criteriaBuilder, produkt, where, DBNames.ANMERKUNG, model.getAnmerkung());
            addCondition(criteriaBuilder, produkt, where, DBNames.BETREIBSNUMMER, model.getBetreibsnummer());
            addCondition(criteriaBuilder, produkt, where, DBNames.BAUZEIT, model.getBauzeit());
            addCondition(criteriaBuilder, produkt, where, DBNames.LANGE, model.getLange());
            addCondition(criteriaBuilder, produkt, where, DBNames.DELETED, model.getDeleted());
        }
        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "produkt.noChildren";
    }
}
