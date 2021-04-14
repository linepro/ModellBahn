package com.linepro.modellbahn.service.criterion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.Criterion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArtikelCriterion extends AbstractCriterion<Artikel> implements Criterion {

    private final Optional<ArtikelModel> artikelModel;

    @Override
    public Predicate[] getCriteria(CriteriaBuilder criteriaBuilder, Root<?> artikel) {
        List<Predicate> where = new ArrayList<>();
        if (artikelModel.isPresent()) {
            ArtikelModel model = artikelModel.get();
            addCondition(criteriaBuilder, artikel, where, DBNames.ARTIKEL_ID, model.getArtikelId());
            if (StringUtils.hasText(model.getHersteller()) || StringUtils.hasText(model.getBestellNr())) {
                Join<?,?> produkt = artikel.join(DBNames.PRODUKT);
                if (StringUtils.hasText(model.getHersteller())) {
                    addJoinCondition(criteriaBuilder, produkt, where, DBNames.HERSTELLER, model.getHersteller());
                }
                addCondition(criteriaBuilder, produkt, where, DBNames.BESTELL_NR, model.getBestellNr());
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
                addCondition(criteriaBuilder, produkt, where, DBNames.BETREIBSNUMMER, model.getBetreibsnummer());
                addCondition(criteriaBuilder, produkt, where, DBNames.LANGE, model.getLange());
            }
            addCondition(criteriaBuilder, artikel, where, DBNames.BEZEICHNUNG, model.getBezeichnung());
            addJoinCondition(criteriaBuilder, artikel, where, DBNames.LICHT, model.getLicht());
            addJoinCondition(criteriaBuilder, artikel, where, DBNames.KUPPLUNG, model.getKupplung());
            addJoinCondition(criteriaBuilder, artikel, where, DBNames.STEUERUNG, model.getSteuerung());
            addJoinCondition(criteriaBuilder, artikel, where, DBNames.DECODER, model.getDecoder());
            addJoinCondition(criteriaBuilder, artikel, where, DBNames.MOTOR_TYP, model.getMotorTyp());
            addCondition(criteriaBuilder, artikel, where, DBNames.KAUFDATUM, model.getKaufdatum());
            addCondition(criteriaBuilder, artikel, where, DBNames.WAHRUNG, model.getWahrung());
            addCondition(criteriaBuilder, artikel, where, DBNames.PREIS, model.getPreis());
            addCondition(criteriaBuilder, artikel, where, DBNames.MENGE, model.getMenge());
            addCondition(criteriaBuilder, artikel, where, DBNames.VERBLEIBENDE, model.getVerbleibende());
            addCondition(criteriaBuilder, artikel, where, DBNames.ANMERKUNG, model.getAnmerkung());
            addCondition(criteriaBuilder, artikel, where, DBNames.BELADUNG, model.getBeladung());
            addCondition(criteriaBuilder, artikel, where, DBNames.STATUS, model.getStatus());
        }
        return where.toArray(new Predicate[0]);
    }

    @Override
    public String getGraphName() {
        return "artikel.noChildren";
    }
}
